package br.sc.senac.controller;

import java.util.List;

import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.exception.PessoaNaoPesquisador;
import br.sc.senac.model.BO.AplicacaoVacinaBO;
import br.sc.senac.model.BO.PessoaBO;
import br.sc.senac.model.BO.VacinaBO;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.Pessoa;
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
	
	public Pessoa consultarPesquisadorController(Pessoa pesquisador) throws PessoaNaoPesquisador {
		PessoaBO pessoaBO = new PessoaBO();
		
		Pessoa consulta = pessoaBO.consultarPessoarPorCpfBO(pesquisador.getCpf());
		if (consulta != null) {
			
			if (!pesquisador.getCategoria().equals(CategoriaPessoa.PESQUISADOR)) {
				throw new PessoaNaoPesquisador("Esta pessoa não foi cadastrada como pesquisador.");
			} else {
				return consulta;
			}
	
		}
		
		return pesquisador;
	}
	
	public String atualizarVacinaController(Vacina vacina) throws NomeVacinaIndisponivelException {
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
				vacina = vacinaBO.consultarVacinaPorNomeEPais(vacina);
				
				if (vacina != null) {
					AplicacaoVacinaBO aplicacaoVacinaBO = new AplicacaoVacinaBO();
					if (aplicacaoVacinaBO.consultarAplicacaoPorIdVacina(vacina.getIdVacina()).isEmpty()) {
						return vacinaBO.excluirVacinaBOByNomeEPais(vacina);
					} else {
						return "Não é possível excluir vacina que já tem aplicação cadastrada.";
					}
					

				} else {
					return "Vacina não cadastrada.";
				}
			

		} else {
			
			
			return "Faltaram os campos:" + msg;
		}
		
	}
	
	private String confirmarTodosCamposVacina(Vacina vacina) {
		String camposRestantes = "";
		
		
		if(vacina.getNome() == null || vacina.getNome().contentEquals("")) {
			camposRestantes += "/nNome";
		}
		
		if(vacina.getNomePaisOrigem() ==null || vacina.getNomePaisOrigem().contentEquals("")) {
			camposRestantes += "/nPais de Origem";
		}
		
		if(vacina.getDataInicioPesquisa() ==null) {
			camposRestantes += "/nData de início da pesquisa";
		}
		
		if(vacina.getPesquisadorResponsavel() ==null || vacina.getPesquisadorResponsavel().getCpf() == null) {
			camposRestantes += "/nPesquisador Responsável";
		}
		
		if(vacina.getEstagioVacina() ==null ) {
			camposRestantes += "/nEstágio da vacina";
		}
		
		if (camposRestantes != "") {
			return "Faltaram os campos " + camposRestantes;
		} else return null;
	}


}
