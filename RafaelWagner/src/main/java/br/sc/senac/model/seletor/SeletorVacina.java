package br.sc.senac.model.seletor;

import java.time.LocalDate;

import br.sc.senac.model.vo.Vacina;

public class SeletorVacina extends Vacina{
	
	private LocalDate dataLimite;


	public SeletorVacina() {
		super();
	}
	
	
	public boolean temfiltro() {
		boolean temFiltro = false;
		
		if (this.getIdVacina() > 0) {
			temFiltro = true;
		}
		
		if (this.getDataInicioPesquisa() != null) {
			temFiltro = true;
		}
		
		if (this.getEstagioVacina() != null) {
			temFiltro = true;
		}
		
		if (this.getDataLimite() != null) {
			temFiltro = true;
		}
		
		if (this.getNome() != null && this.getNome().length() > 0) {
			temFiltro = true;
		}
		
		if (this.getNomePaisOrigem() != null && this.getNomePaisOrigem().length() > 0) {
			temFiltro = true;
		}
		
		if (this.getPesquisadorResponsavel().getNome() != null && this.getPesquisadorResponsavel().getNome().length() > 0) {
			temFiltro = true;
		}

		
		return temFiltro;			
	}
	

	public LocalDate getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDate dataLimite) {
		this.dataLimite = dataLimite;
	}
	
	

}
