package Entidad;

public class TipoDeCuenta {

	private int Id;
	private String Descripcion;
	public TipoDeCuenta(int id, String descripcion) {
		super();
		Id = id;
		Descripcion = descripcion;
	}
	public TipoDeCuenta() {
		super();
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	
	
}
