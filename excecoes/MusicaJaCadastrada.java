package excecoes;
public class MusicaJaCadastrada extends Exception {
	public MusicaJaCadastrada() {
		super("ERRO: Música ja cadastrado!\n");
	}
}
