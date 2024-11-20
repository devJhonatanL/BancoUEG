package contasusuarios;

public class ContaAdicional extends Conta{
    private double limite;
    public ContaAdicional(double saldo, String titular, String numeroConta, String senha) {
        super(saldo, titular, numeroConta, senha, "adicional");
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public void sacar(double valor) {

    }

    @Override
    public String toString() {
        return super.toString()+" [limite=" + limite + "]";
    }
}
