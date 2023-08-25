package com.trybe.acc.java.sistemadevotacao;


/**
 * Classe que trada das pessoas candidatas que extende de Pessoa. 
 */
public class PessoaCandidata extends Pessoa {
  private int numero;
  private int votos = 0;
  
  public PessoaCandidata(String nome, int numero) {
    super(nome);
    this.numero = numero;
  }
  
  public int getNumero() {
    return numero;
  }
  
  public void setNumero(int numero) {
    this.numero = numero;
  }
  
  public int getVotos() {
    return votos;
  }

  public void setVotos(int votos) {
    this.votos = votos;
  }
  
  public void receberVoto() {
    this.votos += 1; 
  }
}