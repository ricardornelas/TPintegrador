package Excepciones;

public class LimiteCuentas extends RuntimeException{

	@Override
	public String getMessage() {
		return "El cliente lleg� al limite de 3 (tres) cuentas";
	}
	
}
