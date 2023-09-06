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
        if(p.getApelido() == null || p.getApelido().length() > 32 ||pessoaRepository.findByApelido(p.getApelido()).isPresent()){
            throw new InvalidRequestException("Apelido inválido");
        }

        if(p.getNome() == null || p.getNome().length() > 100){
            throw new InvalidRequestException("Nome inválido");
        }

        if(p.getNascimento() == null){
            throw new InvalidRequestException("Data inválida");
        }

        return pessoaRepository.save(p);
    }



}
