package excecoes;
public class PlaylistNaoExistente extends Exception {
	public PlaylistNaoExistente(){
		super("ERRO: Playlist não existente!\n");
	}
}
