package com.vitin.RinhaBackend.service;

import com.vitin.RinhaBackend.entity.Pessoa;
import com.vitin.RinhaBackend.exception.InvalidRequestException;
import com.vitin.RinhaBackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa p){
        if(pessoaRepository.findByApelido(p.getApelido()).isPresent()){
            throw new InvalidRequestException("Apelido já está em uso");
        }

        return pessoaRepository.save(p);
    }



}
