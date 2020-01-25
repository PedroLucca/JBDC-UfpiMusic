package ufpimusic;
import java.util.ArrayList;
import java.sql.Date;

import excecoes.*;
import dao.*;

public class UFPIMusic implements InterfaceStreaming{
	DAOMusicas acervo = new DAOMusicas();
	DAOEstilos estilos = new DAOEstilos();
	DAOUsuarios usuarios = new DAOUsuarios();
	DAOPlaylists playlists =  new DAOPlaylists();
	DAOMusicasPlaylists mplay = new DAOMusicasPlaylists();
	
	
	@Override
	public void cadastrarEstilo(String nome) throws ValorInvalido, EstiloJaCadastrado {
		estilos.cadastrarEstilo(nome);
	}

	@Override
	public ArrayList<Musica> pesquisarPorEstilo(String nome){
		ArrayList<Musica> aux = new ArrayList<Musica>();
		aux = acervo.pesquisarPorEstilo(nome);
		return aux;
	}

	@Override
	public ArrayList<Musica> pesquisarPorData(Date inicial){
		ArrayList<Musica> aux = new ArrayList<Musica>();
		aux = acervo.pesquisarPorData(inicial);
		return aux;
	}

	@Override
	public ArrayList<Musica> pesquisarPorArtista(String nome){
		ArrayList<Musica> aux = new ArrayList<Musica>();
		aux = acervo.pesquisarPorArtista(nome);
		return aux;
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) throws ValorInvalido, UsuarioJaCadastrado {
		usuarios.cadastrarUsuario(usuario);
	}

	@Override
	public void adicionarMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) throws ValorInvalido, UsuarioNaoCadastrado, MusicaJaCadastrada, EstiloNaoCadastrado {
			acervo.adicionarMusica(idUsu, nomeMusica, estilo, link, duracao, lancamento);
			
	}

	@Override
	public void criarPlaylist(String idUsu, String nomeLista) throws ValorInvalido, UsuarioNaoCadastrado, PlaylistExistente {
			playlists.criarPlaylist(idUsu, nomeLista);
	}

	@Override
	public void adicionarMusicaPlaylist(String idUsu, String nomeLista, String nomeAutor, String nomeMusica)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente, MusicaNaoCadastrada, MusicaJaCadastrada {
		mplay.adicionarMusicaPlaylist(idUsu, nomeLista, nomeAutor, nomeMusica);
	}

	@Override
	public Playlist pesquisaPlaylistUsuario(String idUsu, String nomeLista)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		Playlist kk = new Playlist();
		playlists.pesquisaPlaylistUsuario(idUsu, nomeLista);
		return kk;
	}

	@Override
	public ArrayList<Playlist> pesquisaPlaylistEstilo(String idUsu, String estilo)
			throws UsuarioNaoCadastrado, PlaylistNaoExistente {
		ArrayList<Playlist> aux =  new ArrayList<Playlist>();
		
		return aux;
	}
	
	@Override
	public void removerEstilo(String estilo) throws EstiloNaoCadastrado{
		estilos.removerEstilo(estilo);
	}
	
	@Override
	public void juntarUsuarios(String idUsu1, String idUsu2, String idUsu3) throws UsuarioNaoCadastrado{
		usuarios.juntarUsuarios(idUsu1, idUsu2, idUsu3);
	}

}
