package contasusuarios;

import sistema.EscritorLeitor;

import java.io.IOException;
import java.util.Scanner;

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
    public  void sacar(double valor) throws IOException {
        double saldo = getSaldo();
        if (valor > 0 && valor < (saldo + chequeEspecial)) {
            saldo -= valor;
            setSaldo(saldo);
            Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
            EscritorLeitor.adicionarContas(conta);
            System.out.println("***.");
        } else {
            System.out.println("Erro, verifique o saldo total e o valor solicitado");
        }
    }

}












