package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{ 

	@Query("select u from Cliente u where u.estado=true and u.identificacion=:id ")
	public Cliente getClientexId(String id);
}