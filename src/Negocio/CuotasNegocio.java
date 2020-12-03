package Negocio;

import java.util.ArrayList;

import Entidad.Cuotas;

public interface CuotasNegocio {

	public boolean AgregarCuota(Cuotas cuota);
	public ArrayList<Cuotas> TodasLasCuotas();
	public ArrayList<Cuotas> TodasLasCuotas(int IdPrestamo);
	public int CuotasPagadas(int Id);
	public boolean RegistrarPago(int IdPrestamo,int NroCuota,float Monto,String CBU);

}
