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
	
	public Cuenta(String cbu, String cuil,int IdTC,int NroC,float S) {
		super();
		CBU= cbu;
		CUIL= cuil;
		IdTipoCuenta= IdTC;
		NroCuenta=NroC;
		Saldo= S;
		
		
	}
	
	
	public Cuenta(String cBU, String cUIL, String fechaCreacion, int idTipoCuenta, int nroCuenta, float saldo) {
		super();
		CBU = cBU;
		CUIL = cUIL;
		FechaCreacion = fechaCreacion;
		IdTipoCuenta = idTipoCuenta;
		NroCuenta = nroCuenta;
		Saldo = saldo;
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
