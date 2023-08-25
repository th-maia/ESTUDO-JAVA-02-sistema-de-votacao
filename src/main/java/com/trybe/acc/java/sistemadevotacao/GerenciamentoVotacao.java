package com.trybe.acc.java.sistemadevotacao;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * classe responsavel por gerenciar todas as fases do processo de votação.
 */
public class GerenciamentoVotacao {
  
  public static Scanner scanner = new Scanner(System.in);
  ArrayList<PessoaCandidata> pessoasCandidatas = new ArrayList<PessoaCandidata>();
  ArrayList<PessoaEleitora> pessoasEleitoras = new ArrayList<PessoaEleitora>();
  ArrayList<String> cpfComputado = new ArrayList<String>();
  int totalVotos = 0;
  
  /**
   * metodo construtor que coloca todas as fases da voção em ordem.
   */
  public void iniciar() {

    System.out.println("----------- Bem-vindo ao Sistema de Votação -----------\n");
    boolean recadastrarCandidato = true;
    while (recadastrarCandidato) {
      System.out.println(
          "Cadastrar pessoa candidata?\n"
          + "1 - Sim\n"
          + "2 - Não\n"
          + "Entre com o número correspondente à opção desejada:"
      );
        
      String inputMenu = scanner.next();
      int intMenu = Integer.parseInt(inputMenu);
        
      if (intMenu == 1) {
        System.out.println("Entre com o nome da pessoa candidata:");
        String nomeCandidato = scanner.next();
      
        System.out.println("Entre com o número da pessoa candidata:");
        String valorDigitado = scanner.next();
        int numeroCandidato = Integer.parseInt(valorDigitado);
        cadastrarPessoaCandidata(nomeCandidato, numeroCandidato);
      }
      if (intMenu == 2) {
        recadastrarCandidato = false;
      }
    }
    
    System.out.println("----------- Cadastre as pessoas eleitoras -----------\n");
    boolean recadastrarEleitor = true;
    while (recadastrarEleitor) {
      System.out.println(
          "Cadastrar pessoa eleitora?\n"
          + "1 - Sim\n"
          + "2 - Não\n"
          + "Entre com o número correspondente à opção desejada:"
      );

      String inputMenu = scanner.next();
      int intMenu = Integer.parseInt(inputMenu);

      if (intMenu == 1) {
        System.out.println("Entre com o nome da pessoa eleitora:");
        String nomeEleitor = scanner.next();

        System.out.println("Entre com o cpf da pessoa eleitora:");
        String numeroCpf = scanner.next();

        cadastrarPessoaEleitora(nomeEleitor, numeroCpf);   
      }
      if (intMenu == 2) {
        recadastrarEleitor = false;
      }
    }

    System.out.println("----------- Votação iniciada! -----------\n");
    boolean reMenuVotacao = true;
    while (reMenuVotacao) {
      System.out.println(
            "Entre com o número correspondente à opção desejada:\n"
            + "1 - Votar\n"
            + "2 - Resultado Parcial\n"
            + "3 - Finalizar Votação"
      );

      String inputMenu = scanner.next();
      int intMenu = Integer.parseInt(inputMenu);

      if (intMenu == 1) {
        System.out.println("Entre com o cpf da pessoa eleitora:");
        String cpfPessoaEleitora = scanner.next();
        
        System.out.println("Entre com o número da pessoa candidata:");
        String valorDigitado = scanner.next();
        int numeroPessoaCandidata = Integer.parseInt(valorDigitado);

        votar(cpfPessoaEleitora, numeroPessoaCandidata);
      }
      if (intMenu == 2) {
        mostrarResultado();
      }
      if (intMenu == 3) {
        mostrarResultado();
        reMenuVotacao = false;
      }
    }
  }
  
  /**
   * cadastra uma pessoa candidata criando um objeto pessoaCandidata no array pessoasCandidatas.
   */ 
  public void cadastrarPessoaCandidata(String nome, int numero) {
    if (conferirNumero(numero)) {
      PessoaCandidata pessoaCandidata = new PessoaCandidata(nome, numero);
      pessoasCandidatas.add(pessoaCandidata);
      // System.out.println(pessoasCandidatas);
    }
  }

  /**
   * conferi se o numero do candidato não já está sendo utilizado.
   */
  public boolean conferirNumero(int numeroCandidato) {
    for (PessoaCandidata candidato : pessoasCandidatas) {
      if (numeroCandidato == candidato.getNumero()) {
        System.out.println("Número pessoa candidata já utilizado!");
        return false;
      }
    }
    return true;
  }
  
  /**
   * cadastra uma pessoa eleitora no criando um objeto pessoaEleitora no array pessoas eleitoras.
   */
  public void cadastrarPessoaEleitora(String nome, String cpf) {
    if (conferirCpf(cpf)) {
      PessoaEleitora pessoaEleitora = new PessoaEleitora(nome, cpf);
      pessoasEleitoras.add(pessoaEleitora);
      // System.out.println(pessoasEleitoras);
    }
  }
  
  /**
   * conferi entre os eleitores se o cpf não aparece já entre um deles.
   */
  public boolean conferirCpf(String numeroCpf) {
    for (PessoaEleitora eleitor : pessoasEleitoras) {
      if (numeroCpf.equals(eleitor.getCpf())) {
        System.out.println("Pessoa eleitora já cadastrada!");
        return false;
      }
    }
    return true;
  }
  
  /**
   * reliza o voto da pessoa eleitora para um candidato.
   */
  public void votar(String cpfPessoaEleitora, int numeroPessoaCandidata) {
    if (conferirCpfComputado(cpfPessoaEleitora)) {
      for (PessoaCandidata candidato : pessoasCandidatas) {
        if (candidato.getNumero() == numeroPessoaCandidata) {
          candidato.receberVoto();
          totalVotos += 1;
          cpfComputado.add(cpfPessoaEleitora);
        }
      }
    } else {
      System.out.println("Pessoa eleitora já votou!");
    }
  }
  
  /**
   * confere se o CPF já votou.
   */
  public boolean conferirCpfComputado(String cpfPessoaEleitora) {
    for (String cpf : cpfComputado) {
      if (cpf.equals(cpfPessoaEleitora)) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * mostra como está o resultado da eleição.
   */
  public void mostrarResultado() {
    for (PessoaCandidata candidato : pessoasCandidatas) {
      System.out.println(
          "Nome: " + candidato.getNome() + " - "
          + candidato.getVotos() + " votos " 
          + " ( " + calcularPorcentagemVotos(candidato.getVotos()) + " )");
    }
    System.out.println("Total de votos: " + totalVotos);
  }

  /**
   * calcula a porcetagem dos votos para aparecer no resultado. 
   */
  public float calcularPorcentagemVotos(int votosRecebidos) {
    float floatVotos = totalVotos;
    float floatRecebidos = votosRecebidos; 
    float porcentagem = ((floatRecebidos * 100) / floatVotos);
    return porcentagem;
  }
}
