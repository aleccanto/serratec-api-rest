package br.com.serratec.monitoria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.monitoria.model.Carro;
import br.com.serratec.monitoria.model.Pessoa;
import br.com.serratec.monitoria.repository.CarroRepository;
import br.com.serratec.monitoria.repository.PessoaRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Carro mudarDonoCarro(Long idCarro, Long idDono) {
		Carro carro = carroRepository.findById(idCarro).get();
		Pessoa pessoa = pessoaRepository.findById(idDono).get();

		carro.getPessoas().add(pessoa);
		carroRepository.save(carro);
//		carroRepository.findAllByOrderByNomeAsc();
		pessoa.getCarros().add(carro);
		pessoaRepository.save(pessoa);

		return carro;
	}

	public Carro save(Carro carro) {
		return carroRepository.save(carro);
	}

	public List<Carro> findAll() {
		return carroRepository.findAllByOrderByNomeAsc();
	}

}
