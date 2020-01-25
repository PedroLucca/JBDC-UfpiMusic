package excecoes;
public class MusicaNaoCadastrada extends Exception {
	public MusicaNaoCadastrada() {
		super("ERRO: Música não cadastrada!\n");
	}
}
