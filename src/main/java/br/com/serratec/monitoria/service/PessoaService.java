package br.com.serratec.monitoria.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.serratec.monitoria.dto.PessoaDTO;
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

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}

	public Pessoa findById(Long id) throws CustomException {
		Optional<Pessoa> optional = pessoaRepository.findById(id);
		if (optional.isEmpty()) {
			throw new CustomException("Pessoa não encontrada com id " + id, HttpStatus.OK);
		}
		return optional.get();
	}

	public PessoaDTO create(PessoaDTO pessoaDto) {
		Pessoa pessoa = new Pessoa();

		pessoa.setNome(pessoaDto.getNome());
		pessoa.setSobrenome(pessoaDto.getSobrenome());

		Pessoa pessoaSave = pessoaRepository.save(pessoa);

		pessoaDto.setId(pessoaSave.getId());

		return pessoaDto;
	}

	public PessoaDTO uptade(PessoaDTO pessoaDto, Long id) throws CustomException {
		Pessoa pessoaFind = this.findById(id);

		if (Strings.isNotEmpty(pessoaDto.getNome())) {
			pessoaFind.setNome(pessoaDto.getNome());
		}

		if (Strings.isNotEmpty(pessoaDto.getSobrenome())) {
			pessoaFind.setSobrenome(pessoaDto.getSobrenome());
		}

		pessoaRepository.save(pessoaFind);
		pessoaDto.setId(pessoaFind.getId());

		return pessoaDto;
	}

	public void delete(Long id) throws CustomException {
		this.findById(id);
		pessoaRepository.deleteById(id);
	}

}
