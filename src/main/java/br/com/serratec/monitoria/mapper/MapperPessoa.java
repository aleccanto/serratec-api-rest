package br.com.serratec.monitoria.mapper;

import org.springframework.stereotype.Component;

import br.com.serratec.monitoria.dto.PessoaDTO;
import br.com.serratec.monitoria.model.Pessoa;

@Component
public class MapperPessoa implements Mapper<PessoaDTO, Pessoa> {

	@Override
	public PessoaDTO toDto(Pessoa model) {
		return new PessoaDTO(model.getId(), model.getNome(), model.getSobrenome(), model.getUsername(),
				model.getPassword());
	}

	@Override
	public Pessoa toModel(PessoaDTO dto) {
		return new Pessoa(dto.getId(), dto.getNome(), dto.getSobrenome(), dto.getUsername(), dto.getSenha());
	}

}
