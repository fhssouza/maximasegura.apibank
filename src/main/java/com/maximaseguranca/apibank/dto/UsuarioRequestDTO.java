package com.maximaseguranca.apibank.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRequestDTO {

    @Schema(description="Nome do Usuario", example = "João Silva")
    private String nome;

    @Schema(description="Idade do Usuario", example = "25")
    private int idade;

    @Schema(description="CPF do Usuario", example = "123.456.789-10")
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
