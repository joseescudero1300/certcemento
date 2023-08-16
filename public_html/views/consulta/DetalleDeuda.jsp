<%@ taglib uri="/WEB-INF/sqltaglib.tld" prefix="database" %>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Types"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="javax.sql.DataSource"%>
<%@ page import="oracle.jdbc.OracleTypes"%>
<%@ page import="java.sql.CallableStatement"%>
<%
        String v_id = request.getParameter("id");
	int i = 0;       
        if(v_id!=null)
        {
                DataSource ds2 = null;
                Connection cn2 = null;
                Statement st2 = null;
                String respuesta = "",mensaje="";
                ResultSet rs=null;
                try
                  { 
                    InitialContext ic2 = new InitialContext();
                            ds2 = (DataSource)ic2.lookup("jdbc/adeudo");
                            cn2 = (Connection)ds2.getConnection();
                            st2 = cn2.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);        
                            CallableStatement call = null;
                            call = cn2.prepareCall( "{ ? = call pkg_adeudo_tributario.obtiene_detalle_deuda( ?,?,? ) }" );
                            call.registerOutParameter( 1, OracleTypes.CURSOR );                    
                            call.setString( 2, v_id );
                            call.registerOutParameter( 3, OracleTypes.VARCHAR );
                            call.registerOutParameter( 4, OracleTypes.VARCHAR );
                            call.execute();        
                            rs = (ResultSet) call.getObject( 1 );    
                            respuesta= (String) call.getObject( 3 );
                            mensaje= (String) call.getObject( 4 );
               
                    if(respuesta.equals("OK"))
                    {
                        if( !(rs == null || !rs.next() )) {
                            do {
                                String numero = rs.getString(1)+"/"+rs.getString(2) + "/" + rs.getString(3);
                                i++;
                                if(i==1)
                                {
                                    %>
                                    <table id="detalle" class="lista table table-striped table-bordered dt-responsive" cellspacing="0" width="100%">
                                    <%--caption><%=titulo%></caption>--%>
                                    <thead>	
                                    <tr class="ok">
                                            <th>N&ordm;</th>
                                            <th>Tipo Deuda</th>
                                            <th>Descripci&oacute;n</th>
                                            <th>Concepto</th>
                                            <th>Monto</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                }   
                                %>
                                    <tr class="ok">
                                            <th><%=i%>&ordm;</th>                 
                                            <td><%=rs.getString("tipo_deuda")%></td>
                                            <td><%=rs.getString("deuda_desc")%></td>
                                            <td><%=rs.getString("concepto")%></td>
                                            <td class="text-right"><%=rs.getString("monto")%></td>
                                    </tr> 
                                <%
                            } while (rs.next());
                            if(i==0){%>
                            
                                <h4 align="justify"><font color="blue"><%=mensaje%></font></h4>
                                  
                            <%      }
                                    else
                                    {
                                    %>
                                   
                                    </tbody>
                                    </table>
                                    <%
                                    }
                        }
                    }
                } catch (SQLException e) {
                    throw new Exception("Lotes: " + e.getMessage() + "(" + e.getErrorCode() + ")");
                } finally {
                    try {            
                        if (rs != null) {
                            rs.close();
                        }
                        rs = null;
                        if (cn2 != null) {
                            cn2.close();
                        }
                        cn2 = null;
        
                    } catch (SQLException e) {
                        ;
                    }
                }
      }
      else
      {
                %>
                            
                                <h4 align="justify"><font color="blue">No se puede encontrar el detalle solicitado</font></h4>
                                  
                <% 
      }
%>
