package ufpimusic;
import java.util.ArrayList;

public abstract class Usuario {
	String Identificador;
    String Nome;
    String Email;
    String Senha;
    ArrayList<Playlist> biblioteca = new ArrayList<Playlist>();
    
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		Nome = nome;
	}
	
	public String getIdentificador() {
		return Identificador;
	}
	
	public void setIdentificador(String identificador) {
		Identificador = identificador;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public String getSenha() {
		return Senha;
	}
	
	public void setSenha(String senha) {
		Senha = senha;
	}

	public void setPlaylist(Playlist playlist) {
		biblioteca.add(playlist);
	}

}
