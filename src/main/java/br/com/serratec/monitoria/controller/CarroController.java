package br.com.serratec.monitoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.monitoria.model.Carro;
import br.com.serratec.monitoria.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	@Autowired
	private CarroService carroService;
	
	@PostMapping
	public Carro save(@RequestBody Carro carro) {
		return carroService.save(carro);
	}
	
	@GetMapping
	public List<Carro> findAll(){
		return carroService.findAll();
	}
	
}
