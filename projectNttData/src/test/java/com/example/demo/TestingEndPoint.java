package com.example.demo;

import static org.mockito.Mockito.times;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.example.demo.controller.ClienteController;
import com.example.demo.controller.MovimientoController;
import com.example.demo.dominio.Cliente;
import com.example.demo.dominio.Movimiento;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.MovimientoRepository;
import com.example.demo.service.ServiceCliente;

import io.netty.handler.codec.http.HttpHeaders;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = ClienteController.class)

@ContextConfiguration(classes = {ServiceCliente.class,ClienteController.class})
public class TestingEndPoint {

	@Autowired
	WebTestClient webTestClient;
	@Autowired
	ServiceCliente serviceCliente;
	

	@MockBean
	ClienteRepository clienteRepo;

   
	@Test
	public void testObtenerCliente()
	{
		List<Cliente> clientes=new ArrayList<Cliente>();
		FluxExchangeResult<Cliente> result=
				webTestClient.get().uri("/clientes/obtener")
        .exchange()
        .expectStatus().isOk().returnResult(Cliente.class);
        //expectBodyList(Cliente.class).returnResult();
		Mockito.verify(clienteRepo,times(1)).findAll();
	}
	
	@Test
	  Cliente testCreateCliente() {
	    Cliente cli = new Cliente();
	    cli.setId(3L);
	    cli.setContraseña("1234");
	    cli.setDireccion("sauces");
	    cli.setEdad(20);
	    cli.setEstado(true);
	    cli.setGenero("mascueli");
	    cli.setIdentificacion("0909393936");
	    cli.setNombre("Raul");
	    cli.setTelefono("0999999");
	   cli.setClienteid(2);
	 
	    Mockito.when(clienteRepo.save(cli)).thenReturn(cli);
	 
	    webTestClient.post()
	      .uri("/clientes/ingresar")
	      .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
	      .body(BodyInserters.fromObject(cli))
	      .exchange()
	      .expectStatus().isCreated();
	 
	    return Mockito.verify(clienteRepo, times(1)).save(cli);
	  }
	
	
	
}
