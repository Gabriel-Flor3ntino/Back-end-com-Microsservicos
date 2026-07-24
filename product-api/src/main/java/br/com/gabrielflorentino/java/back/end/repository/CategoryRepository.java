package br.com.gabrielflorentino.java.back.end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielflorentino.java.back.end.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
