package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.model.DAO.AvaliacaoAplicacaoDAO;
import br.sc.senac.model.vo.AvaliacaoAplicacao;

public class AvaliacaoAplicacaoBO {
	
	public String cadastrarAvaliacaoBO(AvaliacaoAplicacao avaliacaoAplicacao) {
		AvaliacaoAplicacaoDAO avaliacaoAplicacaoDAO = new AvaliacaoAplicacaoDAO();
		
		if (avaliacaoAplicacao.getNota() > 5 || avaliacaoAplicacao.getNota() < 1) {
			
			avaliacaoAplicacaoDAO.atualizarAvaliacaoAplicacao(avaliacaoAplicacao);
			
			return "Erro ao cadastrar vacina. Nota somente de 1 a 5.";
		} else {
			return "Avaliacao cadastrada com sucesso.";
		}
		
	}
	
	public String atualizarAvaliacaoBO(AvaliacaoAplicacao avaliacaoAplicacao) {
		AvaliacaoAplicacaoDAO avaliacaoAplicacaoDAO = new AvaliacaoAplicacaoDAO();
		
		if (avaliacaoAplicacao.getNota() > 5 || avaliacaoAplicacao.getNota() < 1) {
			
			avaliacaoAplicacaoDAO.atualizarAvaliacaoAplicacao(avaliacaoAplicacao);
			
			return "Erro ao atualizar vacina. Nota somente de 1 a 5.";
		} else {
			return "Avaliacao atualizada com sucesso.";
		}
		
	}
	
	public AvaliacaoAplicacao consultarAvaliacaoPorIdBO(int id) {
		AvaliacaoAplicacaoDAO avaliacaoAplicacaoDAO = new AvaliacaoAplicacaoDAO();
		
		
		return avaliacaoAplicacaoDAO.consultarAvaliacaoAplicacaoPorId(id); 
		
	}
	
	public List<AvaliacaoAplicacao> consultarTodasAvaliacoes(){
		AvaliacaoAplicacaoDAO avaliacaoAplicacaoDAO = new AvaliacaoAplicacaoDAO();
		
		return avaliacaoAplicacaoDAO.consultarTodasAvaliacoes();
		
	}
	

}
