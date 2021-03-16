package br.sc.senac.model.vo;

import java.time.LocalDate;

public class Vacina {
	
	private int idVacina;
	private String nome;
	private String pesquisadorResponsavel;
	private LocalDate dataInicioPesquisa;
	private EstagioVacina estagioVacina;
	
	public Vacina(int idVacina, String nome, String pesquisadorResponsavel, LocalDate dataInicioPesquisa,
			EstagioVacina estagioVacina) {
		super();
		this.nome = nome;
		this.pesquisadorResponsavel = pesquisadorResponsavel;
		this.dataInicioPesquisa = dataInicioPesquisa;
		this.estagioVacina = estagioVacina;
		this.idVacina = idVacina;
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

	public String getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(String pesquisadorResponsavel) {
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
