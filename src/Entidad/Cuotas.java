package Entidad;

public class Cuotas {
	
	private int IdPrestamo;
	private int NroCuota;
	private boolean Pagado;
	private String FechaDePago;
	private String FechaPagado;
	
	public Cuotas() {
		
	}
	
	public int getIdPrestamo() {
		return IdPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		IdPrestamo = idPrestamo;
	}

	public int getNroCuota() {
		return NroCuota;
	}

	public void setNroCuota(int nroCuota) {
		NroCuota = nroCuota;
	}

	public boolean getPagado() {
		return Pagado;
	}

	public void setPagado(boolean pagado) {
		Pagado = pagado;
	}

	public String getFechaDePago() {
		return FechaDePago;
	}

	public void setFechaDePago(String fechaDePago) {
		FechaDePago = fechaDePago;
	}

	public String getFechaPagado() {
		return FechaPagado;
	}

	public void setFechaPagado(String fechaPagado) {
		FechaPagado = fechaPagado;
	}



	
}
