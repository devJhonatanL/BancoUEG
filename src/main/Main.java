package main;

import sistema.EscritorLeitor;
import sistema.SistemaDeAutenticacao;
import usuariosistema.AdminFull;
import usuariosistema.Bancario;
import usuariosistema.Correntista;
import usuariosistema.Gerente;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        SistemaDeAutenticacao sistemaDeAutenticacao = new SistemaDeAutenticacao();
        EscritorLeitor.carregarUsuarios();
        EscritorLeitor.carregarContas();



        sistemaDeAutenticacao.login();

        switch (SistemaDeAutenticacao.getNivelAcesso()) {
            case "admin":
                AdminFull admFull2 = new AdminFull(SistemaDeAutenticacao.getUsuario(), SistemaDeAutenticacao.getSenha());
                admFull2.menuAdm();
                
                break;
            case "gerente":
                Gerente gerente = new Gerente(SistemaDeAutenticacao.getUsuario(), SistemaDeAutenticacao.getSenha());
                gerente.menuGerente();
                break;
            case "bancario":
                Bancario bancario = new Bancario(SistemaDeAutenticacao.getUsuario(), SistemaDeAutenticacao.getSenha());
                bancario.menuBancario();
                break;
            case "correntista":
                Correntista correntista = new Correntista((SistemaDeAutenticacao.getUsuario()), SistemaDeAutenticacao.getSenha());
                correntista.menuCorrentista();
            }

    }
}
