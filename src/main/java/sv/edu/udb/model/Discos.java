package sv.edu.udb.model;

public class Discos {

	private int id;
	private String name;
	private int artistId;
	private int canciones;
	private double precio;

	private Artistas artista;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the artistId
	 */
	public int getArtistId() {
		return artistId;
	}

	/**
	 * @param artistId the artistId to set
	 */
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}

	/**
	 * @return the canciones
	 */
	public int getCanciones() {
		return canciones;
	}

	/**
	 * @param canciones the canciones to set
	 */
	public void setCanciones(int canciones) {
		this.canciones = canciones;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return the artista
	 */
	public Artistas getArtist() {
		return artista;
	}

	/**
	 * @param artista the artista to set
	 */
	public void setArtist(Artistas artista) {
		this.artista = artista;
	}

}
