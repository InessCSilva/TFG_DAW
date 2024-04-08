package es.studium.bancoAlimentos.modelo;

import java.util.Date;

/**
 *
 * @author Ines DonacionDonanteAlimento Representa una donación con toda la
 *         información
 *
 */
public class DonacionDonanteAlimento {
	private Integer idDonacion;
	private Integer cantidadAlimentoDonacion;
	private Date fechaDonacion;
	private Integer idAlimento;
	private String nombreAlimento;
	private String tipoAlimento;
	private String marcaAlimento;
	private String cantidadAlimento;
	private Boolean caducidadAlimento;
	private Integer idDonante;
	private String tipoDonante;
	private String nombreDonante;
	private String identificadorDonante;

	public DonacionDonanteAlimento() {}

	public DonacionDonanteAlimento(Integer idDonacion, Integer cantidadAlimentoDonacion, Date fechaDonacion,
			Integer idAlimento, String nombreAlimento, String tipoAlimento, String marcaAlimento,
			String cantidadAlimento, Boolean caducidadAlimento, Integer idDonante, String nombreDonante,
			String tipoDonante, String identificadorDonante) {
		setIdDonacion(idDonacion);
		setCantidadAlimentoDonacion(cantidadAlimentoDonacion);
		setFechaDonacion(fechaDonacion);
		setIdAlimento(idAlimento);
		setNombreAlimento(nombreAlimento);
		setTipoAlimento(tipoAlimento);
		setMarcaAlimento(marcaAlimento);
		setCantidadAlimento(cantidadAlimento);
		setCaducidadAlimento(caducidadAlimento);
		setIdDonante(idDonante);
		setIdentificadorDonante(identificadorDonante);
		setNombreDonante(nombreDonante);
		setTipoDonante(tipoDonante);
	}

	public Integer getIdDonacion() {
		return idDonacion;
	}

	public void setIdDonacion(Integer idDonacion) {
		this.idDonacion = idDonacion;
	}

	public Integer getCantidadAlimentoDonacion() {
		return cantidadAlimentoDonacion;
	}

	public void setCantidadAlimentoDonacion(Integer cantidadAlimentoDonacion) {
		this.cantidadAlimentoDonacion = cantidadAlimentoDonacion;
	}

	public Date getFechaDonacion() {
		return fechaDonacion;
	}

	public void setFechaDonacion(Date fechaDonacion) {
		this.fechaDonacion = fechaDonacion;
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

	public String getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(String tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
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