package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dao.PrestamosDao;
import Entidad.Prestamos;
import Entidad.Cliente;


public class PrestamosDaoImpl implements PrestamosDao{

	private static final String Insertar = "INSERT INTO prestamos (CBU_Pre, "
			+ "ImportePedido_Pre, Fecha_Pre, Cuotas_Pre, MontoXMes_Pre) "
			+ "VALUES(?, ?, ?, ?, ?)";
	private static final String Listar = "SELECT IdPrestamo_Pr, CBU_Pre, ImporteAPagar_Pre, ImportePedido_Pre, MontoXMes_Pre, Cuotas_Pre, NroCuota_Cuo"
			+ "FROM prestamos INNER JOIN cuotas on IdPrestamo_Cuo = IdPrestamo_Pr WHERE EstaAutorizado_Pre  = 1";

	private static final String PrestamosAutorizar ="select IdPrestamo_Pre, CBU_Pre, ImportePedido_Pre, ImporteAPagar_Pre, Cuotas_Pre, MontoXMes_Pre, Fecha_Pre from prestamos where EstaAutorizado_Pre = 0 and EstaPagado_Pre = 0";
	private static final String RechazarPrestamo = "update bdintegrador.prestamos set EstaAutorizado_Pre = 0 where IdPrestamo_Pre = ?";
	private static final String AutorizarPrestamo = "update bdintegrador.prestamos set EstaAutorizado_Pre = 1 where IdPrestamo_Pre = ?";
	private static final String ListaPrestamos = "Select * from prestamos";
	
	//Revisar Listar
	
	
	public boolean SolicitarPrestamo(Prestamos prestamo) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean SeInserto = false;
		try
		{
			statement = conexion.prepareStatement(Insertar);
			
			
			statement.setString(1, prestamo.getCbu());
			statement.setFloat(2, prestamo.getImpPedido());
			statement.setString(3, prestamo.getFecha());
			statement.setFloat(4, prestamo.getMontoXMes());
			statement.setInt(5, prestamo.getCuotas());

			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				SeInserto= true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return SeInserto;
	}
	
	public ResultSet LeerMisPrestamos(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conexion = Conexion.getConexion().getSQLConexion();
		
		Statement st;
		
	try {
		st = conexion.createStatement();
		ResultSet result = st.executeQuery(Listar);
		return result;		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	}
	
	public boolean AprobarPrestamo(Prestamos prest) {
	
	  boolean prestamo = false;
	  
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try 
        {
            statement = conexion.prepareStatement(AutorizarPrestamo);
            String autorizar = Integer.toString(prest.getIdPrestamo());
            statement.setString(1, autorizar);

            if(statement.executeUpdate() > 0)
            {
                conexion.commit();
                prestamo = true;
            }
        } 
        
        catch (SQLException e) 
        {
            e.printStackTrace();
      
        }
        
        return prestamo;
}

	public boolean RechazarPrestamo(Prestamos prest) {
	
	  boolean rechazo = false;
	  
      PreparedStatement statement;
      Connection conexion = Conexion.getConexion().getSQLConexion();

      try 
      {
          statement = conexion.prepareStatement(RechazarPrestamo);
          String borrar = Integer.toString(prest.getIdPrestamo());
          statement.setString(1, borrar);

          if(statement.executeUpdate() > 0)
          {
              conexion.commit();
              rechazo = true;
          }
      } 
      
      catch (SQLException e) 
      {
          e.printStackTrace();
  
      }
      
      return rechazo;

}

	public ArrayList<Prestamos> listarPrestamosCliente(Cliente cliente) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Prestamos> prestamosCliente = new ArrayList<Prestamos>();
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			Statement st = conexion.createStatement();

			ResultSet rs = st.executeQuery("select * from prestamos p INNER JOIN cuentas c on p.CBU_Pre = c.CBU_Cue WHERE c.CUIL_Cue = '"+cliente.getCuil()+"'");
			Prestamos prestamo = new Prestamos();
			while (rs.next()) {
				
				// valido que los prestamos esten activos //
				/*
				 * if(rs.getBoolean("Estado_Prestamo") == true && rs.getBoolean("Estado_Solicitud") == true) {}
				 */
				
					prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
					prestamo.setCbu(rs.getString("Cbu"));
					prestamo.setFecha(rs.getString("Fecha"));
					prestamo.setImpAPagar(rs.getFloat("ImpAPagar"));
					prestamo.setImpPedido(rs.getFloat("Importe_Total_Pagar"));
					prestamo.setCuotas(rs.getInt("Cuotas"));
					prestamo.setMontoXMes(rs.getFloat("MontoXMes"));
					prestamosCliente.add(prestamo);
					
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return prestamosCliente;
	}

	public ArrayList<Prestamos> ListarPrestamosAutorizar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Prestamos> listaprestamos = new ArrayList<Prestamos>();
		try {
			Connection conexion = Conexion.getConexion().getSQLConexion();
			Statement st = conexion.createStatement();

			ResultSet rs = st.executeQuery(PrestamosAutorizar);
			while (rs.next()) {
				Prestamos prestamo = new Prestamos();
				prestamo.setIdPrestamo(rs.getInt("IdPrestamo"));
				prestamo.setCbu(rs.getString("Cbu"));
				prestamo.setFecha(rs.getString("Fecha"));
				prestamo.setImpAPagar(rs.getFloat("ImpAPagar"));
				prestamo.setImpPedido(rs.getFloat("Importe_Total_Pagar"));
				prestamo.setCuotas(rs.getInt("Cuotas"));
				prestamo.setMontoXMes(rs.getFloat("MontoXMes"));
			
				listaprestamos.add(prestamo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return listaprestamos;
	}

	public boolean actualizarPrestamo(Prestamos prestamo) {
	       PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean actualizar = false;
        try
        {
            String query = "update bdbancos.prestamos set Saldo_Restante = ?, Cuotas_Restantes = ? where Nro_Prestamo = ?";
            statement = conexion.prepareStatement(query);
          //  statement.setDouble(1, prestamo.getSaldo_Restante());
           // statement.setInt(2, prestamo.getCuotas_Restantes());
         //   statement.setInt(3, prestamo.getNro_Prestamo());

            if(statement.executeUpdate() > 0)
            {
                conexion.commit();
                actualizar = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return actualizar;
	}

	public boolean darBajaPrestamo(Prestamos prestamo) {
		   PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean actualizar = false;
        try
        {
            String query = "update bdintegrador.prestamos set EstaPagado_Pre = ? where IdPrestamo_Pre = ?";
            statement = conexion.prepareStatement(query);
            statement.setBoolean(1, prestamo.getPagado());
            statement.setInt(2, prestamo.getIdPrestamo());

            if(statement.executeUpdate() > 0)
            {
                conexion.commit();
                actualizar = true;
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return actualizar;
	}


	

}
