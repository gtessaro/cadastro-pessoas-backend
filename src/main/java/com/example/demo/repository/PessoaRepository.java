package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public List<Pessoa> findByNomeContaining(String nome);
    public List<Pessoa> findAllByOrderByNomeAsc();
    public List<Pessoa> findAllByOrderByNomeDesc();
}
