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
import sv.edu.udb.model.Discos;
import sv.edu.udb.model.ArtistasDAO;
import sv.edu.udb.model.DiscosDAO;

@Path("artistas")
public class ArtistasRest {

	ArtistasDAO artistasDAO = new ArtistasDAO();
	DiscosDAO discosDAO = new DiscosDAO();

	
	//LISTAR
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtistas() throws SQLException {

		List<Artistas> artistas = artistasDAO.findAll();
		return Response.status(200).entity(artistas).build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}/discos")
	public Response getArtistasDisk(@PathParam("id") int id) throws SQLException {

		List<Discos> discos = discosDAO.findByArtistaId(id);
		 return Response.status(200)
		            .header("Access-Control-Allow-Origin", "*")
		            .entity(discos)
		            .build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArtistasById(@PathParam("id") int id) throws SQLException {

		Artistas artistas = artistasDAO.findById(id);
		if (artistas == null) {
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
					.entity("Artista no encontrado")
					.build();
		}
		
	

		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(artistas)
				.build();
	}
	
	
	// INSERTAR

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertArtista(
			@FormParam("nombre") String nombre,
			@FormParam("descripcion") String descripcion
			)throws SQLException {

		//Artistas artistas = new Artistas();

		if ( nombre == null || nombre.isEmpty()) {
			return Response.status(400)
					.header("Access-Control-Allow-Origin", "*")
					.entity("El artista no es valido, debe escribir un nombre").build();
		}
		
		Artistas artistas = new Artistas();
		artistas.setNombre(nombre);
		artistas.setDescripcion(descripcion);
		
		artistasDAO.insert(artistas);

		return Response.status(201)
				.header("Access-Control-Allow-Origin", "*")
				.entity(artistas)
				.build();
	}

	// DELETE

	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response eliminarArtista(@PathParam("id") int id) throws SQLException {
		Artistas artista = artistasDAO.findById(id);
		if (artista == null) {
			return Response.status(404)
					.entity("El artista no existe")
					.header("Access-Control-Allow-Origin", "*")
					.build();
		}

		artistasDAO.delete(id);

		return Response.status(204)
				.header("Access-Control-Allow-Origin", "*")
				.entity(artista).build();
	}

	
	//ACTUALIZAR
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response updateArtista
	(@PathParam("id") int id, 
			@FormParam("nombre") String nombre,
			@FormParam("descripcion") String descripcion
			
			)throws SQLException {

		Artistas artista = artistasDAO.findById(id);

		if (artista == null) {
			return Response.status(404)
					.header("Access-Control-Allow-Origin", "*")
				    .entity("Artista no existe")
				    .build();

		}

		artista.setNombre(nombre);
		artista.setDescripcion(descripcion);
		artistasDAO.update(artista);

		return Response.status(204)
				.header("Access-Control-Allow-Origin", "*")
				.entity(artista)
				.build();
	}
}