package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Vacina;

public class SeletorVacina extends Vacina{
	
	private LocalDate dataLimite;


	public SeletorVacina() {
		super();
	}
	
	
	public String filtro() {
		String filtros = "";
		
		if (this.getIdVacina() > 0) {
			filtros += "ID - ";
		}
		
		if (this.getDataInicioPesquisa() != null) {
			filtros += "DATA_INICIO - ";
		}
		
		if (this.getEstagioVacina() != null) {
			filtros += "ESTAGIO - ";
		}
		
		if (this.getDataLimite() != null) {
			filtros += "DATA_LIMITE - ";
		}
		
		if (this.getNome() != null && this.getNome().length() > 0) {
			filtros += "NOME - ";
		}
		
		if (this.getNomePaisOrigem() != null && this.getNomePaisOrigem().length() > 0) {
			filtros += "PAIS - ";
		}
		
//		if (this.getPesquisadorResponsavel().getNome() != null && this.getPesquisadorResponsavel().getNome().length() > 0) {
//			filtros = "PESQUISADOR - ";
//		}

		
		return filtros;			
	}
	

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	

}
