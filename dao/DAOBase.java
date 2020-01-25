package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excecoes.*;
import ufpimusic.*;

public abstract class DAOBase {
	
	public void isInvalido(String nome) throws ValorInvalido{
		try {
			if((nome.equals(""))||(nome == null))
				throw new ValorInvalido();
		}catch(java.lang.NullPointerException e) {
			throw new ValorInvalido();
		}
		
	}
	
	public void isInvalidoB(ArrayList<Artista> nome) throws ValorInvalido{
		try {
			if(nome == null)
				throw new ValorInvalido();
		}catch(java.lang.NullPointerException e) {
			throw new ValorInvalido();
		}
		
	}
	
	public void isArtista(String usuario) throws UsuarioNaoCadastrado{
		if(pesquisarUsuario(usuario)==1){
			throw new UsuarioNaoCadastrado();
		}
	}
	
	public void PlaylistCountains(String playlist, String nomeMusica) throws MusicaJaCadastrada{
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from musicasplaylists where usuario = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, nomeMusica);
			ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
	                if(rs.getString("playlist")==playlist)
	                	throw new MusicaJaCadastrada();
			 }
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	
	public int Classificar(Usuario usuario){
		if(usuario instanceof Artista)
			return 2;
		else if(usuario instanceof Banda)
			return 3;
		else
			return 1;
	}
	
	public String pesquisarEstilo(String estilo) throws EstiloNaoCadastrado {
		String estiloaux = null;
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from estilos where nome = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, estilo);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
	                estiloaux = rs.getString("nome");
	            } else {
	                throw new EstiloNaoCadastrado();
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estiloaux;
	}
	
	public Musica pesquisarMusica(String musica) throws MusicaNaoCadastrada {
		Musica musicaaux = new Musica();
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from musicas where nome = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, musica);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 musicaaux.setMusica(rs.getString("artista"), rs.getString("nome"), rs.getString("estilo"), rs.getString("link"), rs.getInt("duracao"), rs.getDate("lancamento"));
	            } else {
	                throw new MusicaNaoCadastrada();
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return musicaaux;
	}
	
	public int pesquisarUsuario(String idUsu) throws UsuarioNaoCadastrado {
		int usuaux = 0;
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from usuarios where identificador = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
				 if(rs.getString("identificador") == rs.getString("idbanda")) {
					 usuaux = 4;
				 }else if(rs.getString("identificador") != rs.getString("idbanda")) {
					 usuaux = rs.getInt("tipo");
	            } 
			 } else {
	                throw new UsuarioNaoCadastrado();
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuaux;
	}
	
	
	public String pesquisarPlaylist(String nome, String usuario) throws PlaylistNaoExistente {
		String playaux = null;
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from playlists where usuario = ? and nome = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, usuario);
			ps.setString(2, nome);
			ResultSet rs = ps.executeQuery();
			 if (rs.next()) {
	                playaux = rs.getString("nome");
	            } else {
	                throw new PlaylistNaoExistente();
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return playaux;
	}
	
	public void limparBanco() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from estilos";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
			String cmd2 = "delete from musicas";
			PreparedStatement ps2 = con.prepareStatement(cmd2);
			ps2.execute();
			String cmd3 = "delete from musicasplaylists";
			PreparedStatement ps3 = con.prepareStatement(cmd3);
			ps3.execute();
			String cmd4 = "delete from playlists";
			PreparedStatement ps4 = con.prepareStatement(cmd4);
			ps4.execute();
			String cmd5 = "delete from usuarios";
			PreparedStatement ps5 = con.prepareStatement(cmd5);
			ps5.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
