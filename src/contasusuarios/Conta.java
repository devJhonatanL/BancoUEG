package contasusuarios;

import sistema.EscritorLeitor;

import java.io.IOException;

public  abstract class Conta {
    private  double saldo;
    private String titular;
    private String numeroConta;
    private String senha;
    private String tipoConta;


    public Conta(double saldo, String titular, String numeroConta, String senha, String tipoConta) {
        this.saldo = saldo;
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.senha = senha;
        this.tipoConta = tipoConta;
    }

    public  double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    //FORMATAÇÃO PARA MOSTRAR AS CONTAS
    @Override
    public String toString() {
        return " " + "usuario "+ " " + this.titular+ ", "+"tipo de conta= " + this.getTipoConta() + "]\n";
    }


    //SACAR É ABSTRATO E CADA CLASSE FAZ DE SEU MODO UNICO
    public abstract void sacar(double valor) throws IOException;

    //DEPOSITAR É UNIVERSAL PARA TODAS AS CONTAS, ATE PORQUE SO TEM UMA FORMA DE DEPOSITAR
    public  void depositar(double valor) throws IOException {
        double saldo = getSaldo();

        if (valor > 0 ) {
            saldo += valor;
            setSaldo(saldo);
            Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
            EscritorLeitor.adicionarContas(conta);
            System.out.println("**");
        } else {
            System.out.println("Erro, verifique o saldo total e o valor solicitado");
        }

    }

}

