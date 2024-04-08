package es.studium.bancoAlimentos.modelo;

import java.util.Date;

/**
 *
 * @author Ines RetiradaBeneficiarioAlimento Representa una retirada con toda la
 *         información
 *
 */
public class RetiradaBeneficiarioAlimento {
	private Integer idRetirada;
	private Integer cantidadAlimentoRetirada;
	private Date fechaRetirada;
	private Integer idAlimento;
	private String nombreAlimento;
	private String apellidosAlimento;
	private String marcaAlimento;
	private String cantidadAlimento;
	private Boolean caducidadAlimento;
	private Integer idBeneficiario;
	private String nombreBeneficiario;
	private String apellidosBeneficiario;
	private String dniBeneficiario;

	public RetiradaBeneficiarioAlimento() {
	}

	public RetiradaBeneficiarioAlimento(Integer idRetirada, Integer cantidadAlimentoRetirada, Date fechaRetirada,
			Integer idAlimento, String nombreAlimento, String apellidosAlimento, String marcaAlimento,
			String cantidadAlimento, Boolean caducidadAlimento, Integer idBeneficiario, String nombreBeneficiario,
			String apellidosBeneficiario, String dniBeneficiario) {
		setIdRetirada(idRetirada);
		setCantidadAlimentoRetirada(cantidadAlimentoRetirada);
		setFechaRetirada(fechaRetirada);
		setIdAlimento(idAlimento);
		setNombreAlimento(nombreAlimento);
		setApellidosAlimento(apellidosAlimento);
		setMarcaAlimento(marcaAlimento);
		setCantidadAlimento(cantidadAlimento);
		setCaducidadAlimento(caducidadAlimento);
		setIdBeneficiario(idBeneficiario);
		setNombreBeneficiario(nombreBeneficiario);
		setApellidosBeneficiario(apellidosBeneficiario);
		setDNIBeneficiario(dniBeneficiario);
	}

	public Integer getIdRetirada() {
		return idRetirada;
	}

	public void setIdRetirada(Integer idRetirada) {
		this.idRetirada = idRetirada;
	}

	public Integer getCantidadAlimentoRetirada() {
		return cantidadAlimentoRetirada;
	}

	public void setCantidadAlimentoRetirada(Integer cantidadAlimentoRetirada) {
		this.cantidadAlimentoRetirada = cantidadAlimentoRetirada;
	}

	public Date getFechaRetirada() {
		return fechaRetirada;
	}

	public void setFechaRetirada(Date fechaRetirada) {
		this.fechaRetirada = fechaRetirada;
	}

	public Integer getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(Integer idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getNombreAlimento() {
		return nombreAlimento;
	}

	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = nombreAlimento;
	}

	public String getApellidosAlimento() {
		return apellidosAlimento;
	}

	public void setApellidosAlimento(String apellidosAlimento) {
		this.apellidosAlimento = apellidosAlimento;
	}

	public String getMarcaAlimento() {
		return marcaAlimento;
	}

	public void setMarcaAlimento(String marcaAlimento) {
		this.marcaAlimento = marcaAlimento;
	}

	public String getCantidadAlimento() {
		return cantidadAlimento;
	}

	public void setCantidadAlimento(String cantidadAlimento) {
		this.cantidadAlimento = cantidadAlimento;
	}

	public Boolean getCaducidadAlimento() {
		return caducidadAlimento;
	}

	public void setCaducidadAlimento(Boolean caducidadAlimento) {
		this.caducidadAlimento = caducidadAlimento;
	}

	public Integer getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(Integer idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public String getApellidosBeneficiario() {
		return apellidosBeneficiario;
	}

	public void setApellidosBeneficiario(String apellidosBeneficiario) {
		this.apellidosBeneficiario = apellidosBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	public String getDNIBeneficiario() {
		return dniBeneficiario;
	}

	public void setDNIBeneficiario(String dniBeneficiario) {
		this.dniBeneficiario = dniBeneficiario;
	}
}