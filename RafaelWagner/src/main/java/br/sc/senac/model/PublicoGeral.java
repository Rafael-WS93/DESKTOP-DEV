package br.sc.senac.model;

import java.time.LocalDate;

public class PublicoGeral extends Pessoa{

	public PublicoGeral() {
		super();
	}

	public PublicoGeral(String nome, String cpf, LocalDate dataNascimento, SexoPessoa sexo, CategoriaPessoa categoria) {
		super(nome, cpf, dataNascimento, sexo, categoria);
	}

	

}
