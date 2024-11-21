package usuariosistema;

public class Correntista extends Usuario {
    private String contaCorrente;
    private String contaPoupança;
    private String contaCorrenteAdicional;

    public Correntista(String usuario, String senha, String contaCorrente, String contaPoupança, String contaCorrenteAdicional) {
        super(usuario, senha, "correntista");

        this.contaCorrente = contaCorrente;
        this.contaPoupança = contaPoupança;
        this.contaCorrenteAdicional = contaCorrenteAdicional;

    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public String getContaPoupança() {
        return contaPoupança;
    }

    public String getContaCorrenteAdicional() {
        return contaCorrenteAdicional;
    }

    @Override
    public String toString() {
        return "Usuário: " + this.getUsuario() + " - " + this.getSenha() + " [" + this.getNivelacesso() + "]"
                + " Corrente:" + this.contaCorrente + " Poupança:" + this.contaPoupança + " Corrente Adicional:" + this.contaCorrenteAdicional;
    }
}

