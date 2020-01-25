package excecoes;
public class EstiloJaCadastrado extends Exception {
	public EstiloJaCadastrado() {
		super("ERRO: Estilo ja cadastrado!\n");
	}
}
