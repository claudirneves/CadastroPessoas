package com.claudir.CadastroPessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.claudir.CadastroPessoas.dto.UtensilioDTO;

import com.claudir.CadastroPessoas.model.Utensilio;
import com.claudir.CadastroPessoas.repository.UtensilioRepository;
import com.claudir.CadastroPessoas.service.mapper.UtensilioMapper;

@Service
public class UtensilioService {

	@Autowired
	UtensilioRepository utensilioRepository;
	@Autowired
	UtensilioMapper utensilioMapper;
	
	public Page<UtensilioDTO> list(Pageable pageable){
		Page<Utensilio> utensilioPage = utensilioRepository.findAll(pageable);
		int totalElements = utensilioPage.getNumberOfElements();
		return utensilioMapper.utensilioPageToUtensiloPageDTO(utensilioPage, pageable, totalElements);
	}
	@Transient
	public UtensilioDTO save(UtensilioDTO utensilioDTO) {
		Utensilio utensilio = utensilioMapper.utensilioDTOToUtensilio(utensilioDTO);
		utensilio = utensilioRepository.save(utensilio);
		return utensilioMapper.utensilioToUtensilioDTO(utensilio);
	}
	
	
	public UtensilioDTO byId(Long id) {
		Utensilio utensilio = utensilioRepository.getById(id);
		return utensilioMapper.utensilioToUtensilioDTO(utensilio);
	}
	
	public void delete(Long id) {
		utensilioRepository.deleteById(id);
	}
	
	public UtensilioDTO byDescricao(String descricao) {
		Utensilio utensilio = utensilioRepository.getByDescricao(descricao);
		return utensilioMapper.utensilioToUtensilioDTO(utensilio);
	}
	
}
