package br.sc.senac.model.vo;

import java.time.LocalDate;

public class AplicacaoVacina {
	
	private int idPessoa;
	private int IdVacina;
	private LocalDate dataAplicação;
	private int idAplicacao;
	
	public AplicacaoVacina() {
		super();
	}
	

	public AplicacaoVacina(int idPessoa, int idVacina, LocalDate dataAplicação, int idAplicacao) {
		super();
		this.idPessoa = idPessoa;
		IdVacina = idVacina;
		this.dataAplicação = dataAplicação;
		this.idAplicacao = idAplicacao;
	}




	public LocalDate getDataAplicação() {
		return dataAplicação;
	}

	public void setDataAplicação(LocalDate dataAplicação) {
		this.dataAplicação = dataAplicação;
	}

	public int getIdAplicacao() {
		return idAplicacao;
	}

	public void setIdAplicacao(int idAplicacao) {
		this.idAplicacao = idAplicacao;
	}


	public int getIdPessoa() {
		return idPessoa;
	}




	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}




	public int getIdVacina() {
		return IdVacina;
	}




	public void setIdVacina(int idVacina) {
		IdVacina = idVacina;
	}


	
	
	
}
