package contasusuarios;

import java.io.IOException;

public  abstract class Conta {
    private double saldo;
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

    public double getSaldo() {
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
    public void deposito(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito realizado com sucesso!");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    public abstract void sacar(double valor) throws IOException;

    @Override
    public String toString() {
        return "Conta: " + this.getNumeroConta() + " - " + this.tipoConta + " [" + this.titular + "]" + " - " + this.saldo + " - " + this.senha;
    }
}

