package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.Conexao;
import excecoes.*;
import ufpimusic.*;

public class DAOEstilos extends DAOBase {
	DAOMusicas musics = new DAOMusicas();
	
	public void cadastrarEstilo(String estilo) throws ValorInvalido, EstiloJaCadastrado {
		isInvalido(estilo);
		try{
			pesquisarEstilo(estilo);
			throw new EstiloJaCadastrado();
		}catch(EstiloNaoCadastrado e){
			try {
				Connection con = Conexao.getConexao();
				String cmd = "insert into estilos(nome) values (?)";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setString(1, estilo);
				ps.execute();
			} catch (SQLException a) {
				e.printStackTrace();
			}
		}
	}
	
	public void deletarEstilos() {
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from estilos";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removerEstilo(String estilo) throws EstiloNaoCadastrado{
		ArrayList<Musica> aux = new ArrayList<Musica>();
		pesquisarEstilo(estilo);
		try {
			Connection con = Conexao.getConexao();
			aux = musics.pesquisarPorEstilo(estilo);
			for(Musica a: aux) {
				String cmd = "delete from musicasplaylists where musica = ?";
				PreparedStatement ps = con.prepareStatement(cmd);
				ps.setString(1, a.getNome());
				ps.execute();
				String cmd2 = "delete from musicas where nome = ?";
				PreparedStatement ps2 = con.prepareStatement(cmd2);
				ps2.setString(1, a.getNome());
				ps2.execute();
			}
			excluirUmEstilo(estilo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirUmEstilo(String estilo) throws EstiloNaoCadastrado{
		pesquisarEstilo(estilo);
		try {
			Connection con = Conexao.getConexao();
			String cmd = "delete from estilos where nome = ?";
			PreparedStatement ps = con.prepareStatement(cmd);
			ps.setString(1, estilo);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> todosEstilos(){
		ArrayList<String> estilos = new ArrayList<String>();
		try {
			 Connection con = Conexao.getConexao();
			 String cmd = "select * from estilos";
			 PreparedStatement ps = con.prepareStatement(cmd);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
	                estilos.add(rs.getString("nome"));
	            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estilos;
	}
}
