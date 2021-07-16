package com.example.demo.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Pessoas")
@RestController
@RequestMapping("/api/v1/pessoas")
//@CrossOrigin("http://localhost:4200")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @ApiOperation("Criar uma nova pessoa")
    @PostMapping
    @ApiImplicitParam(name = "Authorization", 
    				value = "Bearer Token", 
    				required = true,
    				allowEmptyValue = false, 
    				paramType = "header", 
    				example = "Bearer access_token")
    public ResponseEntity<Pessoa> criar(
    						@ApiParam(name = "corpo", value = "Representação de uma nova pessoa") @Validated @RequestBody Pessoa pessoa, 
    						HttpServletResponse httpServletResponse) {
        
    	// Setar data de cadastro
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatarData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        
        pessoa.setDataCadastro(dataAtual.format(formatarData));

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoaSalva.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @ApiOperation("Buscar uma pessoa pelo seu ID")
    @GetMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Pessoa> buscar(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);
        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas")
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome")
    @GetMapping("/nome/{nome}")
    public List<Pessoa> listarPorNome(@PathVariable String nome) {
        return pessoaRepository.findByNomeContaining(nome);
    }

    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome em ordem crescente")
    @GetMapping("/nome/asc")
    public List<Pessoa> listarPorNomeOrdemCrescente() {
        return pessoaRepository.findAllByOrderByNomeAsc();
    }


    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @ApiOperation("Listar todas as pessoas por nome em ordem decrescente")
    @GetMapping("/nome/desc")
    public List<Pessoa> listarPorNomeOrdemDecrescente() {
        return pessoaRepository.findAllByOrderByNomeDesc();
    }

    @ApiOperation("Deletar uma pessoa")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public void deletar(@ApiParam(value = "ID", example = "1") @PathVariable Long id) {
        Pessoa pessoaDeletada = pessoaRepository.getOne(id);
        pessoaRepository.delete(pessoaDeletada);
    }

    @ApiOperation("Atualizar uma pessoa")
    @PutMapping("/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Bearer Token", required = true,
            allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    public ResponseEntity<Pessoa> atualizar(
    		@ApiParam(value = "ID", example = "1") @PathVariable Long id, 
    		@ApiParam(name = "corpo", value = "Representação de uma nova pessoa") @Validated @RequestBody Pessoa pessoa,
    		HttpServletResponse httpServletResponse) {
        // Setar data de atualização
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatarData =
                DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        
        pessoa.setDataAtualizacao(dataAtual.format(formatarData));

        pessoaRepository.save(pessoa);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(pessoa.getId()).toUri();
        httpServletResponse.setHeader("Location", uri.toASCIIString());

        return ResponseEntity.created(uri).body(pessoa);
    }
}
