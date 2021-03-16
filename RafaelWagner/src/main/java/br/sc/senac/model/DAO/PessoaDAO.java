package br.sc.senac.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.Pessoa;
import br.sc.senac.model.vo.SexoPessoa;


public class PessoaDAO {
	
	public int cadastrarPessoa(Pessoa pessoa) {
		
		int resultadoInt = 0;
		
		String sql = "INSERT INTO Pessoa(nome ,cpf ,nascimento ,sexo) values (?,?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , pessoa.getNome());
			stmt.setString(2 , pessoa.getCpf());
			stmt.setString(3 , pessoa.getDataNascimento().toString());
			stmt.setString(4 , pessoa.getSexo().name());
			
			resultadoInt = stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		return resultadoInt;
		
	}
	
	public boolean atualizarVacina(Pessoa pessoa) {
		
		boolean resultadoBool = false;
		
		String sql = "UPDATE PESSOA SET nome= ? ,cpf= ? ,nascimento= ? ,sexo= ? where idpessoa = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , pessoa.getNome());
			stmt.setString(2 , pessoa.getCpf());
			stmt.setString(3 , pessoa.getDataNascimento().toString());
			stmt.setString(4 , pessoa.getSexo().name());
			
			stmt.setString(5, String.valueOf(pessoa.getIdPessoa()));
			
			stmt.executeUpdate();
			
			resultadoBool = true; 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return resultadoBool;
		
		
		
	}
	
	public Pessoa consultarPessoaPorId(Integer id) {
		
		String sql = "SELECT idpessoa ,nome ,cpf ,nascimento ,sexo FROM PESSOA WHERE IDPESSOA= ? ;";
		
	
		Pessoa pessoa = new Pessoa();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, id.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				pessoa.setIdPessoa(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setCpf(rs.getString(3));
				pessoa.setDataNascimento(LocalDate.parse(rs.getString(4)));
				pessoa.setSexo(SexoPessoa.valueOf(rs.getString(5)));
				
			} else {
				System.out.println("Ocorrencia não existe");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return pessoa;


	}
	
public List<Pessoa> consultarTodasPessoas() {
		
		String sql = "SELECT idpessoa ,nome ,cpf ,nascimento ,sexo FROM PESSOA;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(rs.getInt(1));
				pessoa.setNome(rs.getString(2));
				pessoa.setCpf(rs.getString(3));
				pessoa.setDataNascimento(LocalDate.parse(rs.getString(4)));
				pessoa.setSexo(SexoPessoa.valueOf(rs.getString(5)));
				
				listaPessoas.add(pessoa);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPessoas;
		
		
	}
	
	public int excluirPessoa(int id) {
		int resultadoInt = 0;
		
		String sql = "DELETE FROM PESSOA WHERE IDPESSOA= ?;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1, String.valueOf(id));
			
			resultadoInt = stmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return resultadoInt;
	}
	
	
	
	
	

}
