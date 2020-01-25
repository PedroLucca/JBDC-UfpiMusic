package excecoes;
public class UsuarioJaCadastrado extends Exception {
	public UsuarioJaCadastrado() {
		super("ERRO: Usuario já existente!\n");
	}
}
