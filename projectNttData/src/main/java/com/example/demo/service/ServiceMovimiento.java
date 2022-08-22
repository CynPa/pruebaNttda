package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.Movimiento;
import com.example.demo.repository.MovimientoRepository;
import com.example.demo.utils.MovimientosDTO;

@Service
public class ServiceMovimiento {
	@Autowired
	MovimientoRepository movimientoRepo;
	
	public void insertarMovimiento(Movimiento movimiento) {
		movimientoRepo.save(movimiento);
	}
	
	public List<Movimiento> getMovimientos(){
		return movimientoRepo.getMovimientos();
	}
	
	
	public void modificarMovimiento(Movimiento movimiento) {
		movimientoRepo.save(movimiento);
	}
	
	public void eliminarMovimiento(Movimiento movimiento) {
		movimientoRepo.delete(movimiento);
	}
	
	public List<MovimientosDTO> getMovimientos(String id,Date f1,Date f2)
	{
		List<MovimientosDTO> movimientosL=new ArrayList<MovimientosDTO>();
		MovimientosDTO movimiento;
		List<Object[]> lista=movimientoRepo.getMovimiento(id, f1, f2);
		for (Object[] obj : lista) {
			///Fecha 	Cliente 	Numero Cuenta 	Tipo 	Saldo Inicial 	Estado 	Movimiento 	Saldo 

			movimiento=new MovimientosDTO();
			movimiento.setFecha(obj[0].toString());
			movimiento.setCliente(obj[1].toString());
			movimiento.setNumeroCuenta(obj[2].toString());
			movimiento.setTipo(obj[3].toString());
			movimiento.setSaldoInicial(Double.parseDouble(obj[4].toString()));
			movimiento.setEstado(obj[5].toString());
			movimiento.setMovimiento(Double.parseDouble(obj[6].toString()));
			movimiento.setSaldo(Double.parseDouble(obj[7].toString()));
			
			movimientosL.add(movimiento);
		}
		return movimientosL;
	}
}
