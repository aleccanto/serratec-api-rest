package br.com.serratec.monitoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.monitoria.exception.CustomException;
import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.service.PessoaService;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	public ResponseEntity<List<Pessoa>> findAll(){
		return ResponseEntity.ok(pessoaService.findAll());
	}
	
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws CustomException{
		return ResponseEntity.ok(pessoaService.findById(id));
	}
	
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa){
		return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.CREATED);
	}
	
//	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa, @PathVariable Long id){
//		return new ResponseEntity<>();
//	}

}
