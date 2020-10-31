package entidad;

public class Usuario {
	
	private String usuario;
	private Cliente cliente;
	private Empleado empleado;
	private String password;
	private boolean admin;
	
	public Usuario() {}

	public Usuario(String usuario, Cliente cliente, Empleado empleado, String password, boolean admin) {
		super();
		this.usuario = usuario;
		this.cliente = cliente;
		this.empleado = empleado;
		this.password = password;
		this.admin = admin;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", cliente=" + cliente + ", empleado=" + empleado + ", password="
				+ password + ", admin=" + admin + "]";
	}
	
	
	
}
