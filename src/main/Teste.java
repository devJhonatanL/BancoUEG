package main;

import usuariosistema.*;
import sistema.EscritorLeitor;
import sistema.SistemaDeAutenticacao;

import java.io.IOException;

public class Teste {
    public static void main(String[] args) throws IOException {
        boolean funcionamento = true;

        EscritorLeitor.carregarUsuarios();

        SistemaDeAutenticacao sistemaDeAutenticacao = new SistemaDeAutenticacao();

        sistemaDeAutenticacao.login();

        while (funcionamento) {

            switch (SistemaDeAutenticacao.getNivelAcesso()) {
                case "admin":
                    AdminFull admFull2 = new AdminFull(SistemaDeAutenticacao.getUsuario(), SistemaDeAutenticacao.getSenha());
                    admFull2.menuAdm();
                    break;
                case "gerente":
                    Gerente gerente = new Gerente(SistemaDeAutenticacao.getUsuario(), SistemaDeAutenticacao.getSenha());
                    gerente.menuGerente();
                    break;
                default:
                    System.out.println("Acesso não reconhecido.");
                    funcionamento = false;

            }
        }
    }
}