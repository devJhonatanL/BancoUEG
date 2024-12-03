package contasusuarios;

import sistema.EscritorLeitor;

import java.io.IOException;

public class ContaAdicional extends Conta {
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

    //SACAR DA CONTA ADICIONAL NÃO USA SALDO. ELA USA O LIMITE. (NESSE CASO LIMITE = SALDO)
    @Override
    public void sacar(double valor) throws IOException {
        if (valor <= limite) {
            limite -= valor;
            System.out.println("***.");
            setLimite(limite);
            Conta conta = EscritorLeitor.getContas().get(this.getNumeroConta());
            EscritorLeitor.adicionarContas(conta);
        } else {
            System.out.println("Erro, verifique o saldo total e o valor solicitado");
        }

    }
}
