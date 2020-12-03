package Entidad;


public class Prestamos {


	private int IdPrestamo;	
	private String Cbu;
	private String Fecha;
	private float ImpAPagar;
	private float ImpPedido;
	private float MontoXMes;
	private int Cuotas;
	private boolean Autorizado;
	private boolean Pagado;
	
	public Prestamos() {
		
	}
	
	public int getIdPrestamo() {
		return IdPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}

	public String getCbu() {
		return Cbu;
	}

	public void setCbu(String cbu) {
		Cbu = cbu;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public float getImpAPagar() {
		return ImpAPagar;
	}

	public void setImpAPagar(float impAPagar) {
		ImpAPagar = impAPagar;
	}

	public float getImpPedido() {
		return ImpPedido;
	}

	public void setImpPedido(float impPedido) {
		ImpPedido = impPedido;
	}

	public float getMontoXMes() {
		return MontoXMes;
	}

	public void setMontoXMes(float montoXMes) {
		MontoXMes = montoXMes;
	}

	public int getCuotas() {
		return Cuotas;
	}

	public void setCuotas(int cuotas) {
		Cuotas = cuotas;
	}

	public boolean getAutorizado() {
		return Autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		Autorizado = autorizado;
	}

	public boolean getPagado() {
		return Pagado;
	}

	public void setPagado(boolean pagado) {
		Pagado = pagado;
	}


	
	
	
}
