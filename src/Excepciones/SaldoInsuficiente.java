package Excepciones;

public class SaldoInsuficiente extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		
		return "Saldo insuficiente en la cuenta para realizar la operacion.";
	}
	
}
