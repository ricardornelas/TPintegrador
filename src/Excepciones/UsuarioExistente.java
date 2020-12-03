package Excepciones;

public class UsuarioExistente extends RuntimeException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Usuario ya existente";
	}
	
}
