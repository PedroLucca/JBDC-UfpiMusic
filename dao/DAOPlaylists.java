package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excecoes.*;
import ufpimusic.*;

public class DAOPlaylists extends DAOBase {

	public void criarPlaylist(String idUsu, String nomeLista)
			throws ValorInvalido, UsuarioNaoCadastrado, PlaylistExistente {
		isInvalido(idUsu);
		isInvalido(nomeLista);
		pesquisarUsuario(idUsu);
		try {
			pesquisarPlaylist(nomeLista,idUsu);
			throw new PlaylistExistente();
		} catch (PlaylistNaoExistente e) {
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into playlists (usuario, nome) values (?,?)";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setString(1, idUsu);
				ps.setString(2, nomeLista);
				ps.execute();
			} catch (SQLException a) {
				a.printStackTrace();
			}
		}
	}

	public Playlist pesquisaPlaylistUsuario(String idUsu, String nomeLista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		Playlist aux = new Playlist();
		pesquisarUsuario(idUsu);
		pesquisarPlaylist(nomeLista,idUsu);
		try {
			try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from musicasplaylists where usuario = ? and playlist = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu);
			ps.setString(2, nomeLista);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aux.setNome(rs.getString("playlist"));
				aux.addMusica(pesquisarMusica(rs.getString("musica")));
			}
			} catch (MusicaNaoCadastrada e) {
						e.printStackTrace();
						}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	public ArrayList<Playlist> pesquisarPlaylistsUsuario(String idUsu)
			throws UsuarioNaoCadastrado {
		Playlist aux = new Playlist();
		ArrayList<Playlist> aux2 = new ArrayList<Playlist> ();
		pesquisarUsuario(idUsu);
		try {
			try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from musicasplaylists where usuario = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				aux.setNome(rs.getString("playlist"));
				aux.addMusica(pesquisarMusica(rs.getString("musica")));
				aux2.add(aux);
			}
			} catch (MusicaNaoCadastrada e) {
						e.printStackTrace();
						}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux2;
	}
	
	public ArrayList<String> pesquisarPlay(String idUsu)
			throws UsuarioNaoCadastrado {
		ArrayList<String> aux2 = new ArrayList<String> ();
		pesquisarUsuario(idUsu);
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from playlists where usuario = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				aux2.add(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aux2;
	}

	public void excluirPlaylists() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from playlists";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirUmaPlaylist(String nome) {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from playlists where nome = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, nome);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarUmaMusica(String musica) throws MusicaNaoCadastrada{
		this.pesquisarMusica(musica);
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from musicasplaylists where musica = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, musica);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
