package br.sc.senac.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.AplicacaoVacina;


public class AplicacaoDAO {
	
public int cadastrarAplicacaoVacina(AplicacaoVacina aplicacao) {
		
		int resultadoInt = 0;
		
		String sql = "INSERT INTO APLICACAO(dt_aplicacao ,idvacina ,idpessoa) values (?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(3 , String.valueOf(aplicacao.getIdPessoa()));
			stmt.setString(2 , String.valueOf(aplicacao.getIdVacina()));
			stmt.setString(1 , aplicacao.getDataAplicação().toString());
			
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
	
	public boolean atualizarAplicacao(AplicacaoVacina aplicacao) {
		
		boolean resultadoBool = false;
		
		String sql = "UPDATE APLICACAO SET dt_aplicacao= ? ,idpessoa= ? ,idvacina= ? where idaplicacao = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(2 , String.valueOf(aplicacao.getIdPessoa()));
			stmt.setString(3 , String.valueOf(aplicacao.getIdVacina()));
			stmt.setString(1 , aplicacao.getDataAplicação().toString());
			
			stmt.setString(4, String.valueOf(aplicacao.getIdAplicacao()));
			
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
	
	public AplicacaoVacina consultarAplicacaoVacinaPorId(Integer id) {
		
		String sql = "SELECT idaplicacao ,idpessoa ,idvacina ,dt_aplicacao FROM APLICACAO WHERE idAPLICACAO= ? ;";
		
	
		AplicacaoVacina aplicacao = new AplicacaoVacina();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, id.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				aplicacao.setIdAplicacao(rs.getInt(1));

				aplicacao.setIdPessoa(rs.getInt(2));

				aplicacao.setIdVacina(rs.getInt(3));
				
				aplicacao.setDataAplicação(LocalDate.parse(rs.getString(4)));

				
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
		
		return aplicacao;


	}
	
public List<AplicacaoVacina> consultarTodasAplicacacoes() {
		
		String sql = "SELECT idaplicacao ,idpessoa ,idvacina ,dt_aplicacao FROM APLICACAO;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<AplicacaoVacina> listaAplicacaoVacinas = new ArrayList<AplicacaoVacina>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				AplicacaoVacina aplicacao = new AplicacaoVacina();
				
				aplicacao.setIdAplicacao(rs.getInt(1));

				aplicacao.setIdPessoa(rs.getInt(2));

				aplicacao.setIdVacina(rs.getInt(3));
				
				aplicacao.setDataAplicação(LocalDate.parse(rs.getString(4)));
				
				listaAplicacaoVacinas.add(aplicacao);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAplicacaoVacinas;
		
		
	}
	
	public int excluirAplicacao(int id) {
		int resultadoInt = 0;
		
		String sql = "DELETE FROM APLICACAO WHERE IDAPLICACAO= ?;";
		
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
