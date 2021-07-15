package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UsuarioCadastradoException;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
//@CrossOrigin("http://localhost:4200")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario) {
		try {
			service.salvar(usuario);
		} catch (UsuarioCadastradoException e) {
			e.printStackTrace();
		}
		
	}
	
}
