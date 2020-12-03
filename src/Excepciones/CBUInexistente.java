package Excepciones;

public class CBUInexistente extends RuntimeException {

	public String getMessage() {
		return "El CBU ingresado no existe";
	}
	
}
