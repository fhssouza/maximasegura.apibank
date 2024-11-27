package com.maximaseguranca.apibank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

    @NotNull(message = "O nome é obrigatório.")
    @Size(min = 1, max = 100, message = "O nome deve ter entre 1 e 100 caracteres.")
    private String nome;

    @Positive(message = "A idade deve ser maior que zero.")
    private int idade;

    @NotNull(message = "O CPF é obrigatório.")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres.")
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
