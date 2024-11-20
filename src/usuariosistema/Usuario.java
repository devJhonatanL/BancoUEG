package usuariosistema;

public class Usuario {
    protected String nivelacesso;
    protected String senha;
    protected String usuario;

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

    @Override
    public String toString() {
        return "Usuário: " + this.getUsuario() + " - " + this.senha + " [" + this.nivelacesso + "]";
    }

}
