package br.com.serratec.monitoria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.monitoria.dto.PessoaDTO;
import br.com.serratec.monitoria.exception.CustomException;
import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.service.PessoaService;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

//	public PessoaController(PessoaService pessoaService) {
//		super();
//		this.pessoaService = pessoaService;
//	}

	@GetMapping("/")
	public ResponseEntity<List<Pessoa>> findAll() {
		return ResponseEntity.ok(pessoaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) throws CustomException {
		return ResponseEntity.ok(pessoaService.findById(id));
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO pessoa) {
		return new ResponseEntity<>(pessoaService.create(pessoa), HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<PessoaDTO> update(@RequestBody PessoaDTO pessoa, @PathVariable Long id) {
		return new ResponseEntity<>(pessoaService.uptade(pessoa, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		pessoaService.delete(id);
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		return ResponseEntity.noContent().build();
	}

}
