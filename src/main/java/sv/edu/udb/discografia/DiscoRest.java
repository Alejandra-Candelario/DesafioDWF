package sv.edu.udb.discografia;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sv.edu.udb.model.Artistas;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.Discos;
import sv.edu.udb.model.DiscosDAO;

@Path("discos")
public class DiscoRest {

	DiscosDAO discosDAO = new DiscosDAO();
	ArtistasDAO artistasDAO = new ArtistasDAO();

	// LISTAR

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscos() throws SQLException {

		List<Discos> discos = discosDAO.findAll();
		return Response.status(200).entity(discos).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscosById(@PathParam("id") int id) throws SQLException {

		Discos discos = discosDAO.findById(id);
		if (discos == null) {
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Disco no encontrado")
					.build();

		}

		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(discos).build();

	}

	// INSERTAR

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDiscos(
			@FormParam("name") String name, 
			@FormParam("canciones") int canciones,
			@FormParam("precio") Double precio,
			@FormParam("artista_id") int artista_id
			) throws SQLException {

		Discos discos = new Discos();

		if (name == null || name.isEmpty()) {
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("El nombre del disco no es valido").build();
		}
		
		if(artistasDAO.findById(artista_id)==null){
			 return Response.status(400)
			 .header("Access-Control-Allow-Origin", "*")
			 .entity("El artista no corresponde a ninguna existencia")
			 .build();
			 }

		discos.setName(name);
		discos.setCanciones(canciones);
		discos.setPrecio(precio);
		discos.setArtistId(artista_id);
		discosDAO.insert(discos);

		return Response.status(201)
				.header("Access-Control-Allow-Origin", "*")
				.entity(discos)
				.build();
	}

	// DELETE

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{id}")
	public Response eliminarDisco(@PathParam("id") int id) throws SQLException {
		Discos disco = discosDAO.findById(id);
		if (disco == null) {
			return Response.status(404)
					.entity("Disco no corresponde a ninguna existencia")
					.header("Access-Control-Allow-Origin", "*").build();
		}

		discosDAO.delete(id);

		return Response.status(204)
				.header("Access-Control-Allow-Origin", "*")
				.build();
	}

	// ACTUALIZAR
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateDisco(
			@PathParam("id") int id, 
			@FormParam("name") String name,
			@FormParam("canciones") int canciones,
			@FormParam("precio") Double precio,
			@FormParam("artist_id") int artist_id) throws SQLException {

		Discos discos = discosDAO.findById(id);

		if (discos == null) {
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Disco no corresponde a ninguna existencia")
					.build();
		}

		if (artist_id != discos.getArtistId() && discosDAO.findById(artist_id) == null) {
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("El ID del artista no es válido").build();
		}

		discos.setName(name);
		discos.setCanciones(canciones);
		discos.setPrecio(precio);
		discos.setArtistId(artist_id);
		discosDAO.update(discos);

		return Response.status(200).header("Access-Control-Allow-Origin", "*").entity(discos).build();
	}
}
