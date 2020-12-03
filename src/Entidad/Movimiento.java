package Entidad;

public class Movimiento {
	
	private int Codigo_Mov;
	private String CBURemitente_Mov;
	private String CBUDestinatario_Mov;
	private float Monto_Mov;
	private int IdTipoMovimiento_Mov;
	private String Fecha;
	
	
	public Movimiento() {
		
	}
	
	public int getCodigo_Mov() {
		return Codigo_Mov;
	}
	public void setCodigo_Mov(int codigo_Mov) {
		Codigo_Mov = codigo_Mov;
	}
	public String getCBURemitente_Mov() {
		return CBURemitente_Mov;
	}
	public void setCBURemitente_Mov(String cBURemitente_Mov) {
		CBURemitente_Mov = cBURemitente_Mov;
	}
	public String getCBUDestinatario_Mov() {
		return CBUDestinatario_Mov;
	}
	public void setCBUDestinatario_Mov(String cBUDestinatario_Mov) {
		CBUDestinatario_Mov = cBUDestinatario_Mov;
	}
	public float getMonto_Mov() {
		return Monto_Mov;
	}
	public void setMonto_Mov(float monto_Mov) {
		Monto_Mov = monto_Mov;
	}
	public int getIdTipoMovimiento_Mov() {
		return IdTipoMovimiento_Mov;
	}
	public void setIdTipoMovimiento_Mov(int idTipoMovimiento_Mov) {
		IdTipoMovimiento_Mov = idTipoMovimiento_Mov;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	
	
	
}
