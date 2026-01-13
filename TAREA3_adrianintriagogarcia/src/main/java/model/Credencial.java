package model;

public class Credencial {

    private String nombreusuario;
    private String password;
    private Rol rol;
    private int idPersona;
    
    public Credencial() {}

    public Credencial(String username, String password, Rol rol, int idPersona) {
        this.nombreusuario = username;
        this.password = password;
        this.rol = rol;
        this.idPersona = idPersona;
    }

    public String getUsername() {
        return nombreusuario;
    }

    public void setUsername(String username) {
        this.nombreusuario = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
    
    
}
