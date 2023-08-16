package bo.gob.aduana.bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
    protected InitialContext ic;
    protected DataSource ds;
    protected Connection cn;
    protected Statement st = null;

    public Conexion() throws Exception {
        try {
            ic = new InitialContext();
            ds = (DataSource)ic.lookup("jdbc/certificacion");
            //ds = (DataSource)ic.lookup("jdbc/timbres");
            cn = ds.getConnection();
            st = cn.createStatement();
            //cn.setAutoCommit(false);
        } catch (NamingException ne) {
            throw new Exception("Fuente de datos no se encuentra disponible.");
        } catch (SQLException sqle) {
            throw new Exception("No es posible establecer la conexi&oacute;n con la base de datos");
        }
    }

    public void dispose() {
        try {
            if (st != null)
                st.close();

            if (cn != null)
                cn.close();

            cn = null;
            st = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
