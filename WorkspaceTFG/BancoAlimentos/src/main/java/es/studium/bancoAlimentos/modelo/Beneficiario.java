package es.studium.bancoAlimentos.modelo;

/**
 *
 * @author Ines 
 * Beneficiario Representa un beneficiario 
 *
 */
public class Beneficiario {
	private Integer idBeneficiario;
	private String nombreBeneficiario;
	private String apellidosBeneficiario;
	private String dniBeneficiario;

	public Beneficiario() {}

	public Beneficiario(int idBeneficiario, String nombreBeneficiario, String apellidosBeneficiario, String dniBeneficiario) {
		setIdBeneficiario(idBeneficiario);
		setNombreBeneficiario(nombreBeneficiario);
		setApellidosBeneficiario(apellidosBeneficiario);
		setDniBeneficiario(dniBeneficiario);
	}
	
	public Integer getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(Integer idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getApellidosBeneficiario() {
		return apellidosBeneficiario;
	}

	public void setApellidosBeneficiario(String apellidosBeneficiario) {
		this.apellidosBeneficiario = apellidosBeneficiario;
	}

	public String getDniBeneficiario() {
		return dniBeneficiario;
	}

	public void setDniBeneficiario(String dniBeneficiario) {
		this.dniBeneficiario = dniBeneficiario;
	}	
}