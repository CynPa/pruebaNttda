package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.Cliente;
import com.example.demo.dominio.Cuenta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.CuentaRepository;
@Service
public class ServiceCuenta {
	@Autowired
	CuentaRepository cuentaRepo;
	
	public void insertarCuenta(Cuenta cuenta) {
		cuentaRepo.save(cuenta);
	}
	
	public Cuenta getCuentaxNumero(String numero) {
		return cuentaRepo.getCuentaxNumero(numero);
	}
	
	
	public void modificarCuenta(Cuenta cuenta) {
		cuentaRepo.save(cuenta);
	}
	
	public void eliminarCuenta(Cuenta cuenta) {
		cuentaRepo.delete(cuenta);
	}
}
