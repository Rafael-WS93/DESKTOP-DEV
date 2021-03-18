package br.sc.senac.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvaliacaoAplicacao {
	
	private int idAvaliacaoAplicacao;
	private AplicacaoVacina aplicacao;
	private String descricao;
	private int nota;
	private LocalDate dataAvaliacao;
	
	public AvaliacaoAplicacao() {
		super();
	}


	public AvaliacaoAplicacao(int idAvaliacaoAplicacao, AplicacaoVacina aplicacao, String descricao, int nota,
			LocalDate dataAvaliacao) {
		super();
		this.idAvaliacaoAplicacao = idAvaliacaoAplicacao;
		this.aplicacao = aplicacao;
		this.descricao = descricao;
		this.nota = nota;
		this.dataAvaliacao = dataAvaliacao;
	}



	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}


	public void setDataAvaliacao(LocalDate localDate) {
		this.dataAvaliacao = localDate;
	}


	public int getIdAvaliacaoAplicacao() {
		return idAvaliacaoAplicacao;
	}



	public void setIdAvaliacaoAplicacao(int idAvaliacaoAplicacao) {
		this.idAvaliacaoAplicacao = idAvaliacaoAplicacao;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}



	public AplicacaoVacina getAplicacao() {
		return aplicacao;
	}



	public void setAplicacao(AplicacaoVacina aplicacao) {
		this.aplicacao = aplicacao;
	}



}
