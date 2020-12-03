package Excepciones;

public class LoginIncorrecto extends RuntimeException{
	
	public String getMessage(){
		return "Usuario y/o contraseña incorrectos";
	}
}
