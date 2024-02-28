package sv.edu.udb.model;

public class Artistas {
	
		private int id;
		private String nombre;
		private String descripcion;
		
		private Discos disco;

		/**
		 * return the id
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
		 * @return the nombre
		 */
		public String getNombre() {
			return nombre;
		}

		/**
		 * @param nombre the nombre to set
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		/**
		 * return the descripcion
		 */
		
		public String getDescripcion() {
			return descripcion;
		}

		/**
		 * @param descripcion the descripcion to set
		 */
		
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		
		/**
		 * @return the disco
		 */
		 public Discos getDisk() {
		 return disco;
		 }
		 /**
		 * @param disco the disco to set
		 */
		 public void setDisk(Discos disco) {
		 this.disco = disco;
		 }
	}

