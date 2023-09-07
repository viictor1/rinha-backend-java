package com.vitin.RinhaBackend.service;

import com.vitin.RinhaBackend.entity.Pessoa;
import com.vitin.RinhaBackend.exception.BadRequestException;
import com.vitin.RinhaBackend.exception.InvalidRequestException;
import com.vitin.RinhaBackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa p){

        System.out.println(p.getNome());
        System.out.println(p.getApelido());

        if(p.getNome() == null || p.getNome().isEmpty() || p.getApelido() == null || p.getNome().isEmpty()){
            throw new InvalidRequestException("");
        }
        if(pessoaRepository.findByApelido(p.getApelido()).isPresent()){
            throw new InvalidRequestException("Apelido já está em uso");
        }



        if(!(p.getNome().matches("^[^\\d]*$"))){
            throw new BadRequestException("Invalid name");
        }

        if(!(p.getApelido().matches("^[^\\d]*$"))){
            throw new BadRequestException("Invalid apelido");
        }

        if(p.getStack() != null){
            for (String stack: p.getStack()) {
                if(!(stack.matches("^[^\\d]*$"))){
                    throw new BadRequestException("Invalid stack");
                }
            }
        }

        return pessoaRepository.save(p);
    }



}
