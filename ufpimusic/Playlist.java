package ufpimusic;
import java.util.ArrayList;

public class Playlist {
	private String Nome;
	ArrayList<String> estilos = new ArrayList<String>();
	private long Duracao = 0;
	private ArrayList<Musica> playlist = new ArrayList<Musica>();
	
	public ArrayList<Musica> getMusicas() {
		return this.playlist;
	}
	
	public ArrayList<String> getEstilos() {
		return this.estilos;
	}

	public void addEstilo(String estilo) {
		estilos.add(estilo);
	}

	public long getDuracaoTotal() {
		return this.Duracao;
	}

	public void setDuracao(long duracao) {
		this.Duracao = duracao;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public void addMusica(Musica aux) {
		this.playlist.add(aux);
		this.Duracao = this.Duracao + aux.getTempoD();
		}
	
	public void setPlaylist(Playlist aux) {
		for(Musica a: aux.playlist) {
			for(String b: aux.estilos) {
				this.estilos.add(b);
			}
			this.addMusica(a);;
		}
		this.setDuracao(aux.Duracao);
		this.setNome(aux.Nome);
	}
}
