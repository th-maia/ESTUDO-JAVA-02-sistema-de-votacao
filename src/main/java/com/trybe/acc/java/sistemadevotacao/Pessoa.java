package com.trybe.acc.java.sistemadevotacao;

/**
 * classe abstrata pessoa que herda para PessoaCandidata ou PessoaEleitora.
 */
public abstract class Pessoa {
  public String nome;

  public Pessoa(String nome) {
    this.nome = nome;
  }
  
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
