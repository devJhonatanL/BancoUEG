package usuariosistema;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;
import sistema.SistemaDeAutenticacao;

import java.io.IOException;
import java.util.Scanner;

public class Correntista extends Usuario {
    private String contaCorrente;
    private String contaPoupança;
    private String contaCorrenteAdicional;

    public Correntista(String usuario, String senha) {
        super(usuario, senha, "correntista");


    }

    public void setContaCorrenteAdicional(String contaCorrenteAdicional) {
        this.contaCorrenteAdicional = contaCorrenteAdicional;
    }

    public void setContaPoupança(String contaPoupança) {
        this.contaPoupança = contaPoupança;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public String getContaCorrente() {
        return contaCorrente;
    }

    public String getContaPoupança() {
        return contaPoupança;
    }

    public String getContaCorrenteAdicional() {
        return contaCorrenteAdicional;
    }

    public void menuCorrentista() throws IOException {
        Scanner input = new Scanner(System.in);
        String contaEscolhida;
        String donoConta;
        double valor;
        int opcao;
        boolean menuON = true;
        boolean submenuOn = true;


        donoConta = SistemaDeAutenticacao.getUsuario();

            System.out.println("Informe o numero da conta a ser usada:");
            contaEscolhida = input.nextLine();
            Conta contaEncontrada = EscritorLeitor.getContas().get(contaEscolhida.toUpperCase());
            if (contaEncontrada == null) {
                System.out.println("Conta nao encontrada.");
                return;
            }

            if (!contaEncontrada.getTitular().equals(donoConta)) {
                System.out.println("Conta não vinculada ao usuario");
                return;
            }

            switch (contaEncontrada.getTipoConta()) {
                case "poupanca":
                    System.out.println("Você optou por uma conta poupança /n");


                        System.out.println("===========================");
                        System.out.println("+         Operações       +");
                        System.out.println("+ 1 - Saque               +");
                        System.out.println("+ 2 - Deposito            +");
                        System.out.println("+ 3 - Redimentos          +");
                        System.out.println("+ 4 - Trocar conta        +");
                        System.out.println("===========================");

                        opcao = input.nextInt();
                        ContaPoupanca contaPoupanca = (ContaPoupanca) contaEncontrada;


                        switch (opcao) {
                            case 1:
                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                contaPoupanca.sacar(valor);
                                break;
                            case 2:
                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                contaPoupanca.depositar(valor);
                                break;
                            case 3:
                                double saldo = contaEncontrada.getSaldo();
                                System.out.println("Digite um tempo (meses) : ");
                                int tempo = input.nextInt();
                                contaPoupanca.calculoRedimentos(saldo, tempo);
                                break;
                            case 4:
                                submenuOn = false;
                                break;
                        }
                        break;

                case "adicional":
                    System.out.println("Você optou por uma adicional /n");

                        System.out.println("===========================");
                        System.out.println("+         Operações       +");
                        System.out.println("+ 1 - Saque               +");
                        System.out.println("+ 2 - Trocar conta        +");
                        System.out.println("===========================");

                        opcao = input.nextInt();

                        ContaAdicional contaAdicional = (ContaAdicional) contaEncontrada;

                        switch (opcao) {
                            case 1:
                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                contaAdicional.sacar(valor);
                                break;
                        }
                        break;

                case "corrente": {
                    System.out.println("Você optou por uma corrente");


                        System.out.println("===========================");
                        System.out.println("+         Operações       +");
                        System.out.println("+ 1 - Saque               +");
                        System.out.println("+ 2 - Deposito            +");
                        System.out.println("+ 3 - Trasnferencia       +");
                        System.out.println("+ 4 - Trocar conta        +");
                        System.out.println("===========================");

                        opcao = input.nextInt();
                        ContaCorrente contaCorrente = (ContaCorrente) contaEncontrada;


                        switch (opcao) {
                            case 1:
                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                contaCorrente.sacar(valor);
                                break;
                            case 2:

                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                contaCorrente.depositar(valor);
                                break;
                            case 3:
                                System.out.println("Digite um valor: ");
                                valor = input.nextDouble();
                                input.nextLine();

                                System.out.println("Informe o numero da conta a receber");
                                String contaRecebe = input.nextLine();
                                Conta contaRecEncontrada = EscritorLeitor.getContas().get(contaRecebe);
                                if (contaRecEncontrada == null) {
                                    System.out.println("Conta inexistente");
                                    return;
                                }
                                if (contaRecEncontrada.getTipoConta() == "adicional") {
                                    System.out.println("Essa conta não pode receber uma transferencia");
                                    return;
                                }
                                Conta conta = (Conta) contaRecEncontrada;
                                if (contaEncontrada.getSaldo() < valor) {
                                    System.out.println("Saldo insuficiente");
                                    return;
                                } else {
                                    contaCorrente.sacar(valor);
                                    conta.depositar(valor);
                                }
                                break;
                        }


                }
            }

    }
}



