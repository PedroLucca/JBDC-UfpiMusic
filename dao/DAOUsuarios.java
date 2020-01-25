package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import excecoes.*;
import ufpimusic.*;

public class DAOUsuarios extends DAOBase {
	DAOPlaylists play = new DAOPlaylists();
	DAOMusicasPlaylists playmusic = new DAOMusicasPlaylists();
	
	public void cadastrarUsuario(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado {
		isInvalido(usuario.getNome());// Apenas verifica se uma String é valida
		isInvalido(usuario.getEmail());
		isInvalido(usuario.getIdentificador());
		isInvalido(usuario.getSenha());
		int tipo = 0;
		try {
			tipo = pesquisarUsuario(usuario.getIdentificador());
			throw new UsuarioJaCadastrado();
		} catch (UsuarioNaoCadastrado e) {
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into usuarios (identificador, nome, email, senha, idbanda, tipo) values (?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setString(1, usuario.getIdentificador());
				ps.setString(2, usuario.getNome());// Diz que a exceção é gera aqui na linha 25
				ps.setString(3, usuario.getEmail());
				ps.setString(4, usuario.getSenha());
				if ((Classificar(usuario) != 3)||(tipo != 4))
					ps.setString(5, "");
				else
					ps.setString(5, usuario.getIdentificador());
				ps.setInt(6, Classificar(usuario));
				ps.execute();
			} catch (SQLException a) {
				a.printStackTrace();
		}
		}
	}

	public ArrayList<String> todosUsuarios() {
		ArrayList<String> usus = new ArrayList<String>();
		try {
			Connection con = Conexao.getConexao();
			String cmd = "select * from usuarios";
			PreparedStatement ps = con.prepareStatement(cmd);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usus.add(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usus;
	}
	
public void VerificarLogin(Login v) throws UsuarioNaoCadastrado {
	try {
		Connection con = Conexao.getConexao();
		String cmd = "select * from usuarios where identificador = ? and senha = ?";
		PreparedStatement ps = con.prepareStatement(cmd);
		ps.setString(1, v.getUsuario());
		ps.setString(2, v.getsenha());
		ResultSet rs = ps.executeQuery();
		 if (rs.next()) { 
		 } else {
                throw new UsuarioNaoCadastrado();
            }
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public void juntarUsuarios(String idUsu1, String idUsu2, String idUsu3) throws UsuarioNaoCadastrado{
		pesquisarUsuario(idUsu1);
		pesquisarUsuario(idUsu2);
		pesquisarUsuario(idUsu3);
		try {
			Connection con = Conexao.getConexao();
			String cmd = "update musicasplaylists set usuario = ? where usuario = ? or usuario = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu3);
			ps.setString(2, idUsu1);
			ps.setString(3, idUsu2);
			ps.execute();
			String cmd2 = "update playlists set usuario = ? where usuario = ? or usuario = ?";
			PreparedStatement ps2 = con.prepareStatement(cmd2);
			ps2.setString(1, idUsu3);
			ps2.setString(2, idUsu1);
			ps2.setString(3, idUsu2);
			ps2.execute();
			String cmd3 = "update musicas set artista = ? where artista = ? or artista = ?";
			PreparedStatement ps3 = con.prepareStatement(cmd3);
			ps3.setString(1, idUsu3);
			ps3.setString(2, idUsu1);
			ps3.setString(3, idUsu2);
			ps3.execute();
			excluirUmUsuario(idUsu1);
			excluirUmUsuario(idUsu2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void excluirUsuarios() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from usuarios";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirUmUsuario(String idUsu) {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from usuarios where identificador = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, idUsu);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
