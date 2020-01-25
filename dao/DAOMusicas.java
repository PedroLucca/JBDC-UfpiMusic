package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import excecoes.*;
import ufpimusic.Musica;



public class DAOMusicas extends DAOBase{
	
	public void adicionarMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
			isInvalido(idUsu);
			isInvalido(nomeMusica);
			isInvalido(estilo);
			isInvalido(link);
			pesquisarEstilo(estilo);//Verifica estilo
			pesquisarUsuario(idUsu);//Verifica usuario
			isArtista(idUsu);//Verifica se é artista ou banda
			try {
				if(pesquisarMusica(nomeMusica)!=null) throw new MusicaJaCadastrada();
			} catch (MusicaNaoCadastrada e) {
				try {
					Connection con = Conexao.getConexao();
					String cmd = "insert into musicas (nome, artista, lancamento, duracao, link, estilo) values (?,?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(cmd);
					ps.setString(1, nomeMusica);
					ps.setString(2, idUsu);
					ps.setDouble(3, lancamento.getTime());
					ps.setInt(4, duracao);
					ps.setString(5, link);
					ps.setString(6, estilo);
					ps.execute();
				} catch (SQLException a) {
					a.printStackTrace();
				}
			}
		}
	
	
	public ArrayList<Musica> pesquisarPorEstilo(String nome){
		ArrayList<Musica> estilosaux = new ArrayList<Musica>();
		try {
			 Connection con = Conexao.getConexao();
			 String cmd = "select * from musicas where estilo = ?";
			 PreparedStatement ps = con.prepareStatement(cmd);
			 ps.setString(1, nome);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				 Musica aux = new Musica();
				aux.setMusica(rs.getString("artista"), rs.getString("nome"), rs.getString("estilo"), rs.getString("link"), rs.getInt("duracao"), rs.getDate("lancamento"));
				 estilosaux.add(aux);
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estilosaux;
	}
	
	public ArrayList<Musica> pesquisarPorData(Date inicial){
		ArrayList<Musica> dataaux = new ArrayList<Musica>();
		try {
			 Connection con = Conexao.getConexao();
			 String cmd = "select * from musicas where lancamento = ?";
			 PreparedStatement ps = con.prepareStatement(cmd);
			 ps.setDouble(1, inicial.getTime());
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				 Musica aux = new Musica();
				 aux.setMusica(rs.getString("artista"), rs.getString("nome"), rs.getString("estilo"), rs.getString("link"), rs.getInt("duracao"), rs.getDate("lancamento"));
				 dataaux.add(aux);
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataaux;
	}
	
	public ArrayList<Musica> pesquisarPorArtista(String nome){
		ArrayList<Musica> estilosaux = new ArrayList<Musica>();
		try {
			 Connection con = Conexao.getConexao();
			 String cmd = "select * from musicas where artista = ?";
			 PreparedStatement ps = con.prepareStatement(cmd);
			 ps.setString(1, nome);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				 Musica aux = new Musica();
				 aux.setMusica(rs.getString("artista"), rs.getString("nome"), rs.getString("estilo"), rs.getString("link"), rs.getInt("duracao"), rs.getDate("lancamento"));
				 estilosaux.add(aux);
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estilosaux;
	}
	
	public ArrayList<String> acervo(){
		ArrayList<String> acervo = new ArrayList<String>();
		try {
			 Connection con = Conexao.getConexao();
			 String cmd = "select * from musicas";
			 PreparedStatement ps = con.prepareStatement(cmd);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
	                acervo.add(rs.getString("nome"));
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acervo;
	}
	
	public void excluirAcervo() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from musicas";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
