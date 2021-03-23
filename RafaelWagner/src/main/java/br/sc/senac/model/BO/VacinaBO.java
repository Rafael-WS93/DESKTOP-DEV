package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.model.DAO.VacinaDAO;
import br.sc.senac.model.vo.Vacina;

public class VacinaBO {
	
	public String cadastrarVacinaBO(Vacina vacina) throws NomeVacinaIndisponivelException {
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
	
	

	public String atualizarVacinaBO(Vacina vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if(vacinaDAO.atualizarVacinaDAO(vacina) == 1) {
			return "Vacina atualizada no banco de dados com sucesso.";
		} else {
			return "Erro ao atualizar vacina";
		}
	}
	
	public String excluirVacinaBO(int id) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		if (vacinaDAO.excluirVacinaDAO(id) == 1) {
			return "Vacina excluida com sucesso.";
		} else {
			return "Erro ao Excluir Vacina";
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
