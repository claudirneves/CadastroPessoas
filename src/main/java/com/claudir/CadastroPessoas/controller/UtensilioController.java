package com.claudir.CadastroPessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.claudir.CadastroPessoas.dto.UtensilioDTO;
import com.claudir.CadastroPessoas.service.UtensilioService;


@RestController
@RequestMapping(value="/utensilio")
public class UtensilioController {
	
	
	@Autowired
	UtensilioService utensilioService;
	
	@RequestMapping(value = "",method = RequestMethod.GET)
	public Page<UtensilioDTO> list(Pageable pageable){
		return utensilioService.list(pageable);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public UtensilioDTO getById(@PathVariable("id")Long id) {
		return utensilioService.byId(id);
	}

	@RequestMapping( method = RequestMethod.POST)
    public UtensilioDTO add(@RequestBody UtensilioDTO utensilioDTO){
        return utensilioService.save(utensilioDTO);
    }
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public UtensilioDTO update(@PathVariable("id") Long id, @RequestBody UtensilioDTO utensilioDTO) {
		UtensilioDTO.UtensilioDTOBuilder utensilioToBuilder = utensilioDTO.toBuilder();
		utensilioDTO = utensilioToBuilder.id(id).build();
		return utensilioService.save(utensilioDTO);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id) {
		utensilioService.delete(id);
	}
}
