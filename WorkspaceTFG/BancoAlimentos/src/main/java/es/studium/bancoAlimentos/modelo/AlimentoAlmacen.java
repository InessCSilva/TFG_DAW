package es.studium.bancoAlimentos.modelo;

/**
 *
 * @author Ines 
 * AlimentoAlmacen Representa a el stock de un alimento en el almacen
 *
 */
public class AlimentoAlmacen {
	private Integer idAlmacen;
	private Integer cantidadTotalAlmacen;
	private Integer idAlimento;
	private String nombreAlimento;
	private String tipoAlimento;
	private String marcaAlimento;
	private String cantidadAlimento;
	private Boolean caducidadAlimento;

	public AlimentoAlmacen() {}

	public AlimentoAlmacen(Integer idAlmacen, Integer cantidadTotalAlmacen, Integer idAlimento, String nombreAlimento, String tipoAlimento, String marcaAlimento, String cantidadAlimento, Boolean caducidadAlimento) {
		setIdAlmacen(idAlmacen);
		setCantidadTotalAlmacen(cantidadTotalAlmacen);
		setIdAlimento(idAlimento);
		setNombreAlimento(nombreAlimento);
		setTipoAlimento(tipoAlimento);
		setMarcaAlimento(marcaAlimento);
		setCantidadAlimento(cantidadAlimento);
		setCaducidadAlimento(caducidadAlimento);

	}
		
	public Integer getIdAlmacen() {
		return idAlmacen;
	}

	public void setIdAlmacen(Integer idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public Integer getCantidadTotalAlmacen() {
		return cantidadTotalAlmacen;
	}

	public void setCantidadTotalAlmacen(Integer cantidadTotalAlmacen) {
		this.cantidadTotalAlmacen = cantidadTotalAlmacen;
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
	
}