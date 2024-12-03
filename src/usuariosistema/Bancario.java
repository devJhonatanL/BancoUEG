package usuariosistema;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;

import java.io.IOException;
import java.util.Scanner;

public class Bancario extends Usuario{
    public Bancario(String usuario, String senha) {
        super(usuario, senha, "bancario");
        }

        //REALIZA DEPOSITO NA CONTA DO CORRENTISTA
        public void moviDeposito() throws IOException {
        double valor;
        String contaAlvo;
        Scanner input = new Scanner(System.in);
        System.out.println("Informe o numero da conta");
        contaAlvo = input.nextLine();
        Conta contaEncontrada = EscritorLeitor.getContas().get(contaAlvo.toUpperCase());
        if (contaEncontrada == null) {
            System.out.println("Conta inexistente");
        }
        Conta conta = (Conta) contaEncontrada;
        System.out.println("Informe o valor desejado");
        valor = input.nextDouble();

        conta.depositar(valor);
        }
        //SACA DA CONTA DO CORRENTISTA
        public void moviSaque() throws IOException {
            double valor;
            String contaAlvo;
            Scanner input = new Scanner(System.in);
            System.out.println("Informe o numero da conta");
            contaAlvo = input.nextLine();
            Conta contaEncontrada = EscritorLeitor.getContas().get(contaAlvo.toUpperCase());
            if (contaEncontrada == null) {
                System.out.println("Conta inexistente");
                return;
            }
            System.out.println("Informe o valor desejado");
            valor = input.nextDouble();
            switch (contaEncontrada.getTipoConta()){
                case "poupanca":
                    ContaPoupanca contaPoupanca = (ContaPoupanca) contaEncontrada;
                    contaPoupanca.sacar(valor);
                    break;
                case "corrente":
                    ContaCorrente contaCorrente = (ContaCorrente) contaEncontrada;
                    contaCorrente.sacar(valor);
                    break;
                case "adicional":
                    ContaAdicional contaAdicional = (ContaAdicional) contaEncontrada;
                    contaAdicional.sacar(valor);
                    break;
            }


        }
        /*TRANSFERE DA CONTA DE UM CORRENTISTA PARA OUTRO CORRENSTISTA - OBS: SO DA PRA TRANSFERIR SE A CONTA QUE
        VAI TRANSFERIR É CORRENTE E A QUE VAI RECEBER NÃO É DO TIPO ADICIONAL*/

        public void transferencia() throws IOException {
            double valor;
            String contaAlvo;
            Scanner input = new Scanner(System.in);
            System.out.println("Informe o numero da conta que vai transferir");
            contaAlvo = input.nextLine();
            Conta contaEncontrada = EscritorLeitor.getContas().get(contaAlvo.toUpperCase());
            if (contaEncontrada == null) {
                System.out.println("Conta inexistente");
                return;
            }
            System.out.println("Informe o valor desejado");
            valor = input.nextDouble();
            input.nextLine();
            String contaRecebe;

            System.out.println("Informe o numero da conta a receber");
            contaRecebe = input.nextLine();

            Conta contaRecEncontrada = EscritorLeitor.getContas().get(contaRecebe);
            if (contaRecEncontrada == null) {
                System.out.println("Conta inexistente");
                return;
            }
            if(contaRecEncontrada.getTipoConta() == "adicional"){
                System.out.println("Essa conta não pode receber uma transferencia");
                return;
            }
            Conta conta = (Conta) contaRecEncontrada;
            switch (contaEncontrada.getTipoConta()) {

                case "poupanca":
                    System.out.println("Conta poupança não tem autorização para fazer transferencia");
                    break;
                case "corrente":
                    if(contaEncontrada.getSaldo()<valor) {
                        System.out.println("Saldo insuficiente");
                        return;
                    }else {
                        ContaCorrente contaCorrente = (ContaCorrente) contaEncontrada;
                        contaCorrente.sacar(valor);
                        conta.depositar(valor);
                    }
                    break;
                case "adicional":
                    System.out.println("Conta poupança não tem autorização para fazer transferencia");
                    break;
            }
        }
        //MENU DO BANCARIO


         public  void menuBancario() throws IOException {
        Scanner input = new Scanner(System.in);
        int opcao;

             System.out.println("===========================");
             System.out.println("+         Operações       +");
             System.out.println("+ 1 - Deposito            +");
             System.out.println("+ 2 - Saque               +");
             System.out.println("+ 3 - Trasnferencia       +");
             System.out.println("+ 4 - Ver Rendimento      +");
             System.out.println("===========================");

        opcao = input.nextInt();
        switch (opcao) {
            case 1:
                moviDeposito();
                break;
            case 2:
                moviSaque();
                break;
            case 3:
                transferencia();
            case 5:
                System.out.println("Sistema finalizado com sucesso");
                break;
        }
    }

}

