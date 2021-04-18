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
	
	public int cadastrarVacinaDAO (Vacina vacina){
		int resultadoInt = 0;
		
		String sql = "INSERT INTO VACINA(nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem) values (?,?,?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			
			stmt = this.prepararStatementCadastro(stmt, vacina);
			
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
	
	public int atualizarVacinaDAO(Vacina vacina) {
		int resultadoInt = 0;
		
		String sql = "UPDATE VACINA SET nome= ? ,IDPESQUISADOR= ? ,inicio_pesquisa= ? ,estagio= ? ,nome_pais_origem=? where idvacina = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		
		try {
			
			stmt = this.prepararStatementCadastro(stmt, vacina);
			
			stmt.setString(6, String.valueOf(vacina.getIdVacina()));
			
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
	
	public Vacina consultaVacinaPorIdDAO(Integer id) {
		
		String sql = "SELECT idvacina ,nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem FROM VACINA WHERE IDVACINA= ? ;";
		
	
		Vacina vacina = new Vacina();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, id.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				vacina= this.converterRsToVacina(rs);
				
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
	
	public List<Vacina> consultarTodasVacinasDAO() {
		
		String sql = "SELECT idvacina ,nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem FROM VACINA;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<Vacina> listaVacinas = new ArrayList<Vacina>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				listaVacinas.add(this.converterRsToVacina(rs));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaVacinas;
		
		
	}
	
	public int excluirVacinaDAOById(int id) {
		int resultadoInt = 0;
		
		String sql = "DELETE FROM VACINA WHERE IDVACINA= ?;";
		
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
	
	public int excluirVacinaDAOByNomeEPais(Vacina vacina) {
		int resultadoInt = 0;
		
		String sql = "DELETE FROM VACINA WHERE UPPER(nome) = UPPER(?) AND UPPER(nome_pais_origem) = UPPER(?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1, vacina.getNome());
			stmt.setString(2, vacina.getNomePaisOrigem());
			
			
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

	public Vacina ConsultarVacinaPorNomeDAO(String nome) {
		String sql = "SELECT idvacina ,nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem FROM VACINA WHERE nome= ? ;";
		
		
		Vacina vacina = new Vacina();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, nome);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				vacina = converterRsToVacina(rs);
				
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
	
	public Vacina ConsultarVacinaPorNomeEPais(Vacina vacina) {
		String sql = "SELECT idvacina ,nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem FROM VACINA WHERE UPPER(nome) = UPPER(?) AND UPPER(nome_pais_origem) = UPPER(?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, vacina.getNome());
			stmt.setString(2, vacina.getNomePaisOrigem());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				vacina = converterRsToVacina(rs);
				
			} else {
				vacina = null;
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
	
	private PreparedStatement prepararStatementCadastro(PreparedStatement stmt, Vacina vacina) throws SQLException {
		
		stmt.setString(1 , vacina.getNome());
		stmt.setString(2 , String.valueOf(vacina.getPesquisadorResponsavel().getIdPessoa()));
		stmt.setString(3 , vacina.getDataInicioPesquisa().toString());
		stmt.setString(4 , vacina.getEstagioVacina().name());
		stmt.setString(5, vacina.getNomePaisOrigem());
		
		return stmt;
	}
	
	private Vacina converterRsToVacina (ResultSet rs) throws SQLException {
		
		Vacina vacina = new Vacina();
		vacina.setIdVacina(rs.getInt("idvacina"));
		vacina.setNome(rs.getString("nome"));
		
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		vacina.setPesquisadorResponsavel(pessoaDAO.consultarPessoaPorIdDAO(rs.getInt("IDPESQUISADOR")));
		
		vacina.setDataInicioPesquisa(LocalDate.parse(rs.getString("inicio_pesquisa")));
		vacina.setEstagioVacina(EstagioVacina.valueOf(rs.getString("estagio")));
		vacina.setNomePaisOrigem(rs.getString("nome_pais_origem"));
		
		return vacina;
	}




}
