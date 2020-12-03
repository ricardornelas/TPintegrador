package Excepciones;

public class DNIExistente extends RuntimeException{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "DNI ya existente";
	}
	
}
