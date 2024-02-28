package sv.edu.udb.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DiscosDAO extends AppConnection{
	
	/**
	 * This method inserts into Discos table a new row. ;)
	 * 
	 * @param discos
	 * @throws SQLException
	 */

	// INSERTAR

	public void insert(Discos disco) throws SQLException {
		connect();

		pstmt = conn.prepareStatement("insert into discos (nombre_disco, id_artista ,numero_canciones, precio) values(?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		//pstmt.setInt(1, disco.getId());
		pstmt.setString(1, disco.getName());
		pstmt.setInt(2, disco.getArtistId());
		pstmt.setInt(3, disco.getCanciones());
		pstmt.setDouble(4, disco.getPrecio());
		pstmt.executeUpdate();

		// obteniendo el ultimo id generado
		ResultSet keys = pstmt.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);

		disco.setId(id);
		close();
	}

	/**
	 * Update all fields from artistas table using its id
	 * 
	 * @param discos
	 * @throws SQLException
	 */

	// MODIFICAR

	public void update(Discos disco) throws SQLException {
		connect();

		pstmt = conn
				.prepareStatement("update discos set nombre_disco = ? , id_artista = ? , numero_canciones = ? , precio = ? where id_disco = ? ");
		pstmt.setString(1, disco.getName());
		pstmt.setInt(2, disco.getArtistId());
		pstmt.setInt(3, disco.getCanciones());
		pstmt.setDouble(4, disco.getPrecio());
		pstmt.setInt(5, disco.getId());
		pstmt.executeUpdate();
		close();
	}

	/**
	 * Deletes a Discos by id
	 * 
	 * @param id
	 */

	// ELIMINAR

	public void delete(int id) throws SQLException {
		connect();
		pstmt = conn.prepareStatement("delete from discos where id_disco = ?");
		pstmt.setInt(1, id);
		pstmt.execute();
		close();
	}

	/**
	 * Returns the list of Discos from table.
	 * 
	 * @return
	 * @throws SQLException
	 */

	// LISTAR

	public ArrayList<Discos> findAll() throws SQLException {
		connect();
		stmt = conn.createStatement();
		resultSet = stmt.executeQuery("select id_disco, nombre_disco, id_artista, numero_canciones, precio from discos");
		ArrayList<Discos> discos = new ArrayList();

		while (resultSet.next()) {
			Discos tmp = new Discos();
			tmp.setId(resultSet.getInt(1));
			tmp.setName(resultSet.getString(2));
			tmp.setArtistId(resultSet.getInt(3));
			tmp.setCanciones(resultSet.getInt(4));
			tmp.setPrecio(resultSet.getDouble(5));
			discos.add(tmp);
		}

		close();

		return discos;
	}

	/**
	 * Find a discos and returns it using the discos id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	
	//BUSCAR
	
	public Discos findById(int id) throws SQLException{

		 Discos discos = null;

		 connect();
		 pstmt = conn.prepareStatement("select id_disco, nombre_disco, id_artista, numero_canciones, precio from discos where id_disco = ?");
		 pstmt.setInt(1, id);

		 resultSet = pstmt.executeQuery();

		 while(resultSet.next()){
		 discos = new Discos();
		 discos.setId(resultSet.getInt(1));
		 discos.setName(resultSet.getString(2));
		 discos.setArtistId(resultSet.getInt(3));
		 discos.setCanciones(resultSet.getInt(4));
		 discos.setPrecio(resultSet.getDouble(5));
		 }

		 close();
		 return discos;
		 }
	
	
	
	///////////////
	public ArrayList<Discos> findByArtistaId(int artistaId) throws SQLException{
		 ArrayList<Discos> discos = new ArrayList();
		    connect();

		    try {
		        pstmt = conn.prepareStatement("SELECT id_disco, nombre_disco, id_artista, numero_canciones, precio FROM discos WHERE id_artista = ?");
		        pstmt.setInt(1, artistaId);
		        resultSet = pstmt.executeQuery();

		        while (resultSet.next()) {
		            Discos disco = new Discos();
		            disco.setId(resultSet.getInt("id_disco"));
		            disco.setName(resultSet.getString("nombre_disco"));
		            disco.setArtistId(resultSet.getInt("id_artista"));
		            disco.setCanciones(resultSet.getInt("numero_canciones"));
		            disco.setPrecio(resultSet.getDouble("precio"));
		            discos.add(disco);
		        }
		    } finally {
		        close();
		    }

		    return discos;
	}

}
