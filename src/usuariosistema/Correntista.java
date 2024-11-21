package usuariosistema;

public class Correntista extends Usuario {
    private String contaCorrente;
    private String contaPoupança;
    private String contaCorrenteAdicional;

    public Correntista(String usuario, String senha) {
        super(usuario, senha, "correntista");


    }

    public void setContaCorrenteAdicional(String contaCorrenteAdicional) {
        this.contaCorrenteAdicional = contaCorrenteAdicional;
    }

    public void setContaPoupança(String contaPoupança) {
        this.contaPoupança = contaPoupança;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
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

