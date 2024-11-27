package contasusuarios;

import sistema.EscritorLeitor;

import java.io.IOException;

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
    public  void sacar(double valor) throws IOException {
            if(valor< limite) {
                limite -=valor;
                System.out.println("Saque realizado.");
                setLimite(limite);
                Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
                EscritorLeitor.adicionarContas(conta);
            } else {
                System.out.println("Saque invalido, verifique o saldo total e o valor solicitado");
            }

        }



    @Override
    public String toString() {
        return super.toString()+" [limite=" + limite + "]";
    }

}
