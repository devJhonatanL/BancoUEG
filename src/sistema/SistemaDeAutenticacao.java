package sistema;
import usuariosistema.Usuario;
import java.util.Scanner;

public class SistemaDeAutenticacao {
    private static String usuario;
    private static String senha;
    private static String nivelAcesso;


    public void login() {

        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario: ");
        String usuario = input.nextLine();
        Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);
        if (usuarioEncontrado == null) {
            System.out.println("Usuario não encontrado! ");
            login();

        }
        System.out.println("Digite o senha do usuario:");
        String senha = input.nextLine();
        if (!usuarioEncontrado.getSenha().equals(senha)) {
            System.out.println("Senha incorreta");
            login();
        }else {
            System.out.println("Login efetuado com sucesso!");
            this.usuario = usuarioEncontrado.getUsuario();
            this.senha = usuarioEncontrado.getSenha();
            this.nivelAcesso = usuarioEncontrado.getNivelacesso();
        }










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
