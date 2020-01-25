package excecoes;
public class EstiloNaoCadastrado extends Exception {
	public EstiloNaoCadastrado() {
		super("ERRO: Estilo não cadastrado!\n");
	}
}
