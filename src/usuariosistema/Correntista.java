package usuariosistema;

import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;

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

    



    public void menuCorrentista() {

    }
}


