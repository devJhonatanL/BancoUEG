package sistema;
import usuariosistema.Usuario;
import java.util.Scanner;

public class SistemaDeAutenticacao {
    private static String usuario;
    private static String senha;
    private static String nivelAcesso;


    public void login() {

        Scanner input = new Scanner(System.in);
        boolean autenticado = false;


        do {

            System.out.println("\n==========================");
            System.out.println("=--       LOGIN        --=");
            System.out.println("==========================");
            System.out.println("Digite o nome do usuario: ");
            System.out.println("==========================");
            String usuario = input.nextLine();
            Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);

            if (usuarioEncontrado == null) {

            System.out.println("\nUsuario não encontrado!");
            continue;
            }
            System.out.println("\n==========================");
            System.out.println("Digite o senha do usuario:");
            System.out.println("==========================");
            String senha = input.nextLine();
            if (!usuarioEncontrado.getSenha().equals(senha)) {
            System.out.println("\nSenha incorreta");
        } else {
                System.out.println("\n===========================");
                System.out.println("Login efetuado com sucesso!");
                System.out.println("===========================");
                this.usuario = usuarioEncontrado.getUsuario();
                this.senha = usuarioEncontrado.getSenha();
                this.nivelAcesso = usuarioEncontrado.getNivelacesso();
                autenticado = true;
        }
        }while(autenticado == false);
    }
    public static String getUsuario() {
        return usuario;
    }
    public static String getSenha() {
        return senha;
    }
    public static String getNivelAcesso() {
        return nivelAcesso;
    }
}
