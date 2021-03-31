package br.sc.senac.controller;

import java.util.List;

import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.model.BO.VacinaBO;
import br.sc.senac.model.vo.Vacina;

public class VacinaController {
	
	public String cadastrarVacinaController(Vacina vacina) throws NomeVacinaIndisponivelException{

		String msg = this.confirmarTodosCamposVacina(vacina);
		
		if (msg == null) {
			VacinaBO vacinaBO = new VacinaBO();
			return vacinaBO.cadastrarVacinaBO(vacina);
		} else {
			
			return msg;
		}
		

	}
	
	
	public List<Vacina> consultarTodasVacinas(){
		VacinaBO vacinaBO = new VacinaBO();
		
		return vacinaBO.consultarTodasVacinasBO();
	}
	
	public String excluirVacina(Vacina vacina) {
		String msg = null;
		
		if (vacina.getNome() == null) {
			msg = " Nome";
		}
		
		if (vacina.getNomePaisOrigem() == null) {
			msg = " Pais de Origem";
		}
		
		if (msg == null) {
			VacinaBO vacinaBO = new VacinaBO();
			return vacinaBO.excluirVacinaBOByNomeEPais(vacina);
		} else {
			
			return "Faltaram os campos:" + msg;
		}
		
	}
	
	private String confirmarTodosCamposVacina(Vacina vacina) {
		String camposRestantes = null;
		
		
		if(vacina.getNome() == null) {
			camposRestantes += "/nNome";
		}
		
		if(vacina.getNomePaisOrigem() ==null) {
			camposRestantes += "/nPais de Origem";
		}
		
		if(vacina.getDataInicioPesquisa() ==null) {
			camposRestantes += "/nData de início da pesquisa";
		}
		
		if(vacina.getPesquisadorResponsavel() ==null) {
			camposRestantes += "/nPesquisador Responsável";
		}
		
		if(vacina.getEstagioVacina() ==null) {
			camposRestantes += "/nEstágio da vacina";
		}
		
		if (camposRestantes != null) {
			return "Faltaram os campos " + camposRestantes;
		} else return null;
	}


}
