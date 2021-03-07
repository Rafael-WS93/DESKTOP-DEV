package br.sc.senac.model;

import java.time.LocalDate;

public class Pesquisador extends Pessoa{
	
	private String LocalDeTrabalho;

	public Pesquisador() {
		super();
	}


	public Pesquisador(String nome, String cpf, LocalDate dataNascimento, SexoPessoa sexo, CategoriaPessoa categoria,
			String localDeTrabalho) {
		super(nome, cpf, dataNascimento, sexo, categoria);
		LocalDeTrabalho = localDeTrabalho;
	}


	public String getLocalDeTrabalho() {
		return LocalDeTrabalho;
	}


	public void setLocalDeTrabalho(String localDeTrabalho) {
		LocalDeTrabalho = localDeTrabalho;
	}
	
	


	
	
	
	

}
