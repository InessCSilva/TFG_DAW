package es.studium.bancoAlimentos.modelo;

/**
 *
 * @author Ines 
 * Alimento Representa un alimento 
 *
 */
public class Alimento {
	private Integer id;
	private String nombreAlimento;
	private String tipoAlimento;
	private String marcaAlimento;
	private String cantidadAlimento;
	private Boolean caducidadAlimento;

	public Alimento() {}

	public Alimento(int id, String nombreAlimento, String tipoAlimento, String marcaAlimento, String cantidadAlimento, Boolean caducidadAlimento) {
		setId(id);
		setNombreAlimento(nombreAlimento);
		setTipoAlimento(tipoAlimento);
		setMarcaAlimento(marcaAlimento);
		setCantidadAlimento(cantidadAlimento);
		setCaducidadAlimento(caducidadAlimento);

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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