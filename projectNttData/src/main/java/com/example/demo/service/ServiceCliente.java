package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominio.Cliente;
import com.example.demo.repository.ClienteRepository;

@Service
public class ServiceCliente {
	
	
	//Cliente, Cuenta y Movimiento
	@Autowired
	ClienteRepository clienteRepo;
	
	
	public List<Cliente> obtenerClientes() {
		return (List<Cliente>) clienteRepo.findAll();
	}
	
	public Cliente obtenerClientexId(String identificacion)
	{
		return clienteRepo.getClientexId(identificacion);
		
	}
	public Cliente insertarCliente(Cliente cliente) {
		return clienteRepo.save(cliente);
		
	}
	
	
	public void modificarCliente(Cliente cliente) {
		clienteRepo.save(cliente);
	}
	
	public void eliminarCliente(Cliente cliente) {
		clienteRepo.delete(cliente);
	}

}
