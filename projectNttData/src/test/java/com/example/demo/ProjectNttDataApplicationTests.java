package com.example.demo;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.example.demo.controller.MovimientoController;
import com.example.demo.dominio.Movimiento;
import com.example.demo.repository.CuentaRepository;
import com.example.demo.repository.MovimientoRepository;
import com.example.demo.service.ServiceCuenta;
import com.example.demo.service.ServiceMovimiento;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = MovimientoController.class)
@ContextConfiguration(classes = {ServiceMovimiento.class,MovimientoController.class,ServiceCuenta.class})
class ProjectNttDataApplicationTests {

	@Autowired
	WebTestClient webTestClient;
	@Autowired
	ServiceMovimiento serviceMovimiento;
	
	@Autowired
	ServiceCuenta serviceCuenta;
	@MockBean
	MovimientoRepository moviRepo;
	@MockBean
	CuentaRepository cuentaRepo;
	
	
	@Test
	public void testObtenerMovimientos() {
		FluxExchangeResult<Movimiento> result=
				webTestClient.get().uri("/movimientos/obtener")
        .exchange()
        .expectStatus().isOk().returnResult(Movimiento.class);
        //expectBodyList(Cliente.class).returnResult();
		Mockito.verify(moviRepo,times(1)).getMovimientos();
	}
	
	
	

}
