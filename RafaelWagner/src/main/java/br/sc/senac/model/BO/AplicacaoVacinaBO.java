package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.model.DAO.AplicacaoDAO;
import br.sc.senac.model.vo.AplicacaoVacina;

public class AplicacaoVacinaBO {
	
	public String cadastrarAplicacaoBO (AplicacaoVacina aplicacaoVacina) {
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		
		List<AplicacaoVacina> vacinacoesPessoa = aplicacaoDAO.consultarTodasAplicacacoesPorPessoa(aplicacaoVacina.getIdPessoa());

		
		if(vacinacoesPessoa.size() < 2 || vacinacoesPessoa.isEmpty()) {
			return "Aplicação cadastrada com sucesso.";
		} else {
			return "Erro, foram feitas todas vacinações";
		}
		
	}
	
	public String atualizarAplicacaoBO (AplicacaoVacina aplicacaoVacina) {
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		
		if(aplicacaoDAO.atualizarAplicacao(aplicacaoVacina) == 1) {
			return "Aplicação atualizada.";
		} else {
			return "Erro ao Atualizar a Aplicação.";
		}
		
	}
	
	public AplicacaoVacina consultarAplicacaoPorId(int id) {
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		
		
		return aplicacaoDAO.consultarAplicacaoVacinaPorId(id);
	}
	
	public List<AplicacaoVacina> consultarTodasAplicacoesBO(){
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		
		return aplicacaoDAO.consultarTodasAplicacacoes();
		
	}

	public List<AplicacaoVacina> consultarAplicacaoPorIdVacina(int idVacina) {
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		
		return aplicacaoDAO.consultarAplicacaoVacinaPorIdVacina(idVacina);
		
	}

}
