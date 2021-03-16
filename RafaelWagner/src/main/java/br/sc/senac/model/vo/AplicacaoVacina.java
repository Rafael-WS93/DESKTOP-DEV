package br.sc.senac.model.vo;

import java.time.LocalDate;

public class AplicacaoVacina {
	
	private Pessoa pessoa;
	private Vacina vacina;
	private LocalDate dataAplicação;
	private int idAplicacao;
	
	public AplicacaoVacina() {
		super();
	}

	public AplicacaoVacina( Pessoa pessoa, Vacina vacina, LocalDate dataAplicação, int idAplicacao) {
		super();
		this.vacina = vacina;
		this.dataAplicação = dataAplicação;
		this.idAplicacao = idAplicacao;
		this.setPessoa(pessoa);
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
}
