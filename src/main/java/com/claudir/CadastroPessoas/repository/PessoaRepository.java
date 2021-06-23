package com.claudir.CadastroPessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claudir.CadastroPessoas.model.Pessoa;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	Pessoa findByNome(String nome);

}
