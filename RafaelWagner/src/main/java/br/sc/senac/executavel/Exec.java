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

		AplicacaoVacina aplicacaoVacina = new AplicacaoVacina();
		aplicacaoVacina.setDataAplicação(LocalDate.now());
		aplicacaoVacina.setIdPessoa(1);
		aplicacaoVacina.setIdVacina(1);
		
		
		AplicacaoDAO aplicacaoDAO = new AplicacaoDAO();
		aplicacaoDAO.cadastrarAplicacaoVacina(aplicacaoVacina);		

	}

}
