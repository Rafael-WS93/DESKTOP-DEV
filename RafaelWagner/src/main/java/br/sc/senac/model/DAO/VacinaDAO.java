package br.sc.senac.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Vacina;

public class VacinaDAO {
	
	public Vacina vacinaCadastrar(Vacina vacina){
		
		String sql = "INSERT INTO VACINA(nome ,PESQUISADOR ,inicio_pesquisa ,estagio) values (?,?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , vacina.getNome());
			stmt.setString(2 , vacina.getPesquisadorResponsavel());
			stmt.setString(3 , vacina.getDataInicioPesquisa().toString());
			stmt.setString(4 , vacina.getEstagioVacina().name());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			
			try {
				stmt.close();
				conn.close();
				System.out.println("fechou conn");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		return vacina;
	}
	
	public boolean atualizarVacina(Vacina vacina) {
		boolean resultadoBool = false;
		
		String sql = "UPDATE VACINA SET nome= ? ,pesquisador= ? ,inicio_pesquisa= ? ,estagio= ? where idvacina = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , vacina.getNome());
			stmt.setString(2 , vacina.getPesquisadorResponsavel());
			stmt.setString(3 , vacina.getDataInicioPesquisa().toString());
			stmt.setString(4 , vacina.getEstagioVacina().name());
			
			stmt.setString(5, String.valueOf(vacina.getIdVacina()));
			
			stmt.executeUpdate();
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
	
	public Vacina consultaVacinaPorId(int id) {
		Vacina vacina = new Vacina();
		String sql = "SELECT idvacina ,nome ,PESQUISADOR ,inicio_pesquisa ,estagio FROM VACINA WHERE IDVACINA= ? ;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		ResultSet rs = null;
		try {
			stmt.setString(1, String.valueOf(id));
			
			stmt.executeQuery();
			
			if(rs.next()) {
				vacina.setIdVacina(rs.getInt(1));
				vacina.setNome(rs.getString(2));
				vacina.setPesquisadorResponsavel(rs.getString(3));
				vacina.setDataInicioPesquisa(LocalDate.parse(rs.getString(4)));
				vacina.setEstagioVacina(EstagioVacina.valueOf(rs.getString(5)));
				
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
		
		
		return vacina;
	}
	
	public List<Vacina> consultarTodasVacinas() {
		
		String sql = "SELECT idvacina ,nome ,PESQUISADOR ,inicio_pesquisa ,estagio FROM VACINA;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<Vacina> listaVacinas = new ArrayList<Vacina>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Vacina vacina = new Vacina();
				vacina.setIdVacina(rs.getInt(1));
				vacina.setNome(rs.getString(2));
				vacina.setPesquisadorResponsavel(rs.getString(3));
				vacina.setDataInicioPesquisa(LocalDate.parse(rs.getString(4)));
				vacina.setEstagioVacina(EstagioVacina.valueOf(rs.getString(5)));
				
				listaVacinas.add(vacina);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaVacinas;
		
		
	}
	
	public boolean excluirVacina(int id) {
		boolean resultado = false;
		
		String sql = "DELETE FROM VACINA WHERE IDVACINA= ?;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1, String.valueOf(id));
			
			stmt.executeUpdate();
			
			resultado = true;
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
		
		return resultado;
	}
	

}
