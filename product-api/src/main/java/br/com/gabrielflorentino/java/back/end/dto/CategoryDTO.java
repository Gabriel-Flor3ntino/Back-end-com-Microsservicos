package br.com.gabrielflorentino.java.back.end.dto;

import br.com.gabrielflorentino.java.back.end.model.Category;
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

	public static CategoryDTO convert(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(category.getId());
		categoryDTO.setNome(category.getNome());
		return categoryDTO;
	}
}
