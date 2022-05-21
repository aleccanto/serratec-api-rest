package br.com.serratec.monitoria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.monitoria.exception.CustomException;
import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public PessoaService() {
		System.out.println("Fui iniciado...");
	}
	
	public List<Pessoa> findAll(){
		return pessoaRepository.findAll();
	}
	
	public Pessoa findById(Long id) throws CustomException {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if(optional.isEmpty()) {
			throw new CustomException("Pessoa não encontrada com id " + id);
		}
		return optional.get();
	}
	
	public Pessoa create(Pessoa pessoa) {
		pessoa.setId(null);
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa update(Pessoa pessoa, Long id) throws CustomException {
		Pessoa pessoaFind = this.findById(id);
		pessoaFind.setNome(pessoa.getNome());
		pessoaFind.setSobrenome(pessoa.getSobrenome());
		return pessoaRepository.save(pessoaFind);
	}
	
	public void delete(Long id) throws CustomException {
		this.findById(id);
		pessoaRepository.deleteById(id);
	}
	

}
