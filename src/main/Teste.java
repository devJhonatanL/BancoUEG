package main;

import usuariosistema.*;
import sistema.EscritorLeitor;
import sistema.SistemaDeAutenticacao;

import java.io.IOException;

public class Teste {
    public static void main(String[] args) throws IOException {
        boolean funcionamento = true;

        EscritorLeitor.carregarUsuarios();
        EscritorLeitor.carregarContas();

        SistemaDeAutenticacao sistemaDeAutenticacao = new SistemaDeAutenticacao();

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