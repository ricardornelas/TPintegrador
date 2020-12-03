package Excepciones;

public class CBUIguales extends RuntimeException {
	
	public String getMessage() {
		return "No se puede hacer una transferencia entre dos mismas cuentas.";
	}
}
