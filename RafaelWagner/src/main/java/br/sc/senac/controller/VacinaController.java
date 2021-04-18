package br.sc.senac.controller;

import java.util.List;

import br.sc.senac.exception.CampoInvalidoVacina;
import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.exception.PessoaNaoPesquisador;
import br.sc.senac.model.BO.AplicacaoVacinaBO;
import br.sc.senac.model.BO.PessoaBO;
import br.sc.senac.model.BO.VacinaBO;
import br.sc.senac.model.vo.CategoriaPessoa;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class VacinaController {
	
	public String cadastrarVacinaController(Vacina vacina) throws NomeVacinaIndisponivelException, CampoInvalidoVacina{


			VacinaBO vacinaBO = new VacinaBO();
			return vacinaBO.cadastrarVacinaBO(vacina);

		

	}
	
	public Pessoa validarPesquisadorController(Pessoa pesquisador) throws PessoaNaoPesquisador {
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
	
	public String atualizarVacinaController(Vacina vacina) throws NomeVacinaIndisponivelException, CampoInvalidoVacina {

			VacinaBO vacinaBO = new VacinaBO();
			
			return vacinaBO.cadastrarVacinaBO(vacina);


		
	}
	
	
	public List<Vacina> consultarTodasVacinas(){
		VacinaBO vacinaBO = new VacinaBO();
		
		return vacinaBO.consultarTodasVacinasBO();
	}
	
	public String excluirVacina(Vacina vacina) {

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

		
	}
	

	public Object atualizarvacinaController(Vacina vacinaAtualizar) throws CampoInvalidoVacina {
		VacinaBO vacinaBO = new VacinaBO();
		return vacinaBO.atualizarVacinaBO(vacinaAtualizar);
	}


}
