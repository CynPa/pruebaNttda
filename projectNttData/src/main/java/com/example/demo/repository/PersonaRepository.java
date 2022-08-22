package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}
