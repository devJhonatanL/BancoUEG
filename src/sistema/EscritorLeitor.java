package sistema;
import contasusuarios.Conta;
import contasusuarios.ContaAdicional;
import contasusuarios.ContaCorrente;
import contasusuarios.ContaPoupanca;
import usuariosistema.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EscritorLeitor {

    private static HashMap<String, Usuario> usuarios = new HashMap<>();
    private static final String arquivo = "usuarios.txt";
    private static HashMap<String, Conta> contas = new HashMap<>();
    private static final String arquivoContas = "contas.txt";

    //RETORNA  A LISTA
    public static HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }
    public static HashMap<String, Conta> getContas() {return contas;}

    //SALVA O USUARIO NA LISTA E PUXA O METODO QUE ESCREVE ELE NO ARQUIVO DE TEXTO
    public static void adicionarUsuario(Usuario usuario) throws IOException {
        usuarios.put(usuario.getUsuario(),usuario);
        registrarUsuario(usuarios);
    }

    public static void adicionarContas(Conta conta) throws IOException {
        contas.put(conta.getNumeroConta(), conta);
        registrarContas(contas);
    }
    // REGISTRA O USUARIO NO ARQUIVO DE TEXTO

    private static void registrarUsuario(Map<String, Usuario> usuarios) {


        try (BufferedWriter registrador = new BufferedWriter(new FileWriter(arquivo))) {
            registrador.write("Usuario,Senha,ContaCorrente,ContaPoupanca,ContaAdicional\n");

            for (Map.Entry<String, Usuario> entrada : usuarios.entrySet()) {
                Usuario usuario = entrada.getValue();

                if (usuario instanceof Correntista correntista) {
                    registrador.write(correntista.getUsuario() + "," + correntista.getSenha() + ","
                            + correntista.getNivelacesso() + "," + correntista.getContaCorrente()
                            + "," + correntista.getContaPoupança() + "," + correntista.getContaCorrenteAdicional() + "\n");
                } else {
                    registrador.write(usuario.getUsuario() + "," + usuario.getSenha() + ","
                            + usuario.getNivelacesso() + "\n");
                }
            }
           // System.out.println(" *Informações registradas com sucesso.* ");
        } catch (IOException e) {
            System.out.println(" *Erro ao registrar as informações. Tente novamente!* ");
        }
    }

    private static void registrarContas(Map<String, Conta> contas) {


        try (BufferedWriter registrador = new BufferedWriter(new FileWriter(arquivoContas))) {
            registrador.write("Saldo,Titular,NumeroConta,Senha,TipoConta,ChequeEspecial\n");

            for (Map.Entry<String, Conta> entrada : contas.entrySet()) {
                Conta conta = entrada.getValue();

                if (conta instanceof ContaCorrente corrente) {
                    registrador.write(corrente.getSaldo() + "," + corrente.getTitular() + ","
                            + corrente.getNumeroConta() + "," + corrente.getSenha()
                            + "," + corrente.getTipoConta() + "," + corrente.getChequeEspecial() + "\n");
                } else if (conta instanceof ContaAdicional adicional) {
                    registrador.write(adicional.getSaldo() + "," + adicional.getTitular() + ","
                            + adicional.getNumeroConta() + "," + adicional.getSenha()
                                + "," + adicional.getTipoConta() + "," + adicional.getLimite() + "\n");
                }else {
                    registrador.write(conta.getSaldo() + "," + conta.getTitular() + ","
                            + conta.getNumeroConta() + "," + conta.getSenha()
                            + "," + conta.getTipoConta() + "\n");

                }
            }
            // System.out.println(" *Informações registradas com sucesso.* ");
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

                if (nivelUsuario.equals("correntista") && dados.length >= 6) {
                    String numContaCorrente = dados[3];
                    String numContaPoupanca = dados[4];
                    String numContaAdicional = dados[5];

                    Correntista correntista = new Correntista(usuario, senha, numContaCorrente, numContaPoupanca, numContaAdicional);
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

    public static void carregarContas() throws IOException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoContas))) {
            String linha = leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(",");

                Double saldo = Double.valueOf(dados[0]);
                String titular = dados[1];
                String numeroConta = dados[2];
                String senha = dados[3];
                String tipoConta = dados[4];

                if (tipoConta.equals("corrente") && dados.length >= 5) {
                    Double chequeEspecial = Double.valueOf(dados[5]);
                    ContaCorrente corrente = new ContaCorrente(saldo, titular, numeroConta, senha);
                    corrente.setChequeEspecial(chequeEspecial);
                    adicionarContas(corrente);
                } else if (tipoConta.equals("adicional") && dados.length >= 5) {
                    double limite = Double.parseDouble(dados[5]);
                    ContaAdicional adicional = new ContaAdicional(saldo, titular, numeroConta, senha);
                    adicional.setLimite(limite);
                    adicionarContas(adicional);
                    } else{
                    ContaPoupanca poupanca = new ContaPoupanca(saldo, titular, numeroConta, senha);
                    adicionarContas(poupanca);
                }
            }

        }
        System.out.println("Contas carregados com sucesso.");
    }


}
