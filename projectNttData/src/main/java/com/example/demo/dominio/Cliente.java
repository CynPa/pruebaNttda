package com.example.demo.dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Cliente extends Persona{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	private Integer clienteid;
	private String contraseña;
	private Boolean estado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getClienteid() {
		return clienteid;
	}
	public void setClienteid(Integer clienteid) {
		this.clienteid = clienteid;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	 @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	    private List<Cuenta> cuentas;
	
}
