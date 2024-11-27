package com.maximaseguranca.apibank.dto;

import java.math.BigDecimal;

public class UsuarioConsultaResponseDTO {

    private String nome;
    private int idade;
    private String cpf;
    private String numeroConta;
    private BigDecimal saldo;

    public UsuarioConsultaResponseDTO(String nome, int idade, String cpf, String numeroConta, BigDecimal saldo) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

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

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
