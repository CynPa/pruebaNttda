package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dominio.Cliente;
import com.example.demo.dominio.Cuenta;
import com.example.demo.service.ServiceCliente;
import com.example.demo.service.ServiceCuenta;
@RestController
public class CuentaController {

	@Autowired
	ServiceCuenta serviceCuenta;
	@Autowired
	ServiceCliente serviceCliente;
	
	
	@PostMapping("cuentas/ingresar")
	@ResponseBody
	public void insertarCliente(@RequestParam(name = "identificacion") String identifiacion,@RequestBody Cuenta cuenta)
	{
		Cliente cliente=serviceCliente.obtenerClientexId(identifiacion);
		cuenta.setCliente(cliente);
		
		
		serviceCuenta.insertarCuenta(cuenta);
	}

}
