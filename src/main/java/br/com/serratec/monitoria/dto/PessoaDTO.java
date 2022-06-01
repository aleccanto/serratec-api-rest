package br.com.serratec.monitoria.dto;

public class PessoaDTO {
	
	private Long id;
	
	private String nome;
	
	private String sobrenome;

	public PessoaDTO(Long id, String nome, String sobrenome) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
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

	public String getSobrenome() {
		
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		if(sobrenome != null && !"".equals(sobrenome)) {
			this.sobrenome = sobrenome;
		}
	}

}
