package contasusuarios;

import sistema.EscritorLeitor;

import java.io.IOException;

public class ContaPoupanca extends Conta {
    private double rendimentos;
    double taxaDeRedimento = 3.8;
    public ContaPoupanca(double saldo, String titular, String numeroConta, String senha) {
        super(saldo, titular, numeroConta, senha, "poupanca");
    }

    @Override
    public  void sacar(double valor) throws IOException {
        double saldo = getSaldo();

        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            setSaldo(saldo);
            Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
            EscritorLeitor.adicionarContas(conta);
            System.out.println("***.");
        } else {
            System.out.println("Erro, verifique o saldo total e o valor solicitado");
        }

    }

    public double calculoRedimentos(double saldo, int tempo) {

        rendimentos = getSaldo() * Math.pow(1 + (taxaDeRedimento / 100), tempo);
        System.out.printf("Redimentos da conta poupança: R$ %.2f em um total de %d meses. ", rendimentos, tempo);
        return rendimentos;
    }
}

