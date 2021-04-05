package br.sc.senac.controller;

import br.sc.senac.exception.CpfIndisponivelException;
import br.sc.senac.model.BO.PessoaBO;
import br.sc.senac.model.vo.Pessoa;

public class PessoaController {
	
	public String cadastrarPessoaController(Pessoa pessoa) throws CpfIndisponivelException {
		
		PessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.cadastrarPessoaBO(pessoa);

	}
	
	public Pessoa consultarPessoaPorCPFController(String cpf) {
		PessoaBO pessoaBO = new PessoaBO();
		
		return pessoaBO.consultarPessoarPorCpfBO(cpf);
		
	}

}
