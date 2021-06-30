package com.claudir.CadastroPessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.claudir.CadastroPessoas.dto.PessoaDTO;
import com.claudir.CadastroPessoas.repository.PessoaRepository;
import com.claudir.CadastroPessoas.service.PessoaService;



@RestController
@RequestMapping(value="/pessoa")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<PessoaDTO> list(Pageable pageable){
        return pessoaService.list(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PessoaDTO getById(@PathVariable("id") Long id){
        return pessoaService.byId(id);
    }
    @RequestMapping(value = "/{nome}", method = RequestMethod.GET)
    public PessoaDTO getByNome(@PathVariable("nome") String nome) {
    	return pessoaService.byNome(nome);
    }
    @RequestMapping( method = RequestMethod.POST)
    public PessoaDTO add(@RequestBody PessoaDTO pessoaDTO){
        return pessoaService.save(pessoaDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PessoaDTO update(@PathVariable("id") Long id, @RequestBody PessoaDTO pessoaDTO){
    	PessoaDTO.PessoaDTOBuilder pessoaDTOBuilder = pessoaDTO.toBuilder();
        pessoaDTO = pessoaDTOBuilder.id(id).build();
        return pessoaService.save(pessoaDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id){
        pessoaService.delete(id);
    }
	
	
}
