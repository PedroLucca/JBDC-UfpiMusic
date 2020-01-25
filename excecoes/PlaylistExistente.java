package excecoes;
public class PlaylistExistente extends Exception {
	public PlaylistExistente() {
		super("ERRO: Playlist já existente!\n");
	}
}
