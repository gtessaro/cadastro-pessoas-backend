package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Source")
@RestController
@RequestMapping("/source")
public class SourceController {

    @GetMapping("/back-end")
    @ApiOperation("Acesso ao link do código fonte")
    public String getSourceCodeBackEnd() {
        return "https://github.com/gtessaro/";
    }
    
    @GetMapping("/front-end")
    @ApiOperation("Acesso ao link do código fonte")
    public String getSourceCodeFrontEnd() {
    	return "https://github.com/gtessaro/";
    }
}