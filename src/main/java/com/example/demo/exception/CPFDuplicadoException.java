package com.example.demo.exception;

public class CPFDuplicadoException extends RuntimeException{

	public CPFDuplicadoException(String CPF) {
		super("O CPF "+CPF+" já está cadastrado");
	}
	
}
