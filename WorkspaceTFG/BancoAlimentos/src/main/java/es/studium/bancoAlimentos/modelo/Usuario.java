package es.studium.bancoAlimentos.modelo;

/**
 *
 * @author Ines 
 * Usuario Representa un usuario 
 *
 */
public class Usuario {
	private Integer id;
	private String nombre;
	private String contraseña;
	private Boolean admin;

	public Usuario() {}

	public Usuario(Integer id, String nombre, String contraseña, Boolean admin) {
		setId(id);
		setNombre(nombre);
		setContraseña(contraseña);
		setAdmin(admin);
	}
	
	public Usuario(Integer id, String nombre, Boolean admin) {
		setId(id);
		setNombre(nombre);
		setAdmin(admin);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}	
}