package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominio.Movimiento;
@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long>{

	@Query("select u from Movimiento u  ")
	public List<Movimiento> getMovimientos();
	
	@Query(value="select b.fecha,c.nombre,a.numero,a.tipo ,case tipo_movimiento when 'Deposito' then "
			+ "b.saldo-b.valor else b.saldo+(b.valor*-1)+(b.valor*-1) end saldo_inicial, "
			+ "a.estado,b.valor movimiento,b.saldo saldo_disponible "
			+ "from cuenta a ,movimiento b,persona c "
			+ "where a.id=b.fk_cuenta and a.fk_cliente=c.id and c.identificacion=:id "
			+ "and fecha between :f1 and :f2", nativeQuery=true)
	public List<Object[]> getMovimiento(String id,Date f1,Date f2);
	
}
