package com.claudir.CadastroPessoas.service.mapper;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.claudir.CadastroPessoas.dto.PessoaDTO;
import com.claudir.CadastroPessoas.model.Pessoa;

@Service
public class PessoaMapper {

	@Autowired
    ModelMapper modelMapper;
	              
	public Pessoa pessoaDTOToPessoa(PessoaDTO pessoaDTO){
        return modelMapper.map(pessoaDTO, Pessoa.class);
    }

    public PessoaDTO pessoaToPessoaDTO(Pessoa pessoa){
        return modelMapper.map(pessoa, PessoaDTO.class);
    }

    public List<Pessoa> pessoaDTOListToPessoaList(List<PessoaDTO> pessoaDTOList){
        return pessoaDTOList.stream()
                .map(this::pessoaDTOToPessoa)
                .collect(Collectors.toList());
    }

    public List<PessoaDTO> pessoaListToPessoaDTOList(List<Pessoa> pessoaList){
        return pessoaList.stream()
                .map(this::pessoaToPessoaDTO)
                .collect(Collectors.toList());
    }
                          
    public Page<PessoaDTO> pessoaPageToPessoaDTOPage(Page<Pessoa> pessoaPage, Pageable pageable, int total){
    	return new PageImpl<PessoaDTO>(
                pessoaPage.stream()
                .map(this::pessoaToPessoaDTO)
                .collect(Collectors.toList()),pageable,total);
    }
}
