package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.exception.CampoInvalidoVacina;
import br.sc.senac.exception.NomeVacinaIndisponivelException;
import br.sc.senac.model.DAO.VacinaDAO;
import br.sc.senac.model.DAO.VacinaDAO_seletor;
import br.sc.senac.model.seletor.SeletorVacina;
import br.sc.senac.model.vo.Vacina;

public class VacinaBO {
	
	public String cadastrarVacinaBO(Vacina vacina) throws NomeVacinaIndisponivelException, CampoInvalidoVacina {
		
		String dadosVerificados = verificarCamposVacinaBO(vacina);
		
		if (verificarCamposVacinaBO(vacina) != null) {
			throw new CampoInvalidoVacina(verificarCamposVacinaBO(vacina));
		}
		
		
		VacinaDAO vacinaDAO = new VacinaDAO();
		

		
		Vacina vacinaConsulta = vacinaDAO.ConsultarVacinaPorNomeEPais(vacina);

			
			if ( vacinaConsulta != null) {
				throw new NomeVacinaIndisponivelException("O nome da vacina "+ vacina.getNome()+ " já está cadastrado no mesmso país.");
			} else {
				
				if(vacinaDAO.cadastrarVacinaDAO(vacina)==1) {
					return "Vacina cadastrada no banco de dados com sucesso.";
				} else {
					return "Erro ao cadastrar vacina.";
				}

			}

		
	}



	public String atualizarVacinaBO(Vacina vacina) throws CampoInvalidoVacina {
		
		String dadosVerificados = verificarCamposVacinaBO(vacina);
		
		if (verificarCamposVacinaBO(vacina) != null) {
			throw new CampoInvalidoVacina(verificarCamposVacinaBO(vacina));
		}
		
		
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
			
			if (vacinaDAO.excluirVacinaDAOByNomeEPais(vacina) == 1) {
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
	
	public Vacina consultarVacinaPorNomeEPais(Vacina vacina) {
		VacinaDAO vacinaDAO = new VacinaDAO();
		
		return vacinaDAO.ConsultarVacinaPorNomeEPais(vacina);
	
		
	}
	
	private String verificarCamposVacinaBO(Vacina vacina) {
		
		String resultado = "";
		
		
		if(vacina.getNome() == null || vacina.getNome().contentEquals("")) {
			resultado += "Nome ;";
		}
		
		if(vacina.getNomePaisOrigem() ==null || vacina.getNomePaisOrigem().contentEquals("")) {
			resultado += "Pais de Origem ;";
		}
		
		if(vacina.getDataInicioPesquisa() ==null) {
			resultado += "Data de início da pesquisa ;";
		}
		
		if(vacina.getPesquisadorResponsavel() ==null || vacina.getPesquisadorResponsavel().getCpf() == null) {
			resultado += "Pesquisador Responsável ;";
		}
		
		if(vacina.getEstagioVacina() ==null ) {
			resultado += "Estágio da vacina ;";
		}
		
		if (resultado != "") {
			return "Faltaram os campos:\n" + resultado.substring(0, resultado.length()-2) +".";
		} else return null;
	}


	// TODO ORGANIZAR OVERFLOW
	public List<Vacina> consultarTodasVacinasBO(SeletorVacina pesquisa) {
		VacinaDAO_seletor vacinaDAO = new VacinaDAO_seletor();
		return vacinaDAO.pesquisarListaVacinas(pesquisa);
	}

}
