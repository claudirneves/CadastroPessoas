package com.claudir.CadastroPessoas;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.claudir.CadastroPessoas.service.PessoaService;

@SpringBootApplication
public class CadastroPessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroPessoasApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper;
	}
	
	

}
