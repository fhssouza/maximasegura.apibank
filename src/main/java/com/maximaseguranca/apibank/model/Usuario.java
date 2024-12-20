package com.maximaseguranca.apibank.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade; private String cpf;
    private String numeroConta;
    private BigDecimal saldo;

    {
        this.saldo = BigDecimal.ZERO;
        this.numeroConta = gerarNumeroConta();
    }

    public Usuario() {
    }

    public Usuario(String nome, int idade, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
    }

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
        return saldo.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private String gerarNumeroConta() {
        int numeroConta = new Random().nextInt(999999);
        return String.format("%06d", numeroConta);
    }
}
