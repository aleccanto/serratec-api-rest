package br.com.serratec.monitoria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.serratec.monitoria.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM pessoa WHERE nome = :nome ORDER BY nome ASC")
	Optional<Pessoa> findByName(@Param(value = "nome") String nome);

	Optional<Pessoa> findByUsername(String username);

}
