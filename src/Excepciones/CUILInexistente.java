package Excepciones;

public class CUILInexistente extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "No se ha encontrado el CUIL ingresado";
	}
	
}
