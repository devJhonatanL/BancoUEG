package contasusuarios;

public class ContaCorrente extends Conta {
    private double chequeEspecial;

    public ContaCorrente(double saldo, String titular, String numeroConta, String senha) {
        super(saldo, titular, numeroConta, senha, "corrente");
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    @Override
    public void sacar(double valor) {

    }

    @Override
    public String toString() {
        return super.toString() + " [ cheque especial =" + chequeEspecial + "]";
    }
}
