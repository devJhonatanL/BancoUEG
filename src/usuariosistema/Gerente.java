package usuariosistema;
import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import sistema.EscritorLeitor;
import java.io.IOException;
import java.util.Scanner;

public class Gerente extends usuariosistema.Usuario {
    public Gerente(String usuario, String senha) {
        super(usuario, senha,"gerente");
    }
    //CRIAR O USUARIO DO BANCARIO BANCARIO
    public void criarBancario() throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario: ");
        String usuario = input.nextLine();

        usuariosistema.Usuario localizado = EscritorLeitor.getUsuarios().get(usuario);

        if (localizado != null) {
            System.out.println("Já existe esse usuario no sistema. Tente outro.");
            return;
        }

        System.out.println("Digite uma senha: ");
        String senha = input.nextLine();

        //INSTACIA E SALVA NO HASH MAP E ESCREVE NO BLOCO DE NOTAS
        Bancario novoBancario = new Bancario(usuario, senha);
        EscritorLeitor.adicionarUsuario(novoBancario);
        System.out.println("Usuario bancario foi registrado com sucesso.");

    }

    //CRIAR A CONTA CORRENTE
    public void criarContaCorrente(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);
        String contaCorrente;
        double saldo;
        int opcao;

        System.out.println("\n==============================");
        System.out.println("Digite um numero para a conta:");
        System.out.println("\n==============================");
        contaCorrente = input.nextLine() + "C";

        System.out.println("\nQual vai ser o valor do saldo inicial:");
        saldo = input.nextDouble();
        input.nextLine();

        ContaCorrente corrente = new ContaCorrente(saldo, correntista.getUsuario(), contaCorrente, correntista.getSenha());
        System.out.println("\n============================");
        System.out.println("+ Opção de cheque especial +");
        System.out.println("+ 1 - Incluir              +");
        System.out.println("+ 2 - Não incluir          +");
        System.out.println("\n============================");

        opcao = input.nextInt();
        input.nextLine();
        switch (opcao) {
            case 1:
                double valor;
                System.out.println("=========================");
                System.out.println("Digite o valor desejado: ");
                System.out.println("\n=========================");
                valor = input.nextDouble();
                correntista.setContaCorrente(contaCorrente);
                corrente.setChequeEspecial(valor);
                EscritorLeitor.adicionarUsuario(correntista);
                EscritorLeitor.adicionarContas(corrente);
                break;
            case 2:
                correntista.setContaCorrente(contaCorrente);
                EscritorLeitor.adicionarUsuario(correntista);
                EscritorLeitor.adicionarContas(corrente);
                break;
            default:
                System.out.println("\nEssa opção não existe, preste atenção no menu\n");
        }
    }

    //CRIAR A CONTA POUPANÇA
    public void criarContaPoupanca(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);
        String contaPoupanca;
        double saldo;


        System.out.println("\n==============================");
        System.out.println("Digite um numero para a conta:");
        System.out.println("\n==============================");
        contaPoupanca = input.nextLine()+"P";
        System.out.println("\nQual vai ser o valor do saldo inicial:\n");
        saldo = input.nextDouble();
        input.nextLine();

        ContaPoupanca poupanca = new ContaPoupanca(saldo, correntista.getUsuario(), contaPoupanca, correntista.getSenha());

        correntista.setContaPoupança(contaPoupanca);
        EscritorLeitor.adicionarUsuario(correntista);
        EscritorLeitor.adicionarContas(poupanca);

    }

    //CRIAR A CONTA ADICIONAL
    public void criarContaAdicional(Correntista correntista) throws IOException {
        Scanner input = new Scanner(System.in);
        String contaAdicional;
        double saldo = 0.0;
        double limite;

        System.out.println("Digite um numero para a conta:");
        contaAdicional = input.nextLine()+"AD";

        System.out.println("Qual vai ser o valor do limite:");
        limite = input.nextDouble();
        input.nextLine();

        ContaAdicional adicional =new ContaAdicional(saldo, correntista.getUsuario(), contaAdicional, correntista.getSenha());
        correntista.setContaCorrenteAdicional(contaAdicional);
        adicional.setLimite(limite);
        EscritorLeitor.adicionarUsuario(correntista);
        EscritorLeitor.adicionarContas(adicional);
    }

    //ALTERADOR DE LIMITE DA CONTA ADICIONAL
    public void alterarLimite() throws IOException {
        Scanner input = new Scanner(System.in);
        String numeroConta;
        System.out.println("Digite o numero da conta");
        numeroConta = input.nextLine();

        Conta contaEncontrada = EscritorLeitor.getContas().get(numeroConta);
        if (contaEncontrada == null) {
            System.out.println("Essa conta não foi encontrada");
            return;
        }
        if (!contaEncontrada.getTipoConta().equals("adicional")){
            System.out.println("Essa conta nao e adicional");
            return;
        }
        ContaAdicional contaAdicional = (ContaAdicional) contaEncontrada;

        double limite;

        System.out.println("Informe o novo valor do limite:");
        limite = input.nextDouble();
        input.nextLine();
        if(limite > 0){
            contaAdicional.setLimite(limite);
            EscritorLeitor.adicionarContas(contaAdicional);
            System.out.println("Limite atualizado com sucesso");
        }else{
            System.out.println("Valor invalido. Tente Novamente.");
            alterarLimite();
        }
    }

    //CRIA O USUARIO DO CORRENTISTA - POR PADRÂO ELE TEM QUE IR COM UM TIPO DE CONTA, PODENDO SER CORRENTE OU POUPANÇA
    public void criarCorrentista() throws IOException {

        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario: ");
        String usuario = input.nextLine();

        usuariosistema.Usuario localizado = EscritorLeitor.getUsuarios().get(usuario);

        if (localizado != null) {
            System.out.println("Já existe esse usuario no sistema. Tente outro.");
            return;
        }

        System.out.println("Digite uma senha: ");
        String senha = input.nextLine();

        System.out.println("===========================");
        System.out.println("+ Tipo de conta           +");
        System.out.println("+ 1 - Corrente            +");
        System.out.println("+ 2 - Poupança            +");
        System.out.println("===========================");

        int opcao;
        opcao = input.nextInt();
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
                System.out.println("Essa opção não existe, preste atenção no menu");

        }
        System.out.println(novoCorrentista);
        System.out.println("Usuario correntista foi registrado  com sucesso");
    }

    //ADICIONA UMA NOVA CONTA AO CORRENTISTA - OBS: SO PODE ADICIONAR UMA CONTA DO TIPO ADICIONAL SE O CORRENTISTA TIVER UMA CONTA CORRENTE
    private void adicionarNovaConta() throws IOException {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o nome do usuario que você deseja adicionar uma conta: ");
        String usuario = input.nextLine();

        usuariosistema.Usuario usuarioEncontrado = EscritorLeitor.getUsuarios().get(usuario);
        if (usuarioEncontrado == null) {
            System.out.println("usuario não encontrado");
            return;
        }
        if (!usuarioEncontrado.getNivelacesso().equals("correntista")){
            System.out.println("Esse usuario não é um correntista");
            return;
        }
        Correntista correntista = (Correntista) usuarioEncontrado;

        System.out.println("===========================");
        System.out.println("+ Escolha o tipo de conta +");
        System.out.println("+ 1 - Corrente            +");
        System.out.println("+ 2 - Poupança            +");
        System.out.println("+ 2 - Adicional           +");
        System.out.println("===========================");

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

    // MENU DO GERENTE
    public  void menuGerente() throws IOException {
        Scanner input = new Scanner(System.in);
        int opcao;


        System.out.println("=================================");
        System.out.println("+       Menu do gerente         +");
        System.out.println("+ 1 - Criar usuario bancario    +");
        System.out.println("+ 2 - Criar usuario correntista +");
        System.out.println("+ 3 - Adicionar uma nova conta  +");
        System.out.println("+ 4 - Configurar limte C.A      +");
        System.out.println("+ 5 - Sair                      +");
        System.out.println("=================================");

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
                alterarLimite();
                break;
            case 5:
                System.out.println("Sistema foi fechado.");
                break;
        }
    }
}
