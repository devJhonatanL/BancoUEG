package sistema;
import usuariosistema.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EscritorLeitor {

    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    private static final String arquivo = "usuarios.txt";

    //RETORNA  A LISTA
    public static HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    //SALVA O USUARIO NA LISTA E PUXA O METODO QUE ESCREVE ELE NO ARQUIVO DE TEXTO
    public static void adicionarUsuario(Usuario usuario) throws IOException {
        usuarios.put(usuario.getUsuario(),usuario);
        registrarUsuario(usuarios);
    }

    // REGISTRA O USUARIO NO ARQUIVO DE TEXTO
    private static void registrarUsuario(Map<String, Usuario> usuarios) {


        try (BufferedWriter registrador = new BufferedWriter(new FileWriter(arquivo))) {
            registrador.write("Usuario,Nome,Senha,ContaCorrente,ContaPoupanca,ContaAdicional\n");

            for (Map.Entry<String, Usuario> entrada : usuarios.entrySet()) {
                Usuario usuario = entrada.getValue();

                if (usuario instanceof Correntista correntista) {
                    registrador.write(correntista.getUsuario() + "," + correntista.getSenha() + ","
                            + correntista.getNivelacesso() + "," + correntista.getContaCorrente()
                            + "," + correntista.getContaPoupança() + "\n");
                } else {
                    registrador.write(usuario.getUsuario() + "," + usuario.getSenha() + ","
                            + usuario.getNivelacesso() + "\n");
                }
            }
            System.out.println(" *Informações registradas com sucesso.* ");
        } catch (IOException e) {
            System.out.println(" *Erro ao registrar as informações. Tente novamente!* ");
        }
    }

    //CARREGA OS USUARIOS NO ARQUIVO DE TEXTO PARA A LISTA
    public static void carregarUsuarios() throws IOException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            String linha = leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");

                String usuario = dados[0];
                String senha = dados[1];
                String nivelUsuario = dados[2];

                if (nivelUsuario.equals("correntista") && dados.length >= 5) {
                    String numContaCorrente = dados[3];
                    String numContaPoupanca = dados[4];
                    //String numContaAdicional = dados[5];

                    Correntista correntista = new Correntista(usuario, senha, numContaCorrente, numContaPoupanca);
                    adicionarUsuario(correntista);
                } else {
                    switch (nivelUsuario) {
                        case "admin":
                            AdminFull admFull = new AdminFull(usuario, senha);
                            adicionarUsuario(admFull);
                            break;
                        case "gerente":
                            Gerente gerente = new Gerente(usuario, senha);
                            adicionarUsuario(gerente);
                            break;
                        case "bancario":
                            Bancario bancario = new Bancario(usuario, senha);
                            adicionarUsuario(bancario);
                            break;
                    }
                }
            }

        }
        System.out.println("Usuários carregados com sucesso.");
    }
}
