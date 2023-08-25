package com.trybe.acc.java.sistemadevotacao;

/**
 * objeto pessoa eleitora que extende da classe pessoa.
 */
public class PessoaEleitora extends Pessoa {
  private String cpf;
  
  public PessoaEleitora(String nome, String cpf) {
    super(nome);
    this.cpf = cpf;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
}
