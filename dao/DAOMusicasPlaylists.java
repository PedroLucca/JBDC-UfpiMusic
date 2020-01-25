package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import excecoes.*;


public class DAOMusicasPlaylists extends DAOBase{
	
	public void adicionarMusicaPlaylist(String idUsu, String nomeLista, String nomeAutor, String nomeMusica)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		pesquisarMusica(nomeMusica);
		pesquisarUsuario(idUsu);
		pesquisarPlaylist(nomeLista,idUsu);
		PlaylistCountains(nomeLista,nomeMusica);
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into musicasplaylists (usuario, playlist, musica, artista) values (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setString(1, idUsu);
				ps.setString(2, nomeLista);
				ps.setString(3, nomeMusica);
				ps.setString(4, nomeAutor);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public void excluirMusicasPlaylists() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from musicasplaylists";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirMusicasUmaPlaylist(String play) {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from musicasplaylists where playlist = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, play);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
				

}
