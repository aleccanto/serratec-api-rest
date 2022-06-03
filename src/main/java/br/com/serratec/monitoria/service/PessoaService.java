package br.com.serratec.monitoria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.monitoria.dto.PessoaDTO;
import br.com.serratec.monitoria.exception.CustomException;
import br.com.serratec.monitoria.mapper.Mapper;
import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private Mapper<PessoaDTO, Pessoa> mapper;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
		pessoaDto.setSenha(passwordEncoder.encode(pessoaDto.getNome()));
		return mapper.toDto(pessoaRepository.save(mapper.toModel(pessoaDto)));
	}

	public PessoaDTO uptade(PessoaDTO pessoaDto, Long id) throws CustomException {

		Pessoa pessoaFind = this.findById(id);
		pessoaDto.setId(id);

		BeanUtils.copyProperties(pessoaDto, pessoaFind);

		return mapper.toDto(pessoaRepository.save(pessoaFind));
	}

	public void delete(Long id) throws CustomException {
		this.findById(id);
		pessoaRepository.deleteById(id);
	}

}
