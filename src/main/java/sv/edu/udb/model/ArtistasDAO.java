package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistasDAO extends AppConnection {
	/**
	 * This method inserts into Artistas table a new row. ;)
	 * 
	 * @param artistas
	 * @throws SQLException
	 */

	// INSERTAR

	public void insert(Artistas artista) throws SQLException {
		connect();

		pstmt = conn.prepareStatement("insert into artistas (nombre_artista,descripcion) values(?,?)",
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, artista.getNombre());
		pstmt.setString(2, artista.getDescripcion());
		pstmt.executeUpdate();

		// obteniendo el ultimo id generado
		ResultSet keys = pstmt.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);

		artista.setId(id);
		close();
	}

	/**
	 * Update all fields from artistas table using its id
	 * 
	 * @param artistas
	 * @throws SQLException
	 */

	// MODIFICAR

	public void update(Artistas artista) throws SQLException {
		connect();

		pstmt = conn
				.prepareStatement("update artistas set nombre_artista = ? , descripcion = ? where id_artista = ? ");
		pstmt.setString(1, artista.getNombre());
		pstmt.setString(2, artista.getDescripcion());
		pstmt.setInt(3, artista.getId());
		pstmt.executeUpdate();
		close();
	}

	/**
	 * Deletes a Artistas by id
	 * 
	 * @param id
	 */

	// ELIMINAR

	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("delete from artistas where id_artista = ?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}

	/**
	 * Returns the list of Artistas from table.
	 * 
	 * @return
	 * @throws SQLException
	 */

	// LISTAR

	public ArrayList<Artistas> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("select id_artista, nombre_artista, descripcion from artistas");
	//	resultSet = stmt.executeQuery("SELECT  A.id_artista, A.nombre_artista, A.descripcion, D.nombre_disco FROM artistas A INNER JOIN discos D \r\n ON A.id_artista = D.id_disco;");
		ArrayList<Artistas> artistas = new ArrayList();

		while (resultSet.next()) {
			Artistas tmp = new Artistas();
			tmp.setId(resultSet.getInt(1));
			tmp.setNombre(resultSet.getString(2));
			tmp.setDescripcion(resultSet.getString(3));
			
			
			//crear objeto discos 
		//    Discos disco = new Discos();
		//	disco.setName(resultSet.getString(4));
		//    tmp.setDisk(disco);
			artistas.add(tmp);
		}

		close();

		return artistas;
	}

	/**
	 * Find a artistas and returns it using the artistas id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	
	//BUSCAR
	
	public Artistas findById(int id) throws SQLException{

		 Artistas artistas = null;

		 connect();
		 pstmt = conn.prepareStatement("select id_artista, nombre_artista,descripcion from artistas where id_artista = ?");
		 pstmt.setInt(1, id);

		 resultSet = pstmt.executeQuery();

		 while(resultSet.next()){
		 artistas = new Artistas();
		 artistas.setId(resultSet.getInt(1));
		 artistas.setNombre(resultSet.getString(2));
		 artistas.setDescripcion(resultSet.getString(3));
		 }

		 close();
		 return artistas;
		 }

}
