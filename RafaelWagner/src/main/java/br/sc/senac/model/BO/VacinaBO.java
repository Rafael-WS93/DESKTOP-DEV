package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.model.DAO.PessoaDAO;
import br.sc.senac.model.DAO.VacinaDAO;
import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.Vacina;

public class VacinaBO {
	
	public String cadastrarVacinaBO(Vacina vacina) throws NomeVacinaIndisponivelException {
		
		vacina.setPesquisadorResponsavel(this.cadastrarPesquisadorVacinaBO(vacina.getPesquisadorResponsavel()));
		
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if (vacinaDAO.ConsultarVacinaPorNomeDAO(vacina.getNome()) != null) {
			throw new NomeVacinaIndisponivelException("O nome da vacina "+ vacina.getNome()+ " já está cadastrado");
		} else {
			
			if(vacinaDAO.cadastrarVacinaDAO(vacina)==1) {
				return "Vacina cadastrada no banco de dados com sucesso.";
			} else {
				return "Erro ao cadastrar vacina.";
			}

		}
	}
	
	


	private Pessoa cadastrarPesquisadorVacinaBO(Pessoa pesquisadorResponsavel) {
		PessoaBO pessoaBO = new PessoaBO();
		
		try {
			pessoaBO.cadastrarPessoaBO(pesquisadorResponsavel);
		} catch (CpfIndisponivelException e) {
			System.out.println("Pesquisador ja cadastrado");
		} 
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		return pessoaDAO.consultarPessoaPorCpfDAO(pesquisadorResponsavel.getCpf());
	
		
	}



	public String atualizarVacinaBO(Vacina vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if(vacinaDAO.atualizarVacinaDAO(vacina) == 1) {
			return "Vacina atualizada no banco de dados com sucesso.";
		} else {
			return "Erro ao atualizar vacina";
		}
	}
	
	public String excluirVacinaBOById(int id) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if (vacinaDAO.excluirVacinaDAOById(id) == 1) {
			return "Vacina excluida com sucesso.";
		} else {
			return "Erro ao Excluir Vacina";
		}
	}
	
	public String excluirVacinaBOByNomeEPais(Vacina vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if(vacinaDAO.ConsultarVacinaPorNomeEPais(vacina) != null) {
			
			if (vacinaDAO.excluirVacinaDAOByNomeEPais(vacina) == 1) {
				return "Vacina excluida com sucesso.";
			} else {
				return "Erro ao Excluir Vacina";
			}
			
		} else {
			return "Vacina não existe";
		}
		
		
	}
	
	public Vacina consultarVacinaPorIdBO(int id) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		return vacinaDAO.consultaVacinaPorIdDAO(id);
		
	}
	
	public List<Vacina> consultarTodasVacinasBO() {
		VacinaDAO vacinaDAO = new VacinaDAO();
		return vacinaDAO.consultarTodasVacinasDAO();
	}

}
