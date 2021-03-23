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
	
	public int cadastrarPessoaDAO(Pessoa pessoa) {
		
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
	
	public int atualizarVacinaDAO(Pessoa pessoa) {
		
		int resultadoInt = 0;
		
		String sql = "UPDATE PESSOA SET nome= ? ,cpf= ? ,nascimento= ? ,sexo= ? where idpessoa = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , pessoa.getNome());
			stmt.setString(2 , pessoa.getCpf());
			stmt.setString(3 , pessoa.getDataNascimento().toString());
			stmt.setString(4 , pessoa.getSexo().name());
			
			stmt.setString(5, String.valueOf(pessoa.getIdPessoa()));
			
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
	
	public Pessoa consultarPessoaPorIdDAO(Integer id) {
		
		String sql = "SELECT idpessoa ,nome ,cpf ,nascimento ,sexo FROM PESSOA WHERE IDPESSOA= ? ;";
		
	
		Pessoa pessoa = new Pessoa();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, id.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				pessoa.setIdPessoa(rs.getInt("idpessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setDataNascimento(LocalDate.parse(rs.getString("nascimento")));
				pessoa.setSexo(SexoPessoa.valueOf(rs.getString("sexo")));
				
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
	
public List<Pessoa> consultarTodasPessoasDAO() {
		
		String sql = "SELECT idpessoa ,nome ,cpf ,nascimento ,sexo FROM PESSOA;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(rs.getInt("idpessoa"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setDataNascimento(LocalDate.parse(rs.getString("nascimento")));
				pessoa.setSexo(SexoPessoa.valueOf(rs.getString("sexo")));
				
				listaPessoas.add(pessoa);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaPessoas;
		
		
	}
	
	public int excluirPessoaDAO(int id) {
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

	public boolean verificarPessoaPorCpfDAO(String cpf) {
		boolean resultadoBool = true;
		
		String sql = "SELECT idpessoa ,nome ,cpf ,nascimento ,sexo FROM PESSOA WHERE cpf= ? ;";
		
		
		Pessoa pessoa = new Pessoa();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, cpf);
			
			rs = stmt.executeQuery();
			
			resultadoBool = rs.next();
			
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
	
	
	
	
	

}
