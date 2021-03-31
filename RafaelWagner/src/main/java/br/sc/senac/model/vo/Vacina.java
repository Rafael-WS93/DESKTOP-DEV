package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Vacina {
	
	private int idVacina;
	private String nome;
	private Pessoa pesquisadorResponsavel;
	private LocalDate dataInicioPesquisa;
	private EstagioVacina estagioVacina;
	private String nomePaisOrigem;
	

	
	
	
	public Vacina(int idVacina, String nome, Pessoa pesquisadorResponsavel, LocalDate dataInicioPesquisa,
			EstagioVacina estagioVacina, String nome_pais_origem) {
		super();
		this.idVacina = idVacina;
		this.nome = nome;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.estagioVacina = estagioVacina;
		this.nomePaisOrigem = nome_pais_origem;
	}


	public void setNomePaisOrigem(String nome_pais_origem) {
		this.nomePaisOrigem = nome_pais_origem;
	}
	
	public String getNomePaisOrigem() {
		return nomePaisOrigem;
	}


	public Vacina() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Pessoa getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(Pessoa pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

	public LocalDate getDataInicioPesquisa() {
		return dataInicioPesquisa;
	}

	public void setDataInicioPesquisa(LocalDate dataInicioPesquisa) {
		this.dataInicioPesquisa = dataInicioPesquisa;
	}

	public EstagioVacina getEstagioVacina() {
		return estagioVacina;
	}

	public void setEstagioVacina(EstagioVacina estagioVacina) {
		this.estagioVacina = estagioVacina;
	}

	public int getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(int idVacina) {
		this.idVacina = idVacina;
	}


	
}
