package br.sc.senac.executavel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.DAO.AplicacaoDAO;
import br.sc.senac.model.DAO.AvaliacaoAplicacaoDAO;
import br.sc.senac.model.DAO.VacinaDAO;
import br.sc.senac.model.vo.AplicacaoVacina;
import br.sc.senac.model.vo.AvaliacaoAplicacao;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Vacina;

public class Exec {
	
	public static void main (String[] args) {

		AvaliacaoAplicacao avaliacaoAplicacao = new AvaliacaoAplicacao();
		AplicacaoVacina apl = new AplicacaoVacina();
		apl.setIdAplicacao(2);
		avaliacaoAplicacao.setDataAvaliacao(LocalDate.now());
		avaliacaoAplicacao.setDescricao("muito bom, perfeita");
		avaliacaoAplicacao.setNota(5);
		avaliacaoAplicacao.setAplicacao(apl);
		avaliacaoAplicacao.setIdAvaliacaoAplicacao(2);
		
		AvaliacaoAplicacaoDAO avDAO = new AvaliacaoAplicacaoDAO();
		
		avDAO.excluirAvaliacaoAplicacao(1);

	}

}
