package br.com.serratec.monitoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.monitoria.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
