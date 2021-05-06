package br.sc.senac.model.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.seletor.SeletorVacina;
import br.sc.senac.model.vo.EstagioVacina;
import br.sc.senac.model.vo.Vacina;

public class VacinaDAO_seletor {
	
	private static final String QUERY_LISTA = "SELECT idvacina ,nome ,IDPESQUISADOR ,inicio_pesquisa ,estagio ,nome_pais_origem FROM VACINA ";
	
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
	


	public List<Vacina> pesquisarListaVacinas (SeletorVacina vacina) {
		String query = QUERY_LISTA;
		String filtros = vacina.filtro();
		

		query += pesquisarComFiltro(vacina, filtros);

		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
		ResultSet rs = null;
		
		List<Vacina> listaVacinas = new ArrayList<Vacina>();
		try {
			rs = stmt.executeQuery();
			
	
			
			while (rs.next()) {

				listaVacinas.add(converterRsToVacina(rs));
				
			}
			return listaVacinas;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return listaVacinas;
		
	}
	
	private String pesquisarComFiltro(SeletorVacina vacina, String filtros) {
		String consulta = "";
		String and = " AND ";
		
		if (!filtros.isEmpty()) {
			consulta = "WHERE ";
			
			if (filtros.contains("ID") && filtros.length() == 1) {
				consulta += "ID = " + vacina.getIdVacina() + and;
			} else {
				if (filtros.contains("NOME")) {
					consulta += "NOME = '" + vacina.getNome()	 + "'" + and;
				}
				
				if (filtros.contains("PAIS")) {
					consulta += "nome_pais_origem = '" + vacina.getNomePaisOrigem() + "'" + and;
				}
				
				if (filtros.contains("ESTAGIO")) {
					consulta += "ESTAGIO = '" + vacina.getEstagioVacina().toString() + "'" + and;
				}
				
				// TODO CONSULTA PESQUISADOR
//				if (filtros.contains("PESQUISADOR")) {
//					consulta += "IDPESQUISADOR = " + vacina.getPesquisadorResponsavel().getIdPessoa()  + and;
//				}
				
				if (filtros.contains("DATA_INICIO")) {
					consulta += "inicio_pesquisa >= '" + vacina.getDataInicioPesquisa() + "'" + and;
				}
				
				if (filtros.contains("DATA_LIMITE")) {
					consulta += "inicio_pesquisa <= '" + vacina.getDataLimite() + "'" + and;
				}
			}
			
			
		}
	
		consulta += "LIMIT " + vacina.getLimiteOffset() + "," + 10 + "    ";

		
		return consulta.substring(0, consulta.length() - 4);
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
