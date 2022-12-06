package Fuentes;

import javax.swing.*;

import Fuentes.Cliente;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Servidor  
	{
		public static void main(String[] args) 
			{
				VentanaServidor VentanaServidor = new VentanaServidor();
				VentanaServidor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}	
	}

class VentanaServidor extends JFrame implements Runnable
	{
		private	JTextArea areatexto;
		
		public VentanaServidor()
			{
				setBounds(1300,350,400,350);
				JPanel pantalla = new JPanel();
				pantalla.setLayout(new BorderLayout());
				areatexto = new JTextArea();
				pantalla.add(areatexto,BorderLayout.CENTER);
				add(pantalla);
				setVisible(true);
				Thread hilo = new Thread(this);
				hilo.start();
			}
			
		public void run()
			{
			String usuario, mensaje;
			Paquete paquete_recibido;
			Connection cn = null;
			try 
				{
			        ServerSocket servidor = new ServerSocket(9999);
					while (true)
						{
							Socket misocket = servidor.accept();
							ObjectInputStream paquete_datos = new ObjectInputStream(misocket.getInputStream());
							paquete_recibido =(Paquete) paquete_datos.readObject();
							usuario = paquete_recibido.getUsuario().toString();
							mensaje = paquete_recibido.getMensaje().toString();
							InetAddress localizacion = misocket.getInetAddress();
							String ipRemota = localizacion.getHostAddress();
							if (mensaje.toLowerCase().equals("nuevo"))
								{
			   					   areatexto.append("\n Ususario " + usuario.toUpperCase() + " conectado");
								}
							else
								{	
								   DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
						           cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "PERSISTENCIA", "geraldine");
						           if (mensaje.equals("Query"))
								      {
								         CallableStatement cst = cn.prepareCall("{call CONSULTA (?,?,?,?,?,?,?,?,?,?,?,?)}");
									     cst.setString(1, paquete_recibido.getCedula().toString());
								         cst.registerOutParameter(2, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(3, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(4, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(5, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(6, java.sql.Types.INTEGER);
									     cst.registerOutParameter(7, java.sql.Types.INTEGER);
								      	 cst.registerOutParameter(8, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(9, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(10, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(11, java.sql.Types.VARCHAR);
									     cst.registerOutParameter(12, java.sql.Types.VARCHAR);
									     cst.execute();
									     paquete_recibido.setPrimernombre(cst.getString(2));
									     paquete_recibido.setSegundonombre(cst.getString(3));
									     paquete_recibido.setEmail(cst.getString(4));
									     paquete_recibido.setFnacim(cst.getString(5));
									     paquete_recibido.setSueldo(Integer.parseInt(cst.getString(6)));
									     paquete_recibido.setComision(Integer.parseInt(cst.getString(7)));
									     paquete_recibido.setDepartamento(cst.getString(8));
									     paquete_recibido.setCargo(cst.getString(9));
									     paquete_recibido.setCedulagerente(cst.getString(10));
									     paquete_recibido.setEstado(cst.getString(11));
									     paquete_recibido.setMensaje(cst.getString(12));
									     cn.close();
									  }
						        else
						           {
						              if (mensaje.equals("Update"))
						        	    {
						        		   CallableStatement cst = cn.prepareCall("{call MODIFICACION (?,?,?,?,?,?,?,?,?,?,?,?)}");
						        		   cst.setString(1, paquete_recibido.getCedula());
						        		   cst.setString(2, paquete_recibido.getPrimernombre());
						        		   cst.setString(3, paquete_recibido.getSegundonombre());
						        		   cst.setString(4, paquete_recibido.getEmail());
						        		   cst.setString(5, paquete_recibido.getFnacim().trim());
						        		   cst.setString(6, String.valueOf(paquete_recibido.getSueldo()));
						        		   cst.setString(7, String.valueOf(paquete_recibido.getComision()));
						        		   cst.setString(8, paquete_recibido.getDepartamento());
						        		   cst.setString(9, paquete_recibido.getCargo());
						        		   cst.setString(10, paquete_recibido.getCedulagerente());
						        		   cst.setString(11, paquete_recibido.getEstado());
									       cst.registerOutParameter(12, java.sql.Types.VARCHAR);
									       cst.execute();
									       paquete_recibido.setMensaje(cst.getString(12));
									       cn.close();
						        	    }
						        	  else
						        	     {
						        		    if (mensaje.equals("Insert"))
							        	       {
							        		      CallableStatement cst = cn.prepareCall("{call INSERTAR (?,?,?,?,?,?,?,?,?,?,?,?)}");
							        		      cst.setString(1, paquete_recibido.getCedula());
							        		      cst.setString(2, paquete_recibido.getPrimernombre());
							        		      cst.setString(3, paquete_recibido.getSegundonombre());
							        		      cst.setString(4, paquete_recibido.getEmail());
							        		      cst.setString(5, paquete_recibido.getFnacim().trim());
							        		      cst.setString(6, String.valueOf(paquete_recibido.getSueldo()));
							        		      cst.setString(7, String.valueOf(paquete_recibido.getComision()));
							        		      cst.setString(8, paquete_recibido.getDepartamento());
							        		      cst.setString(9, paquete_recibido.getCargo());
							        		      cst.setString(10, paquete_recibido.getCedulagerente());
							        		      cst.setString(11, paquete_recibido.getEstado());
							        		      cst.registerOutParameter(12, java.sql.Types.VARCHAR);
							        		      cst.execute();
							        		      paquete_recibido.setMensaje(cst.getString(12));
							        		      cn.close();
							        	       } 
						        		    else
						        		       {
						        			      if (mensaje.equals("Delete"))
						        			         {
						        				        CallableStatement cst = cn.prepareCall("{call BORRADO (?,?,?,?,?)}");
								        		        cst.setString(1, paquete_recibido.getCedula());
								        		        cst.setString(2, paquete_recibido.getDepartamento());
								        		        cst.setString(3, paquete_recibido.getCargo());
								        		        cst.setString(4, paquete_recibido.getEstado());
											            cst.registerOutParameter(5, java.sql.Types.VARCHAR);
											            cst.execute();
											            paquete_recibido.setMensaje(cst.getString(5));
											            cn.close();
						        			         }
						        			   }
						        	     }
						              }
							       Socket enviaDestinatario= new Socket(ipRemota,9090);
							       ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
							       paqueteReenvio.writeObject(paquete_recibido);
							       paqueteReenvio.close();
							       enviaDestinatario.close();
							       misocket.close();
						        }
						}
				}
			catch (IOException | ClassNotFoundException e) 
				{
				   e.printStackTrace();
				} 
			catch (SQLException e) 
			    {
				   e.printStackTrace();
			    }
			}}
			
