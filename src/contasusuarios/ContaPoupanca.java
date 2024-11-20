package contasusuarios;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(double saldo, String titular, String numeroConta, String senha) {
        super(saldo, titular, numeroConta, senha, "poupanca");
    }

    @Override
    public void sacar(double valor) {

    }
}
