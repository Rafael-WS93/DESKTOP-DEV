package br.sc.senac.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.AvaliacaoAplicacao;


public class AvaliacaoAplicacaoDAO {
	
	
public int cadastrarAvaliacaoAplicacao(AvaliacaoAplicacao avaliacaoAplicacao) {
		
		int resultadoInt = 0;
		
		String sql = "INSERT INTO AVALIACAO_APLICACAO(idaplicacao , nota ,descricao , DT_AVALIACAO) values (?,?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			
			stmt = this.prepararStatementCadastro(stmt, avaliacaoAplicacao);
			
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
	
	public boolean atualizarAvaliacaoAplicacao(AvaliacaoAplicacao avaliacaoAplicacao) {
		
		boolean resultadoBool = false;
		
		String sql = "UPDATE AVALIACAO_APLICACAO SET idAplicacao= ? ,nota= ? ,descricao= ? ,DT_AVALIACAO= ?  where idAvaliacao_aplicacao = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {

			stmt = this.prepararStatementCadastro(stmt, avaliacaoAplicacao);
			
			stmt.setString(5, String.valueOf(avaliacaoAplicacao.getIdAvaliacaoAplicacao()));
			
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
	
	public AvaliacaoAplicacao consultarAvaliacaoAplicacaoPorId(Integer id) {
		
		String sql = "SELECT idAVALIACAO_APLICACAO ,idaplicacao , nota ,descricao , dt_avaliacao FROM Avaliacao_APLICACAO WHERE idAVALIACAO_APLICACAO= ? ;";	
		
	
		AvaliacaoAplicacao avaliacaoAplicacao = new AvaliacaoAplicacao();
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
			
		ResultSet rs = null;
		try {
			
			
			stmt.setString(1, id.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				avaliacaoAplicacao = this.converterRStoObj(rs);


				
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
		
		return avaliacaoAplicacao;


	}
	
public List<AvaliacaoAplicacao> consultarTodasAvaliacoes() {
		
		String sql = "SELECT idAVALIACAO_APLICACAO ,idaplicacao , nota ,descricao , dt_avaliacao FROM Avaliacao_APLICACAO;";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		ResultSet rs = null;
		
		List<AvaliacaoAplicacao> listaAvaliacoes = new ArrayList<AvaliacaoAplicacao>();
		try {	
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
		
				listaAvaliacoes.add(this.converterRStoObj(rs));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaAvaliacoes;
		
		
	}
	
	public int excluirAvaliacaoAplicacao(int id) {
		int resultadoInt = 0;
		
		String sql = "DELETE FROM AVALIACAO_APLICACAO WHERE IDAVALIACAO_APLICACAO= ?;";
		
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
	
	
	
	
	private PreparedStatement prepararStatementCadastro(PreparedStatement stmt, AvaliacaoAplicacao avaliacaoAplicacao) throws SQLException {
		
		stmt.setString(1 , String.valueOf(avaliacaoAplicacao.getAplicacao().getIdAplicacao()));
		
		stmt.setString(2 , String.valueOf(avaliacaoAplicacao.getNota()));
		
		stmt.setString(3 , String.valueOf(avaliacaoAplicacao.getDescricao()));			
		
		stmt.setString(4 , String.valueOf(avaliacaoAplicacao.getDataAvaliacao()));
		
		
		return stmt;
	}
	
	private AvaliacaoAplicacao converterRStoObj(ResultSet rs) throws SQLException {
		AvaliacaoAplicacao avaliacaoAplicacao = new AvaliacaoAplicacao();
		AplicacaoDAO aplicacaoVacinaDAO = new AplicacaoDAO();
		
		avaliacaoAplicacao.setAplicacao( aplicacaoVacinaDAO.consultarAplicacaoVacinaPorId( rs.getInt("idAVALIACAO_APLICACAO") ) );
		
		avaliacaoAplicacao.setNota(rs.getInt("nota"));

		avaliacaoAplicacao.setDescricao(rs.getString("descricao"));
		
		avaliacaoAplicacao.setDataAvaliacao(LocalDate.parse(rs.getString("dt_avaliacao")));
		
		avaliacaoAplicacao.setIdAvaliacaoAplicacao(rs.getInt("idAVALIACAO_APLICACAO"));
		
		
		
		
		return avaliacaoAplicacao;
		
	}
	
	
	
	

}
