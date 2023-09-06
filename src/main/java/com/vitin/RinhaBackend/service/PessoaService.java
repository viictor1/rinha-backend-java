package com.vitin.RinhaBackend.service;

import com.vitin.RinhaBackend.entity.Pessoa;
import com.vitin.RinhaBackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa p){
        return pessoaRepository.save(p);
    }



}
