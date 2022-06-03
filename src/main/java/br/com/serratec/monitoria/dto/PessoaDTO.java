package br.com.serratec.monitoria.dto;

public class PessoaDTO {

	private Long id;

	private String nome;

	private String sobrenome;

	private String senha;

	private String username;

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(Long id, String nome, String sobrenome, String username, String senha) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.username = username;
		this.senha = senha;
	}

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		if (sobrenome != null && !"".equals(sobrenome)) {
			this.sobrenome = sobrenome;
		}
	}

}
