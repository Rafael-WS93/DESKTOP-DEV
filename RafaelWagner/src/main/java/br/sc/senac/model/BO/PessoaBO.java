package br.sc.senac.model.BO;

import java.util.List;

import br.sc.senac.exception.CamposInvalidosPessoa;
import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.model.DAO.PessoaDAO;
import br.sc.senac.model.vo.Pessoa;

public class PessoaBO {
	
	public String cadastrarPessoaBO(Pessoa pessoa) throws CpfIndisponivelException, CamposInvalidosPessoa{
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		String dadosVerificados = verificarDadosPessoaBO(pessoa);
		
		if (dadosVerificados != null) {
			throw new CamposInvalidosPessoa(dadosVerificados);
		}
		
		if (pessoaDAO.consultarPessoaPorCpfDAO(pessoa.getCpf()).getIdPessoa() != 0) {
			throw new CpfIndisponivelException("O CPF já está sendo utilizado.");
		} else {
			
			if (pessoaDAO.cadastrarPessoaDAO(pessoa) == 1) {
				
				return "Pessoa cadastada com sucesso.";
			} else {
				return "Erro ao cadastrar nova pessoa.";
			}
			
		}

	}
	


	public String atualizarPessoaBO(Pessoa pessoa) throws CpfIndisponivelException{
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		Pessoa verificacaoPessoa = pessoaDAO.consultarPessoaPorCpfDAO(pessoa.getCpf());
		
		if (verificacaoPessoa != null && pessoa.getIdPessoa() != verificacaoPessoa.getIdPessoa()) {
			throw new CpfIndisponivelException("Erro, CPF já Cadastrado.");
		} else { 
			
			if (pessoaDAO.atualizarPessoaDAO(pessoa) == 1) {
				return "Atualização realizada com sucesso.";
			} else {
				return "Erro na Atualização.";
			}
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
	
	public Pessoa consultarPessoaPorIdBO(int id) {
		PessoaDAO pessoaDAO = new PessoaDAO();

		return pessoaDAO.consultarPessoaPorIdDAO(id);

		
	}
	
	public List<Pessoa> consultarTodasPessoasBO() {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		return pessoaDAO.consultarTodasPessoasDAO();
	}
	
	public Pessoa consultarPessoarPorCpfBO(String cpf) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		Pessoa pessoaConsulta = pessoaDAO.consultarPessoaPorCpfDAO(cpf);
		
		if(pessoaConsulta.getNome() != null) {
			return pessoaConsulta;
		} else {
			return null;
		}
		
		
		
	}
	
	private String verificarDadosPessoaBO(Pessoa pessoa) {
		
		String resultado = "";
		
		if(pessoa.getNome() == null || pessoa.getNome().equals("") ) {
			resultado += "Nome ;";
		}
		
		if (pessoa.getCpf() == null || !pessoa.getCpf().matches("^[1-9]{11}")) {
			resultado += "CPF ;";
		}
		
		if (pessoa.getCategoria() == null) {
			resultado += "Categoria ;";
		}
		
		if (pessoa.getSexo() == null) {
			resultado += "Sexo ;";
		}
		
		if (resultado.equals("")) {
			return null;
		} else {
			return "Restaram os campos:\n" + resultado.substring(0, resultado.length()-2) + ".";
		}

		

	}

}
