package main;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;

import java.io.IOException;
import java.util.Scanner;

public class TesteContas {
    public static void main(String[] args) throws IOException {
        EscritorLeitor.carregarContas();
        EscritorLeitor.carregarUsuarios();


        Scanner input = new Scanner(System.in);
            /*System.out.println("Digite o numero da conta");
            String numeroConta = input.nextLine();
            Conta contaEncontrada = EscritorLeitor.getContas().get(numeroConta);
            if (contaEncontrada == null) {
                System.out.println("conta não encontrado");
                return;
            }

            ContaAdicional contaAdicional = (ContaAdicional) contaEncontrada;

            System.out.println("Informe o valor desejado:");
            double novosaldo = input.nextDouble();
            input.nextLine();
            if(novosaldo > 0){
                novosaldo += contaAdicional.getSaldo();
                contaAdicional.setSaldo(novosaldo);
                EscritorLeitor.adicionarContas(contaAdicional);
                System.out.println("Saldo atualizado com sucesso");
            }else{
                System.out.println("Valor invalido Tente Novamente");

            }*/
        System.out.println("Digite o numero da conta");
        String numeroConta = input.nextLine();
        Conta contaEncontrada = EscritorLeitor.getContas().get(numeroConta);
        if (contaEncontrada == null) {
            System.out.println("conta não encontrado");
            return;
        }

        ContaAdicional contaAdicional = (ContaAdicional) contaEncontrada;

        System.out.println("Informe o valor desejado:");
        double valor = input.nextDouble();
        input.nextLine();
        if (valor > 0) {
            double novosaldo = contaAdicional.getSaldo();
            novosaldo -= valor;
            contaAdicional.setSaldo(novosaldo);
            EscritorLeitor.adicionarContas(contaAdicional);
            System.out.println("Saldo atualizado com sucesso");
        } else {
            System.out.println("Valor invalido Tente Novamente");

        }

    }

    }

