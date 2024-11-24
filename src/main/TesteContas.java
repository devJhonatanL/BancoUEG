package main;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;

import java.io.IOException;

public class TesteContas {
    public static void main(String[] args) throws IOException {
        ContaPoupanca teste = new ContaPoupanca(900.0,"zezin", "9999a","1234");
        teste.calculoRedimentos(900.0,12);

    }
}
