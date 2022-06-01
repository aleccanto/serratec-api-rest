package br.com.serratec.monitoria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.monitoria.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
	
	Optional<Carro> findByNome(String nome);
	
	List<Carro> findAllByOrderByNomeAsc();
	
}
