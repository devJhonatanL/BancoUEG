package usuariosistema;

public class Usuario {
    protected String nivelacesso;
    protected String senha;
    protected String usuario;
    protected boolean sair = false;


    public Usuario(String usuario, String senha, String nivelacesso) {
        this.nivelacesso = nivelacesso;
        this.senha = senha;
        this.usuario = usuario;
    }
    public String getNivelacesso() {
        return nivelacesso;
    }
    public String getSenha() {
        return senha;
    }
    public String getUsuario() {
        return usuario;
    }
    public boolean getSair() {
        return sair;
    }

}
