package br.sc.senac.executavel;

import br.sc.senac.model.DAO.PessoaDAO;
import br.sc.senac.view.CadastroVacina;

public class Exec {
	
	public static void main (String[] args) {
		
//		
//		PessoaDAO dao = new PessoaDAO();
//		
//		System.out.println(dao.consultarPessoaPorCpfDAO("48632156988").getNome());
		
	
		CadastroVacina cadastroVacina = new CadastroVacina();
		cadastroVacina.menuVacina();
		
	}

}
