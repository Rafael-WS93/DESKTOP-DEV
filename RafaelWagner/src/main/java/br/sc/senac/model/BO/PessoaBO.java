package br.sc.senac.model.BO;

import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.model.DAO.PessoaDAO;
import br.sc.senac.model.vo.Pessoa;

public class PessoaBO {
	
	public String cadastrarPessoaBO(Pessoa pessoa) throws CpfIndisponivelException{
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		if (pessoaDAO.verificarPessoaPorCpfDAO(pessoa.getCpf())) {
			throw new CpfIndisponivelException("Erro, CPF já Cadastrado.");
		} else {
			
			if (pessoaDAO.cadastrarPessoaDAO(pessoa) == 1) {
				return "Pessoa cadastada com sucesso.";
			} else {
				return "Erro ao cadastrar nova pessoa.";
			}
			
		}

	}
	
	public String atualizarPessoaBO(Pessoa pessoa) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		if (pessoaDAO.atualizarVacinaDAO(pessoa) == 1) {
			return "Atualização realizada com sucesso.";
		} else {
			return "Erro na Atualização.";
		}
	}
	
	public String excluirPessoaBO(int id) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		if (pessoaDAO.excluirPessoaDAO(id) == 1) {
			return "Pessoa excluida com sucesso.";
		} else {
			return "Erro ao excluir.";
		}
	}

}
