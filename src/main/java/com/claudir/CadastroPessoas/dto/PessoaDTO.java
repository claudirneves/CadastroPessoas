package com.claudir.CadastroPessoas.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PessoaDTO {
	
	
	private Long id;
	private String nome;
	private String sobrenome;
	
	

}
