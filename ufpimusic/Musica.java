package ufpimusic;
import java.util.Date;
public class Musica {
	private String Nome;
	private String artista;
	private Date Lancamento = new Date();
	private int TempoD;
	private String Estilo;
	private String Link;
	
	public String getNome() {
		return Nome;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public String getEstilo() {
		return Estilo;
	}
	
	public String getLink() {
		return Link;
	}
	
	public  java.util.Date getLancamento() {
		return Lancamento;
	}
	
	public int getTempoD() {
		return TempoD;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public void setArtista(String artistaaux) {
		artista = artistaaux;
	}
	
	public void setEstilo(String estilo) {
		Estilo = estilo;
	}
	
	public void setLink(String link) {
		Link = link;
	}
	
	public void setLancamento(Date lancamento) {
		Lancamento.setTime(lancamento.getTime());
	}
	
	public void setTempoD(int tempo) {
		TempoD = tempo;
	}
	
	public void setMusica(String idUsu, String nomeMusica, String estilo, String link, int duracao,
			Date lancamento) {
		this.setNome(nomeMusica);
		this.setEstilo(estilo);
		this.setLancamento(lancamento);
		this.setLink(link);
		this.setTempoD(duracao);
		this.setArtista(idUsu);
	}

}
