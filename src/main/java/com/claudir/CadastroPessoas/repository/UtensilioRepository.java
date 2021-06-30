package com.claudir.CadastroPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.claudir.CadastroPessoas.model.Utensilio;

@Repository
public interface UtensilioRepository extends JpaRepository<Utensilio, Long> {

	Utensilio getByDescricao(String descricao);

}
