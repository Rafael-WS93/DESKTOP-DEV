package br.sc.senac.executavel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.DAO.VacinaDAO;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Vacina;

public class Exec {
	
	public static void main (String[] args) {
		VacinaDAO vacinaDAO = new VacinaDAO();
//		
//		Vacina vacina = new Vacina();
//		
//		vacina.setDataInicioPesquisa(LocalDate.now());
//		vacina.setEstagioVacina(EstagioVacina.INICIAL);
//		vacina.setNome("nome1");
//		
//		
//		vacina.setPesquisadorResponsavel("pesquisadorRes");
//		
//		
//		
//		vacinaDAO.vacinaCadastrar(vacina);
//	
//		
//		vacina.setPesquisadorResponsavel("outroPesq");
//		vacina.setIdVacina(2);
//		vacinaDAO.atualizarVacina(vacina);
//		
//		vacinaDAO.excluirVacina(3);
//
//		List<Vacina> v = new ArrayList<Vacina>();
//		
//		v = vacinaDAO.consultarTodasVacinas();
//		
//		System.out.println(v.get(1) + "" + " " +v.get(0).getIdVacina());
		// VACINA DAO TESTADO DAO
		
		System.out.println(vacinaDAO.consultaVacinaPorId(1).getNome());

	}

}
