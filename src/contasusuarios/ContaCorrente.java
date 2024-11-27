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
            System.out.println("Saque realizado.");
        } else {
            System.out.println("Saque invalido, verifique o saldo total e o valor solicitado");
        }
    }

    public void transferir(double valor, Conta destino) throws IOException {
        double saldo = getSaldo();
        if (valor > 0 && valor < (saldo + chequeEspecial)) {
            saldo -= valor;
            setSaldo(saldo);
            Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
            EscritorLeitor.adicionarContas(conta);
            System.out.println("Transferencia realizada.");
        }
    }
}



    /*public void alterarLimite() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o numero da conta");
        String numeroConta = input.nextLine();
        Conta contaEncontrada = EscritorLeitor.getContas().get(numeroConta);
        if (contaDestino == null) {
            System.out.println("conta não encontrado");
            return;
        }

        ContaCorrente contaCorrente = (contaCorrente) contaDestino;

        System.out.println("Informe o novo valor do limite:");
        double limite = input.nextDouble();
        input.nextLine();
        if(saldo > 0){
            contaAdicional.setLimite(limite);
            EscritorLeitor.adicionarContas(contaAdicional);
            System.out.println("Limite atualizado com sucesso");
        }else{
            System.out.println("Valor invalido Tente Novamente");
            alterarLimite();
        } */









