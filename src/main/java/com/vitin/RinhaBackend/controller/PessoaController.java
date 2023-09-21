package com.vitin.RinhaBackend.controller;

import com.vitin.RinhaBackend.entity.Pessoa;
import com.vitin.RinhaBackend.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid Pessoa p){
        p = pessoaService.create(p);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(p.getId()).toUri();
        return ResponseEntity.created(uri).body(p);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable(name = "id") UUID id){
        Pessoa p = pessoaService.findById(id);
        return ResponseEntity.ok().body(p);
    }

    @GetMapping
    public ResponseEntity buscaTermo(@RequestParam(value = "t") String termo){
        return ResponseEntity.ok().body(pessoaService.buscaTermo(termo));
    }

    @GetMapping(value = "/contagem-pessoas")
    public String count(){
        return pessoaService.count().toString();
    }

}
