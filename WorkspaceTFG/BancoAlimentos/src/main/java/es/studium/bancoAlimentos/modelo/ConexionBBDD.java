package es.studium.bancoAlimentos.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 *
 * @author Ines ConexionBBDD Encapsula la comunicación con la base de datos
 *
 */
public class ConexionBBDD {

	// Pool de conexiones a la base de datos
	private static DataSource pool;

	public static void iniciarPool() throws ServletException {
		try {
			// Crea un contexto para poder luego buscar el recurso DataSource
			InitialContext ctx = new InitialContext();
			// Busca el recurso DataSource en el contexto
			pool = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql_bancodealimentos");
			if (pool == null) {
				throw new ServletException("DataSource desconocida 'mysql_bancodealimentos'");
			}
		} catch (NamingException ex) {
		}
	}

	// Método para cerrar los recursos
	public static void liberarConexión(Connection conn, Statement stmt) {
		try {
			// Cerramos el resto de recursos
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Método para cerrar los recuross con varios stmts de la misma conexión
	public static void liberarConexiónMultipleStmt(Connection conn, ArrayList<Statement> stmts) {
		try {

			// Cerramos el resto de recursos
			for (Statement stmt : stmts) {
				if (stmt != null) {
					stmt.close();
				}
			}

			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// LOGIN
	// Método para comprobar si existe el usuario
	public Usuario realizarLogin(String nombre, String password) {

		Connection conn = null;
		Statement stmt = null;
		Usuario usuario = null;

		try {

			if (pool == null) {
				iniciarPool();
			}
			conn = pool.getConnection();
			stmt = conn.createStatement();
			StringBuilder sqlStr = new StringBuilder();
			sqlStr.append("SELECT * FROM usuarios WHERE ");
			sqlStr.append("STRCMP(usuarios.nombreUsuario,'").append(nombre).append("') = 0");
			sqlStr.append(" AND STRCMP(usuarios.claveUsuario,MD5('").append(password).append("')) = 0");
			ResultSet rset = stmt.executeQuery(sqlStr.toString());

			if (rset.next()) {
				// Si el resultset no está vacío
				usuario = new Usuario();
				usuario.setNombre(rset.getString("nombreUsuario"));
				usuario.setAdmin(rset.getBoolean("tipoUsuario"));
				return usuario;

			} else {
				return usuario;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return usuario;
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para recuperar el listado de usuarios
	public static ArrayList<Usuario> obtenerUsuarios() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<Usuario> usuarios = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM usuarios";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("nombreUsuario"),
						rs.getString("claveUsuario"), rs.getBoolean("tipoUsuario"));
				usuarios.add(usuario);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return usuarios;
	}

	// Método para dar de alta un usuario
	public static void altaUsuario(String nombreUsuario, String claveUsuario, Boolean tipoUsuario) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Creamos el usuario en BBDD
			String sqlStr = "INSERT INTO usuarios (nombreUsuario, claveUsuario, tipoUsuario) " + "VALUES ('"
					+ nombreUsuario + "', '" + claveUsuario + "', " + tipoUsuario + ")";
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para editar un usuario
	public static void editarUsuario(String idUsuario, String nombreUsuario, String claveUsuario, Boolean tipoUsuario) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Modificamos el usuario en BBDD
			String sqlStr = "UPDATE usuarios SET nombreUsuario = '" + nombreUsuario + "', claveUsuario = '"
					+ claveUsuario + "', tipoUsuario = " + tipoUsuario + " " + "WHERE idUsuario = " + idUsuario;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar un usuario
	public static void eliminarUsuario(String idUsuario) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos el usuario en BBDD
			String sqlStr = "DELETE FROM usuarios WHERE idUsuario = " + idUsuario;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// DONANTES
	// Método para recuperar el listado de donantes
	public static ArrayList<Donante> obtenerDonantes() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<Donante> donantes = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM donantes";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				Donante donante = new Donante(rs.getInt("idDonante"), rs.getString("tipoDonante"),
						rs.getString("nombreDonante"), rs.getString("identificadorDonante"));
				donantes.add(donante);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return donantes;
	}

	// Método para dar de alta un donante
	public static void altaDonante(String nombreDonante, String tipoDonante, String identificadorDonante) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Creamos el usuario en BBDD
			String sqlStr = "INSERT INTO donantes (tipoDonante, nombreDonante, identificadorDonante) " + "VALUES ('"
					+ tipoDonante + "', '" + nombreDonante + "', '" + identificadorDonante + "')";
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para editar un donante
	public static void editarDonante(String idDonante, String nombreDonante, String tipoDonante,
			String identificadorDonante) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Modificamos el donante en BBDD
			String sqlStr = "UPDATE donantes SET nombreDonante = '" + nombreDonante + "', tipoDonante = '" + tipoDonante
					+ "', identificadorDonante = '" + identificadorDonante + "' " + "WHERE idDonante = " + idDonante;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar un donante
	public static void eliminarDonante(String idDonante) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la referencia en donaciones si existiera
			eliminarDonacionesPorDonante(idDonante);

			// Eliminamos el donante en BBDD
			String sqlStr = "DELETE FROM donantes WHERE idDonante = " + idDonante;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// BENEFICIARIOS
	// Método para recuperar el listado de beneficiarios
	public static ArrayList<Beneficiario> obtenerBeneficiarios() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<Beneficiario> beneficiarios = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM beneficiarios";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				Beneficiario beneficiario = new Beneficiario(rs.getInt("idBeneficiario"),
						rs.getString("nombreBeneficiario"), rs.getString("apellidosBeneficiario"),
						rs.getString("dniBeneficiario"));
				beneficiarios.add(beneficiario);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return beneficiarios;
	}

	// Método para dar de alta un beneficiario
	public static void altaBeneficiario(String nombreBeneficiario, String apellidosBeneficiario,
			String dniBeneficiario) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Creamos el usuario en BBDD
			String sqlStr = "INSERT INTO beneficiarios (nombreBeneficiario, apellidosBeneficiario, dniBeneficiario) "
					+ "VALUES ('" + nombreBeneficiario + "', '" + apellidosBeneficiario + "', '" + dniBeneficiario
					+ "')";
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para editar un beneficiario
	public static void editarBeneficiario(String idBeneficiario, String nombreBeneficiario,
			String apellidosBeneficiario, String dniBeneficiario) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Modificamos el beneficiario en BBDD
			String sqlStr = "UPDATE beneficiarios SET nombreBeneficiario = '" + nombreBeneficiario
					+ "', apellidosBeneficiario = '" + apellidosBeneficiario + "', dniBeneficiario = '"
					+ dniBeneficiario + "' " + "WHERE idBeneficiario = " + idBeneficiario;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar un beneficiario
	public static void eliminarBeneficiario(String idBeneficiario) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la referencia en retiradas si existiera
			eliminarRetiradasPorBeneficiario(idBeneficiario);

			// Eliminamos el beneficiario en BBDD
			String sqlStr = "DELETE FROM beneficiarios WHERE idBeneficiario = " + idBeneficiario;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// ALIMENTOS
	// Método para recuperar el listado de alimentos
	public static ArrayList<Alimento> obtenerAlimentos() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<Alimento> alimentos = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM alimentos";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				Alimento alimento = new Alimento(rs.getInt("idAlimento"), rs.getString("nombreAlimento"),
						rs.getString("tipoAlimento"), rs.getString("marcaAlimento"), rs.getString("cantidadAlimento"),
						rs.getBoolean("caducidadAlimento"));
				alimentos.add(alimento);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return alimentos;
	}

	// Método para dar de alta un alimento
	public static void altaAlimento(String nombreAlimento, String tipoAlimento, String marcaAlimento,
			String cantidadAlimento, Boolean caducidadAlimento) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Creamos el alimento en BBDD
			String sqlStr = "INSERT INTO alimentos (nombreAlimento, tipoAlimento, marcaAlimento, cantidadAlimento, caducidadAlimento) "
					+ "VALUES ('" + nombreAlimento + "', '" + tipoAlimento + "', '" + marcaAlimento + "', '"
					+ cantidadAlimento + "', " + caducidadAlimento + ")";
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para editar un alimento
	public static void editarAlimento(String idAlimento, String nombreAlimento, String tipoAlimento,
			String marcaAlimento, String cantidadAlimento, Boolean caducidadAlimento) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Modificamos el alimento en BBDD
			String sqlStr = "UPDATE alimentos SET nombreAlimento = '" + nombreAlimento + "', tipoAlimento = '"
					+ tipoAlimento + "', marcaAlimento = '" + marcaAlimento + "', cantidadAlimento = '"
					+ cantidadAlimento + "', caducidadAlimento = " + caducidadAlimento + " " + "WHERE idAlimento = "
					+ idAlimento;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar un alimento
	public static void eliminarAlimento(String idAlimento) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la referencia en el almacen
			eliminarAlimentoAlmacenPorAlimento(idAlimento);

			// Eliminamos la referencia en donaciones si existiera
			eliminarDonacionesPorAlimento(idAlimento);

			// Eliminamos la referencia en retiradas si existiera
			eliminarRetiradasPorAlimento(idAlimento);

			// Eliminamos el alimento en BBDD
			String sqlStr = "DELETE FROM alimentos WHERE idAlimento = " + idAlimento;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// ALMACEN
	// Método para recuperar el listado de alimentos en almacén
	public static ArrayList<AlimentoAlmacen> obtenerAlimentosAlmacen() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<AlimentoAlmacen> alimentosAlmacen = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM almacen a, alimentos al WHERE al.idAlimento = a.idAlimentoFK AND a.cantidadTotalAlmacen != 0";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				AlimentoAlmacen alimentoAlmacen = new AlimentoAlmacen(rs.getInt("idAlmacen"),
						rs.getInt("cantidadTotalAlmacen"), rs.getInt("idAlimento"), rs.getString("nombreAlimento"),
						rs.getString("tipoAlimento"), rs.getString("marcaAlimento"), rs.getString("cantidadAlimento"),
						rs.getBoolean("caducidadAlimento"));
				alimentosAlmacen.add(alimentoAlmacen);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return alimentosAlmacen;
	}

	// Método para actualizar stock de almacén al hacer una donación
	private static void añadirAlimentosAlmacen(String idAlimentoFK, String cantidadAlimentoDonada) {
		
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();
			
			// Comprobamos si el alimento ya existia en el almacen
			
			String sqlStr = "SELECT * FROM almacen WHERE idAlimentoFK = " + idAlimentoFK;
			ResultSet rs = stmt.executeQuery(sqlStr);
			
			// Si existe lo actualizamos
			if (rs.next()) {
				// Cambiamos el numero de alimentos del almacen en BBDD
				String sqlUpdateStr = "UPDATE almacen SET cantidadTotalAlmacen = (cantidadTotalAlmacen + " + cantidadAlimentoDonada + ") "
						+ "WHERE idAlimentoFK = " + idAlimentoFK;
				stmt.executeUpdate(sqlUpdateStr);

			}
			// Si no existe creamos la nueva relación
			else {
				String sqlInsertStr = "INSERT INTO almacen (cantidadTotalAlmacen, idAlimentoFK) "
						+ "VALUES ('" + cantidadAlimentoDonada + "', '" + idAlimentoFK + "')";
				stmt.executeUpdate(sqlInsertStr);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}
	
	// Método para actualizar stock de almacén al hacer una retirada
	private static void retirarAlimentosAlmacen(String idAlmacen, String cantidadAlimentoRetirada) {
		
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Cambiamos el numero de alimentos del almacen en BBDD
			String sqlStr = "UPDATE almacen SET cantidadTotalAlmacen = (cantidadTotalAlmacen - " + cantidadAlimentoRetirada + ") "
					+ "WHERE idAlmacen = " + idAlmacen;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}
	
	// Método para eliminar stock de almacén si se elimina alimento de BBDD
	public static void eliminarAlimentoAlmacenPorAlimento(String idAlimentoFK) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos el alimento en BBDD
			String sqlStr = "DELETE FROM almacen WHERE idAlimentoFK = " + idAlimentoFK;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// DONACIONES
	// Método para eliminar donaciones si se elimina alimento de BBDD
	public static void eliminarDonacionesPorAlimento(String idAlimentoFK) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la donación en BBDD
			String sqlStr = "DELETE FROM donaciones WHERE idAlimentoFK = " + idAlimentoFK;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar donaciones si se elimina donante de BBDD
	public static void eliminarDonacionesPorDonante(String idDonanteFK) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la donación en BBDD
			String sqlStr = "DELETE FROM donaciones WHERE idDonanteFK = " + idDonanteFK;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para dar de alta una donación
		public static void crearDonacion(String cantidadAlimentoDonacion, String idAlimentoFK, String idDonanteFK) {

			Connection conn = null;
			Statement stmt = null;

			try {

				if (pool == null) {
					iniciarPool();
				}

				conn = pool.getConnection();
				stmt = conn.createStatement();

				// Antes de añadir la donación en BBDD, sumamos los alimentos donados en el almacen
				añadirAlimentosAlmacen(idAlimentoFK, cantidadAlimentoDonacion);
				
				// Calculamos la fecha de la operación
				Date fechaDonacion = new Date();
				java.sql.Date fechaDonacionSQL = new java.sql.Date(fechaDonacion.getTime());

				// Creamos el donacion en BBDD
				String sqlStr = "INSERT INTO donaciones (cantidadAlimentoDonacion, fechaDonacion, idAlimentoFK, idDonanteFK) "
						+ "VALUES ('" + cantidadAlimentoDonacion + "', '" + fechaDonacionSQL + "', '" + idAlimentoFK
						+ "', '" + idDonanteFK + "')";
				stmt.executeUpdate(sqlStr);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				// Cerramos conexion
				liberarConexión(conn, stmt);
			}
		}

		// Método para recuperar el listado de donaciones
		public static ArrayList<DonacionDonanteAlimento> obtenerDonaciones() {
			// Creamos objetos para la conexión
			Connection conn = null;
			Statement stmt = null;

			ArrayList<DonacionDonanteAlimento> donaciones = new ArrayList<>();

			try {

				if (pool == null) {
					iniciarPool();
				}

				conn = pool.getConnection();
				stmt = conn.createStatement();

				// Ejecutar las sentencias
				String sqlStr = "SELECT * FROM donaciones ds, alimentos al, donantes d WHERE al.idAlimento = ds.idAlimentoFK AND d.idDonante = ds.idDonanteFK ORDER BY fechaDonacion DESC";
				ResultSet rs = stmt.executeQuery(sqlStr);
				while (rs.next()) {
					DonacionDonanteAlimento donacion = new DonacionDonanteAlimento(rs.getInt("idDonacion"),
							rs.getInt("cantidadAlimentoDonacion"), rs.getDate("fechaDonacion"), rs.getInt("idAlimento"),
							rs.getString("nombreAlimento"), rs.getString("tipoAlimento"), rs.getString("marcaAlimento"),
							rs.getString("cantidadAlimento"), rs.getBoolean("caducidadAlimento"), rs.getInt("idDonante"),
							rs.getString("nombreDonante"), rs.getString("tipoDonante"),
							rs.getString("identificadorDonante"));
					donaciones.add(donacion);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				// Cerramos conexion
				liberarConexión(conn, stmt);
			}
			return donaciones;
		}	
	
	// RETIRADAS
	// Método para eliminar retiradas si se elimina alimento de BBDD
	public static void eliminarRetiradasPorAlimento(String idAlimentoFK) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la retirada en BBDD
			String sqlStr = "DELETE FROM retiradas WHERE idAlimentoFK = " + idAlimentoFK;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para eliminar retiradas si se elimina beneficiario de BBDD
	public static void eliminarRetiradasPorBeneficiario(String idBeneficiarioFK) {
		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Eliminamos la retirada en BBDD
			String sqlStr = "DELETE FROM retiradas WHERE idBeneficiarioFK = " + idBeneficiarioFK;
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para dar de alta una retirada
	public static void crearRetirada(String cantidadAlimentoRetirada, String idAlmacen, String idBeneficiarioFK, String idAlimentoFK) {

		Connection conn = null;
		Statement stmt = null;

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Antes de realizar la retirada, reducimos en el almacen el numero de alimentos retirados
			retirarAlimentosAlmacen(idAlmacen, cantidadAlimentoRetirada);
			
			// Calculamos la fecha de la operación
			Date fechaRetirada = new Date();
			java.sql.Date fechaRetiradaSQL = new java.sql.Date(fechaRetirada.getTime());

			// Creamos la retirada en BBDD
			String sqlStr = "INSERT INTO retiradas (cantidadAlimentoRetirada, fechaRetirada, idAlimentoFK, idBeneficiarioFK) "
					+ "VALUES ('" + cantidadAlimentoRetirada + "', '" + fechaRetiradaSQL + "', '" + idAlimentoFK
					+ "', '" + idBeneficiarioFK + "')";
			stmt.executeUpdate(sqlStr);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
	}

	// Método para recuperar el listado de retiradas
	public static ArrayList<RetiradaBeneficiarioAlimento> obtenerRetiradas() {
		// Creamos objetos para la conexión
		Connection conn = null;
		Statement stmt = null;

		ArrayList<RetiradaBeneficiarioAlimento> retiradas = new ArrayList<>();

		try {

			if (pool == null) {
				iniciarPool();
			}

			conn = pool.getConnection();
			stmt = conn.createStatement();

			// Ejecutar las sentencias
			String sqlStr = "SELECT * FROM retiradas re, alimentos al, beneficiarios b WHERE al.idAlimento = re.idAlimentoFK AND b.idBeneficiario = re.idBeneficiarioFK ORDER BY fechaRetirada DESC";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while (rs.next()) {
				RetiradaBeneficiarioAlimento donacion = new RetiradaBeneficiarioAlimento(rs.getInt("idRetirada"),
						rs.getInt("cantidadAlimentoRetirada"), rs.getDate("fechaRetirada"), rs.getInt("idAlimento"),
						rs.getString("nombreAlimento"), rs.getString("tipoAlimento"), rs.getString("marcaAlimento"),
						rs.getString("cantidadAlimento"), rs.getBoolean("caducidadAlimento"),
						rs.getInt("idBeneficiario"), rs.getString("nombreBeneficiario"),
						rs.getString("apellidosBeneficiario"), rs.getString("dniBeneficiario"));
				retiradas.add(donacion);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// Cerramos conexion
			liberarConexión(conn, stmt);
		}
		return retiradas;
	}
}