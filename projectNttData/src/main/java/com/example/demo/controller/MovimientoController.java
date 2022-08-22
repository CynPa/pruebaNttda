package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dominio.Cuenta;
import com.example.demo.dominio.Movimiento;
import com.example.demo.dominio.MovimientoEjemplo;
import com.example.demo.service.ServiceCuenta;
import com.example.demo.service.ServiceMovimiento;
import com.example.demo.utils.MovimientosDTO;
import com.example.demo.utils.Resultado;

@RestController
public class MovimientoController {

	@Autowired
	ServiceMovimiento serviceMovimiento;
	
	@Autowired
	ServiceCuenta serviceCuenta;
	
	
	@GetMapping("movimientos/obtener")
	public List<Movimiento> getMovimientos(){
		return serviceMovimiento.getMovimientos();
	}
	
	@GetMapping("/reportes")
	public List<MovimientosDTO> listaMovimiento(@RequestParam(name="identificacion")String id,
			@RequestParam(name="fecha1") Date fecha1,@RequestParam(name="fecha2") Date fecha2){
		return serviceMovimiento.getMovimientos(id, fecha1, fecha2);
	}
	
	@PostMapping("/movimientos/insertar")
	@ResponseBody
	public Resultado insertarMovimiento(@RequestParam(name = "cuenta") String cuenta,
			@RequestBody MovimientoEjemplo descmovimiento)
	{
		Resultado resultado=new Resultado();
		try {
		Cuenta cuentaObj=serviceCuenta.getCuentaxNumero(cuenta);
		resultado.setResultado("OK");
		resultado.setDescripcion("Movimiento realizado exitosamente");
		Movimiento movimiento=new Movimiento();
		movimiento.setCuenta(cuentaObj);
		movimiento.setFecha(new Date());
		movimiento.setTipoMovimiento(descmovimiento.getTipoMovimiento());
		
		if(movimiento.getTipoMovimiento().equals("Retiro"))
		{if(cuentaObj.getSaldoInicial()>0) {
			if(descmovimiento.getValor()<=cuentaObj.getSaldoInicial()) {
			movimiento.setValor(descmovimiento.getValor()*-1);
			Double saldo=cuentaObj.getSaldoInicial()+movimiento.getValor();
			cuentaObj.setSaldoInicial(saldo);
			movimiento.setSaldo(saldo);
			serviceMovimiento.insertarMovimiento(movimiento);
			}
			else
			{
				resultado.setResultado("Error");
				resultado.setDescripcion("Valor Solicitado mayor al saldo disponible");
				
			}}
			else
			{
				resultado.setResultado("Error");
				resultado.setDescripcion("Saldo no disponible");
			}
		}
		else
			if(movimiento.getTipoMovimiento().equals("Deposito"))
			{
				movimiento.setValor(descmovimiento.getValor()*1);
				Double saldoD=cuentaObj.getSaldoInicial()+movimiento.getValor();
				movimiento.setSaldo(saldoD);
				
				cuentaObj.setSaldoInicial(saldoD);
				serviceMovimiento.insertarMovimiento(movimiento);
			}
			else
			{
				resultado.setResultado("Error");
				resultado.setDescripcion("Valor de movimento no permitido");	
			}
		}
		catch(Exception e)
		{
			resultado.setResultado("Error");
			resultado.setDescripcion(e.toString());
		}
		return resultado;
	}
	
	
	
}
