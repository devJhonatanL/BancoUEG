package usuariosistema;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
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
    public void criarContaCorrente(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o número da conta:");
        String contaCorrente = input.nextLine() + "C";

        System.out.println("Qual valor do saldo inicial:");
        double saldo = input.nextDouble();
        input.nextLine();

        ContaCorrente corrente = new ContaCorrente(saldo, correntista.getUsuario(), contaCorrente, correntista.getSenha());


        System.out.println("""
                Deseja utilizar cheque especial:
                [1] Sim
                [2] Não
                """);
        String aceitarCheque = input.nextLine();
        String contaAdicional = contaCorrente + "A";
        switch (aceitarCheque) {
            case "1":
                System.out.println("Digite o valor do Cheque Especial");
                double valor = input.nextDouble();
                correntista.setContaCorrente(contaCorrente);
                corrente.setChequeEspecial(valor);
                EscritorLeitor.adicionarUsuario(correntista);
                EscritorLeitor.adicionarContas(corrente);
                break;
            case "2":
                correntista.setContaCorrente(contaCorrente);
                EscritorLeitor.adicionarUsuario(correntista);
                EscritorLeitor.adicionarContas(corrente);
                break;

        }
    }
    public void criarContaPoupanca(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o numero do conta:");
        String contaPoupanca = input.nextLine()+"P";

        System.out.println("Qual valor do saldo inicial:");
        double saldo = input.nextDouble();
        input.nextLine();

        ContaPoupanca poupanca = new ContaPoupanca(saldo, correntista.getUsuario(), contaPoupanca, correntista.getSenha());

        correntista.setContaPoupança(contaPoupanca);
        EscritorLeitor.adicionarUsuario(correntista);
        EscritorLeitor.adicionarContas(poupanca);

    }
    public void criarContaAdicional(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);

        double saldo = 0;
        System.out.println("Digite o numero da conta:");
        String contaAdicional = input.nextLine()+"AD";

        System.out.println("Qual valor do limite :");
        double limite = input.nextDouble();
        input.nextLine();

        ContaAdicional adicional =new ContaAdicional(saldo, correntista.getUsuario(), contaAdicional, correntista.getSenha());
        correntista.setContaCorrenteAdicional(contaAdicional);
        adicional.setLimite(limite);
        EscritorLeitor.adicionarUsuario(correntista);
        EscritorLeitor.adicionarContas(adicional);
    }




    //CRIA O CORRENTISTA
    public void criarCorrentista() throws IOException {

        Scanner input = new Scanner(System.in);

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
        Correntista novoCorrentista = new Correntista(usuario, senha);

        switch (opcao) {
            case 1:
                criarContaCorrente(novoCorrentista);
                break;
            case 2:
                criarContaPoupanca(novoCorrentista);
                break;
            default:
                System.out.println("Digite uma opção válida");

        }


        //EscritorLeitor.adicionarUsuario(novoCorrentista);
        System.out.println(novoCorrentista);
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
                if(correntista.getContaCorrente().equals("null")){
                    criarContaCorrente(correntista);
                }else{
                    System.out.println("Correntista ja possui uma conta corrente");
                }
                break;
            case 2:
                if(correntista.getContaPoupança().equals("null")){
                    criarContaPoupanca(correntista);
                }else {
                    System.out.println("Correntista ja possui uma conta poupança");
                }
                break;
            case 3:
                //VERIFICA SE TEM UMA CONTA CORRENTE
                if(!correntista.getContaCorrente().equals("null")) {
                    //CRIA A CONTA ADICIONAL
                    if(correntista.getContaCorrenteAdicional().equals("null")){
                        criarContaAdicional(correntista);
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
                adicionarNovaConta();
                break;
            case 4:
                System.out.println("Sistema finalizado com sucesso");
                break;
        }
    }
}
