package br.com.serratec.monitoria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

//	@ManyToOne
//	private Pessoa pessoa;

	@ManyToMany
	@JoinTable(
			name = "carros_pessoas", 
			joinColumns = @JoinColumn(name = "carro_id"), 
			inverseJoinColumns = @JoinColumn(name = "pessoa_id"))
	private List<Pessoa> pessoas;

	@OneToOne(mappedBy = "carro")
	private Aluga aluga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
