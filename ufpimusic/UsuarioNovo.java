package ufpimusic;

import java.util.ArrayList;

public class UsuarioNovo {
	private String Identificador;
    private String Nome;
    private String Email;
    private String Senha;
    private ArrayList<Playlist> biblioteca = new ArrayList<Playlist>();
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
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public ArrayList<Playlist> getBiblioteca() {
		return biblioteca;
	}
	public void setBiblioteca(ArrayList<Playlist> biblioteca) {
		this.biblioteca = biblioteca;
	}
}
