package br.com.serratec.monitoria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String sobrenome;

	private String username;
	
	private String password;

//	@OneToMany(mappedBy = "pessoa")
//	private List<Carro> carros;

	@ManyToMany(mappedBy = "pessoas")
	private List<Carro> carros;

	@OneToOne(mappedBy = "pessoa")
	private Aluga aluga;

	public Pessoa() {

	}

	public Pessoa(Long id, String nome, String sobrenome, String username, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.username = username;
		this.password = senha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Carro> getCarros() {
		return carros;
	}

}
