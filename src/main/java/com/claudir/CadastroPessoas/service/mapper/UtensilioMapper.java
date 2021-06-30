package com.claudir.CadastroPessoas.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.claudir.CadastroPessoas.dto.UtensilioDTO;
import com.claudir.CadastroPessoas.model.Utensilio;

@Service
public class UtensilioMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public Utensilio utensilioDTOToUtensilio(UtensilioDTO utensilioDTO) {
		return modelMapper.map(utensilioDTO, Utensilio.class);
	}
	
	public UtensilioDTO utensilioToUtensilioDTO(Utensilio utensilio) {
		return modelMapper.map(utensilio, UtensilioDTO.class);
	}
	
	public List<Utensilio> utensilioDTOListToUtensilioList(List<UtensilioDTO> utensilioDTOList){
		return utensilioDTOList.stream()
				.map(this::utensilioDTOToUtensilio)
				.collect(Collectors.toList());
	}
	
	public Page<UtensilioDTO> utensilioPageToUtensiloPageDTO(Page<Utensilio> utensilioPage, Pageable pageable, int total){
		return new PageImpl<UtensilioDTO>(
				utensilioPage.stream()
				.map(this::utensilioToUtensilioDTO)
				.collect(Collectors.toList()),pageable,total);
				
	}
}
