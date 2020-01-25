package ufpimusic;
import java.util.ArrayList;
import java.lang.NullPointerException;

public class Banda extends Usuario {
	ArrayList<Artista> banda = new ArrayList<Artista>();
	public Banda(String string, String string2, String string3, String string4, ArrayList<Artista> artistas){
		try {
			if(artistas.size()==0)
				throw new NullPointerException();
			this.Identificador=string;
			this.Nome=string2;
			this.Email=string3;
			this.Senha=string4;
			for(Artista a: artistas){
				banda.add(a);
			}
		}catch(NullPointerException e) {
			this.Identificador= null;
		}
	}
	
	public String getNome() {
		return this.Nome;
	}
	
	public ArrayList<Artista> getBanda() {
		return this.banda;
	}
}
