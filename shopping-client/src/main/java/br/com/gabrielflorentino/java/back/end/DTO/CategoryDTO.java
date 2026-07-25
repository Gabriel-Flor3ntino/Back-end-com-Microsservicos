package br.com.gabrielflorentino.java.back.end.DTO;

import jakarta.validation.constraints.NotNull;

public class CategoryDTO {

	@NotNull
	private long id;
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
