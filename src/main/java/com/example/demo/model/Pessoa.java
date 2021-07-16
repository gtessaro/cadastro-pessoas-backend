package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;

/**
 * Nome - obrigatório
 * Sexo
 * E-mail - não obrigatório, deve ser validado caso preenchido
 * Data de Nascimento - obrigatório, deve ser validada
 * Naturalidade
 * Nacionalidade
 * CPF - obrigatório, deve ser validado (formato e não pode haver dois
 * cadastros com mesmo cpf)
 */

@ApiModel(value = "Pessoa", description = "Representa uma pessoa")
@Table(
		name = "pessoa",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "cpf")
	    }
)
@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Favor preencher campo nome.")
	private String nome;
	
	@Pattern(regexp = "MASCULINO|FEMININO", message = "Sexo aceita apenas os valores MASCULINO ou FEMININO")
	private String sexo;
	
	@NotNull(message = "Favor preencher campo data de nascimento.")
    private String dataDeNascimento;

    private String naturalidade;
    private String nacionalidade;

    @NotNull(message = "O campo cpf não deve ser nulo")
    @CPF(message = "Favor preencher um CPF válido.")
    private String cpf;

    private String dataCadastro;

    private String dataAtualizacao;

    @Email(message = "Favor preencher um endereço e-mail válido.")
    private String email;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
