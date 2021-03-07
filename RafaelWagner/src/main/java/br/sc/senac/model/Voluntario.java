package br.sc.senac.model;

import java.time.LocalDate;

public class Voluntario extends Pessoa{

	public Voluntario() {
		super();
	}

	public Voluntario(String nome, String cpf, LocalDate dataNascimento, SexoPessoa sexo, CategoriaPessoa categoria) {
		super(nome, cpf, dataNascimento, sexo, categoria);
	}
	
	

}
