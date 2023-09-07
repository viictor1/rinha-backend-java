package com.vitin.RinhaBackend.service;

import com.vitin.RinhaBackend.entity.Pessoa;
import com.vitin.RinhaBackend.exception.BadRequestException;
import com.vitin.RinhaBackend.exception.InvalidRequestException;
import com.vitin.RinhaBackend.exception.ResourceNotFoundException;
import com.vitin.RinhaBackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa create(Pessoa p){
        validate(p);

        return pessoaRepository.save(p);
    }

    public Pessoa findById(UUID id){
        Pessoa p = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa not found"));
        return p;
    }

    public List<Pessoa> buscaTermo(String termo){
        if(termo.isEmpty()){
            throw new BadRequestException("Invalid termo");
        }
        return pessoaRepository.buscarTermo(termo);
    }

    public Long count(){
        return pessoaRepository.count();
    }


    private void validate(Pessoa p){
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
    }


}
