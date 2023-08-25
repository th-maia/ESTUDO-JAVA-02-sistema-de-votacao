package com.trybe.acc.java.sistemadevotacao;

/**
 * Classe principal para o sistema de votação.
 */
public class Principal {

  /**
   * metodo main que irá iniciar a classe para gerenciar a votação.
   */
  public static void main(String[] args) {
    GerenciamentoVotacao gerenciamentoVotacao = new GerenciamentoVotacao();
    gerenciamentoVotacao.iniciar();
  }
}
