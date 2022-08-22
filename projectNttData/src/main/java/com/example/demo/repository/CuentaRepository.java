package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.dominio.Cuenta;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

	@Query("select u from Cuenta u where u.estado=true and u.numero=:id ")
	public Cuenta getCuentaxNumero(String id);
}
