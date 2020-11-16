package Entidad;

public class Cuenta {
	String CBU;
	String CUIL;
	String FechaCreacion;
	int IdTipoCuenta;
	int NroCuenta;
	float Saldo;
	boolean Estado;
	
	public Cuenta() {
		super();
	}
	public String getCBU() {
		return CBU;
	}
	public void setCBU(String cBU) {
		CBU = cBU;
	}
	public String getCUIL() {
		return CUIL;
	}
	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}
	public String getFechaCreacion() {
		return FechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}
	public int getIdTipoCuenta() {
		return IdTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		IdTipoCuenta = idTipoCuenta;
	}
	public int getNroCuenta() {
		return NroCuenta;
	}
	public void setNroCuenta(int nroCuenta) {
		NroCuenta = nroCuenta;
	}
	public float getSaldo() {
		return Saldo;
	}
	public void setSaldo(float saldo) {
		Saldo = saldo;
	}
	public boolean isEstado() {
		return Estado;
	}
	public void setEstado(boolean estado) {
		Estado = estado;
	} 
	
	
	
}
