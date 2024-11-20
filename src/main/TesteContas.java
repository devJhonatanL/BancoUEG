package main;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;

import java.io.IOException;

public class TesteContas {
    public static void main(String[] args) throws IOException {
        EscritorLeitor.carregarContas();
        System.out.println(EscritorLeitor.getContas());
    }
}
