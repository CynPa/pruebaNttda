package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dominio.Cliente;
import com.example.demo.service.ServiceCliente;

@RestController
public class ClienteController {
	
	//Cliente
	@Autowired
	ServiceCliente serviceCliente;
	
	@GetMapping("clientes/obtener")
	public List<Cliente> getCliente()
	{
		return serviceCliente.obtenerClientes();
	}
	
	
	@PostMapping("clientes/ingresar")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente insertarCliente(@RequestBody Cliente cliente)
	{
		 return serviceCliente.insertarCliente(cliente);
	}
	
	@PutMapping("clientes/modificar")
	public void modificarCliente(@RequestBody Cliente cli) {
	Cliente cliente=new Cliente();
	
	cliente.setClienteid(cli.getClienteid());
	cliente.setContraseña(cli.getContraseña());
	cliente.setDireccion(cli.getDireccion());
	cliente.setEdad(cli.getEdad());
	cliente.setEstado(cli.getEstado());
	cliente.setGenero(cli.getGenero());
	cliente.setIdentificacion(cli.getIdentificacion());
	cliente.setNombre(cli.getNombre());
	cliente.setTelefono(cli.getTelefono());
	serviceCliente.modificarCliente(cli);
	}
	
	@DeleteMapping("clientes/eliminar")
	public void eliminarCliente(@RequestParam(name="identificador") String id) {
		Cliente cliente=serviceCliente.obtenerClientexId(id);
		cliente.setEstado(false);
		serviceCliente.modificarCliente(cliente);
	}

}
