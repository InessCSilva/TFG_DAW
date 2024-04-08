package es.studium.bancoAlimentos.modelo;

/**
 *
 * @author Ines 
 * Donante Representa un donante 
 *
 */
public class Donante {
	private Integer idDonante;
	private String tipoDonante;
	private String nombreDonante;
	private String identificadorDonante;

	public Donante() {}

	public Donante(int idDonante, String tipoDonante, String nombreDonante, String identificadorDonante) {
		setIdDonante(idDonante);
		setIdentificadorDonante(identificadorDonante);
		setNombreDonante(nombreDonante);
		setTipoDonante(tipoDonante);
	}
	
	public Integer getIdDonante() {
		return idDonante;
	}

	public void setIdDonante(Integer idDonante) {
		this.idDonante = idDonante;
	}

	public String getTipoDonante() {
		return tipoDonante;
	}

	public void setTipoDonante(String tipoDonante) {
		this.tipoDonante = tipoDonante;
	}

	public String getNombreDonante() {
		return nombreDonante;
	}

	public void setNombreDonante(String nombreDonante) {
		this.nombreDonante = nombreDonante;
	}

	public String getIdentificadorDonante() {
		return identificadorDonante;
	}

	public void setIdentificadorDonante(String identificadorDonante) {
		this.identificadorDonante = identificadorDonante;
	}	
}