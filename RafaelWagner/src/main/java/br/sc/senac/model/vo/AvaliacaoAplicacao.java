package br.sc.senac.model.vo;

public class AvaliacaoAplicacao {
	
	private AplicacaoVacina aplicacao;
	private String descricao;
	private int nota;
	
	public AvaliacaoAplicacao() {
		super();
	}

	public AvaliacaoAplicacao(AplicacaoVacina aplicacao, String descricao, int nota) {
		super();
		this.aplicacao = aplicacao;
		this.descricao = descricao;
		if(nota > 5 || nota < 1) {
			System.out.println("Nota informada invalida. A nota pode ser atribuida de 1 a 5");	}
		else this.nota = nota;
	}

	public AplicacaoVacina getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(AplicacaoVacina aplicacao) {
		this.aplicacao = aplicacao;
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
		if(nota > 5 || nota < 1) {
			System.out.println("Nota informada invalida. A nota pode ser atribuida de 1 a 5");	}
		else this.nota = nota;
	}


	
	

}
