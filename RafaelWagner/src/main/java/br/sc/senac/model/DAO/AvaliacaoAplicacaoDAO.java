package br.sc.senac.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.sc.senac.model.vo.AvaliacaoAplicacao;


public class AvaliacaoAplicacaoDAO {
	
public int cadastrarAvaliacaoAplicacao(AvaliacaoAplicacao avalicacaoAplicacao) {
		
		int resultadoInt = 0;
		
		String sql = "INSERT INTO AVALIACAO_APLICACAO(idaplicacao , nota ,descricao , DT_AVALIACAO) values (?,?,?,?);";
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {
			stmt.setString(1 , String.valueOf(avalicacaoAplicacao.getAplicacao().getIdAplicacao()));
			
			stmt.setString(2 , String.valueOf(avalicacaoAplicacao.getNota()));
			
			stmt.setString(3 , String.valueOf(avalicacaoAplicacao.getDescricao()));			
			
			stmt.setString(4 , String.valueOf(avalicacaoAplicacao.getDataAvaliacao()));
			
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
	
	public boolean atualizarAvaliacaoAplicacao(AvaliacaoAplicacao avalicacaoAplicacao) {
		
		boolean resultadoBool = false;
		
		String sql = "UPDATE AVALIACAO_APLICACAO SET idAplicacao= ? ,nota= ? ,descricao= ? ,DT_AVALIACAO= ?  where idAvaliacao_aplicacao = ?;";
		
		
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);
		
		try {

			stmt.setString(1 , String.valueOf(avalicacaoAplicacao.getAplicacao().getIdAplicacao()));
			
			stmt.setString(2 , String.valueOf(avalicacaoAplicacao.getNota()));
			
			stmt.setString(3 , String.valueOf(avalicacaoAplicacao.getDescricao()));			
			
			stmt.setString(4 , String.valueOf(avalicacaoAplicacao.getDataAvaliacao()));
			
			stmt.setString(5, String.valueOf(avalicacaoAplicacao.getIdAvaliacaoAplicacao()));
			
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
				
				AplicacaoDAO aplicacaoVacinaDAO = new AplicacaoDAO();
				
				avaliacaoAplicacao.setAplicacao( aplicacaoVacinaDAO.consultarAplicacaoVacinaPorId( rs.getInt(2) ) );
				
				avaliacaoAplicacao.setNota(rs.getInt(3));

				avaliacaoAplicacao.setDescricao(rs.getString(4));
				
				avaliacaoAplicacao.setDataAvaliacao(LocalDate.parse(rs.getString(5)));


				
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
				
				AvaliacaoAplicacao avaliacaoAplicacao = new AvaliacaoAplicacao();
				
				AplicacaoDAO aplicacaoVacinaDAO = new AplicacaoDAO();
				avaliacaoAplicacao.setAplicacao( aplicacaoVacinaDAO.consultarAplicacaoVacinaPorId( rs.getInt(2) ) );
				
				avaliacaoAplicacao.setNota(rs.getInt(3));

				avaliacaoAplicacao.setDescricao(rs.getString(4));
				
				avaliacaoAplicacao.setDataAvaliacao(LocalDate.parse(rs.getString(5)));


				
				listaAvaliacoes.add(avaliacaoAplicacao);
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
	
	
	
	

}
