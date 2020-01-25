package excecoes;
public class UsuarioNaoCadastrado extends Exception {
	public UsuarioNaoCadastrado() {
		super("ERRO: Usuario não existente!\n");
	}
}
