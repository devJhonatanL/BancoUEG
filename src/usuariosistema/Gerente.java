package usuariosistema;
import sistema.EscritorLeitor;
import sistema.SistemaDeAutenticacao;

import java.io.IOException;
import java.util.Scanner;

public class Gerente extends Usuario {
    public Gerente(String usuario, String senha) {
        super(usuario, senha,"gerente");

    }

    public void criarBancario() throws IOException {

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

        Bancario novoBancario = new Bancario(usuario, senha);
        EscritorLeitor.adicionarUsuario(novoBancario);
        System.out.println("Usuario criado com sucesso");

    }
    //CRIA O CORRENTISTA
    public void criarCorrentista() throws IOException {

        Scanner input = new Scanner(System.in);

        String contaCorrente = "0";
        String contaPoupanca = "0";
        String contaAdicional = "0";

        System.out.println("Digite o usuario: ");
        String usuario = input.nextLine();

        Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);

        if (usuarioEncontrado != null) {
            System.out.println("Já existe esse usuario.");
            return;
        }

        System.out.println("Digite a Senha: ");
        String senha = input.nextLine();

        System.out.println("""
                Digite o tipo de conta:
                [1] Corrente
                [2] Poupança
                """);

        int opcao = input.nextInt();
        input.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("Digite o número da conta:");
                contaCorrente = input.nextLine() +"C";

                System.out.println("""
                Deseja uma conta corrente adicional:
                [1] Sim
                [2] Não
                """);
                String aceitarConta = input.nextLine();
                    switch (aceitarConta) {
                        case "1":
                            contaAdicional = contaCorrente+"A";
                            break;
                        case "2":
                            contaAdicional = "0";
                    }
                break;
            case 2:
                System.out.println("Digite o número da conta:");
                contaPoupanca = input.nextLine()+"P";
                break;
            default:
                System.out.println("Digite uma opção válida");

        }

        Correntista novoCorrentista = new Correntista(usuario, senha, contaCorrente, contaPoupanca, contaAdicional);
        EscritorLeitor.adicionarUsuario(novoCorrentista);
        System.out.println(novoCorrentista.toString());
        System.out.println("Usuario criado com sucesso");

    }

    private void adicionarNovaConta() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario que você deseja adicionar uma conta: ");
        String usuario = input.nextLine();

        Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);
        if (usuarioEncontrado == null) {
            System.out.println("usuario não encontrado");
            return;
        }
        if (!usuarioEncontrado.getNivelacesso().equals("correntista")){
            System.out.println("Esse usuario não é um correntista");
            return;
        }
        Correntista correntista = (Correntista) usuarioEncontrado;
        System.out.println("""
                Digite o tipo de conta:
                [1] Corrente
                [2] Poupança
                [3] Adicional
                """);

        int opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                if(correntista.getContaCorrente().equals("0")){
                    //criar conta corrente
                }else{
                    System.out.println("Correntista ja possui uma conta corrente");
                }
                break;
            case 2:
                if(correntista.getContaPoupança().equals("0")){
                    //criar conta poupança
                }else {
                    System.out.println("Correntista ja possui uma conta poupança");
                }
                break;
            case 3:
                if(!correntista.getContaCorrente().equals("0")) {
                    //verificação se existe conta corrente
                    if(correntista.getContaCorrenteAdicional().equals("0")){
                        //criar conta adicional
                    }else {
                        System.out.println("Correntista ja possui uma conta adicional");
                    }
                }else {
                    System.out.println("Correntista não possui uma conta corrente");
                }

        }
    }






    public  void menuGerente() throws IOException {
        Scanner input = new Scanner(System.in);
        int opcao;

        System.out.println("""
                
                Escolha uma função:
                [1] Criar usuario bancario
                [2] Criar usuario correntista
                [3] Adicionar conta ao correntista
                [4] Configurar limite conta adicional
                """);
        opcao = input.nextInt();
        switch (opcao) {
            case 1:
                criarBancario();
                break;
            case 2:
                criarCorrentista();
                break;
            case 3:
                SistemaDeAutenticacao voltar = new SistemaDeAutenticacao();
                voltar.login();
                break;
            case 4:
                System.out.println("Sistema finalizado com sucesso");
                break;
        }
    }
}
