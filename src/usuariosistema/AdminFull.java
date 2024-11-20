package usuariosistema;

import sistema.EscritorLeitor;

import java.io.IOException;
import java.util.Scanner;

public class AdminFull extends Usuario {
    public AdminFull(String usuario, String senha) {
        super(usuario, senha, "admin");
    }


    public void criarGerente() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario: ");
        String usuario = input.nextLine();

        Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);

        if (usuarioEncontrado != null) {
            System.out.println("Já existe esse usuario.");
            return;
        }

        System.out.println("Digite a Senha: ");
        String senha = input.nextLine();

        Gerente novoGerente = new Gerente(usuario, senha);
        EscritorLeitor.adicionarUsuario(novoGerente);
    }
    public void menuAdm() throws IOException {
        Scanner input = new Scanner(System.in);
        int opcao;

        System.out.println("""
                Escolha a opção desejada:
                [1] Criar gerente
           
                """);
        opcao = input.nextInt();
        switch (opcao) {
            case 1:
                criarGerente();
                break;

        }
    }
}
