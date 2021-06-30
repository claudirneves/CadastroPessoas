package com.claudir.CadastroPessoas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.claudir.CadastroPessoas.dto.PessoaDTO;
import com.claudir.CadastroPessoas.model.Pessoa;
import com.claudir.CadastroPessoas.repository.PessoaRepository;
import com.claudir.CadastroPessoas.service.mapper.PessoaMapper;

@Service
public class PessoaService {

	 @Autowired
	    PessoaRepository pessoaRepository;
	    @Autowired
	    PessoaMapper pessoaMapper;

	    public Page<PessoaDTO> list(Pageable pageable){
	        Page<Pessoa> pessoaPage = pessoaRepository.findAll(pageable);
	        int totalElements = pessoaPage.getNumberOfElements();
	        System.out.println("passou aquu");
	        return pessoaMapper.pessoaPageToPessoaDTOPage(pessoaPage,pageable,totalElements);
	    }
	    @Transient
	    public PessoaDTO save(PessoaDTO pessoaDTO){
	        Pessoa pessoa = pessoaMapper.pessoaDTOToPessoa(pessoaDTO);
	        pessoa = pessoaRepository.save(pessoa);
	        return pessoaMapper.pessoaToPessoaDTO(pessoa);
	    }

	    public PessoaDTO byId(Long id){
	        Pessoa pessoa = pessoaRepository.getById(id);
	        return pessoaMapper.pessoaToPessoaDTO(pessoa);
	    }

	    public void delete(Long id){
	        pessoaRepository.deleteById(id);
	    }

	    public PessoaDTO byNome(String nome) {
	        Pessoa pessoa = pessoaRepository.findByNome(nome);
	        return pessoaMapper.pessoaToPessoaDTO(pessoa);
	    }
}
