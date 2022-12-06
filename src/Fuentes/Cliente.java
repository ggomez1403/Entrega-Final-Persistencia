package Fuentes;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Cliente 
	{
		public static String wip;
		public static String wusuario;
						
		public static void Ventana0() 
			{
				wusuario = JOptionPane.showInputDialog("Nombre del usuario: ");
				wip      = JOptionPane.showInputDialog("IP del servidor: ");
			}
					
		public static void main(String[] args) throws UnknownHostException 
			{
			    Ventana0();
				Ventana ventana = new Ventana(wip, wusuario);
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
	}

class Paquete implements Serializable
	{
		private String usuario;
		private String mensaje;
		private String cedula;
		private String primernombre;
		private String segundonombre;
	   	private String email; 
		private String fnacim;
		private int sueldo;
		private int comision;
		private String departamento;
		private String cargo;
		private String cedulagerente;
		private String estado;
		   
		public String getEstado() 
		   {
			return estado;
		   }
	
		public void setEstado(String estado) 
		   {
			this.estado = estado;
		   }
		
		public String getUsuario() 
		   {
			return usuario;
		   }
		
		public void setUsuario(String usuario) 
		   {
			this.usuario = usuario;
		   }
		
		public String getMensaje() 
		   {
			return mensaje;
		   }
		
		public void setMensaje(String mensaje) 
		   {
			this.mensaje = mensaje;
		   }
		
		public String getCedula() 
		   {
			return cedula;
		   }
		
		public void setCedula(String cedula) 
		   {
			this.cedula = cedula;
		   }
		
		public String getPrimernombre() 
		   {
			return primernombre;
		   }
		
		public void setPrimernombre(String primernombre) 
		   {
			this.primernombre = primernombre;
		   }
		
		public String getSegundonombre() 
		   {
			return segundonombre;
		   }
		
		public void setSegundonombre(String segundonombre) 
		   {
			this.segundonombre = segundonombre;
		   }
		
		public String getEmail() 
		   {
			return email;
		   }
		
		public void setEmail(String email) 
		   {
			this.email = email;
		   }
		
		public String getFnacim() 
		   {
			return fnacim;
		   }
		
		public void setFnacim(String fnacim) 
		   {
			this.fnacim = fnacim;
		   }
		
		public int getSueldo() 
		   {
			return sueldo;
		   }
		
		public void setSueldo(int sueldo) 
		   {
			this.sueldo = sueldo;
		   }
		
		public int getComision() 
		   {
			return comision;
		   }
		
		public void setComision(int comision) 
		   {
			this.comision = comision;
		   }
		
		public String getDepartamento() 
		   {
			return departamento;
		   }
		
		public void setDepartamento(String departamento) 
		   {
			this.departamento = departamento;
		   }
		
		public String getCargo() 
		   {
			return cargo;
		   }
		
		public void setCargo(String cargo) 
		   {
			this.cargo = cargo;
		   }
		
		public String getCedulagerente() 
		   {
			return cedulagerente;
		   }
		
		public void setCedulagerente(String cedulagerente) 
		   {
			this.cedulagerente = cedulagerente;
		   } 
    }

class Ventana extends JFrame
	{
		public Ventana(String wip, String wusuario)
			{
				setBounds(400,400,500,600);
				Pantalla pantalla = new Pantalla(wip, wusuario);
				add(pantalla);
				setVisible(true);
				addWindowListener(new envioOnline(wip, wusuario));
			}	
	}


class envioOnline extends WindowAdapter
	{
		String xip;
		String xusuario;
		public envioOnline(String wip, String wusuario)
			{
				xip = wip;
				xusuario = wusuario;
			}
		
		public void windowOpened(WindowEvent e)
			{
				try
					{
						Socket misocket = new Socket(xip,9999);
						Paquete datos = new Paquete();
						datos.setMensaje("nuevo");
						datos.setUsuario(xusuario);
						ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
						paquete_datos.writeObject(datos);
						misocket.close();
					}
				catch (Exception e2)
					{
						e2.printStackTrace();
					}
			}
	
   }

class Pantalla extends JPanel implements Runnable
	{
		private JLabel label1,label2,label3, label4;
		private JLabel lblip, lblpuerto, lblusuario, lbliplocal;
		private JLabel label5, label6, label7, label8, label9, label10, label11, label12,label13, label14, label15, label16;
		private JTextField txtmensaje;
		private JTextField txtcedula, txtpnombre, txtsnombre, txtmail, txtfnacim, txtsueldo, txtcomision, txtdepa, txtcargo, txtgerente,txtestado;
		private JButton miboton;
		private JTextArea areamensajes;
		private JComboBox comboacciones;
		private JPanel panel0, panel1, panel2;
		private JPanel panel3, panel4;
		
		public Pantalla(String wip, String wusuario)
			{
				lblusuario = new JLabel();
				lblusuario.setText(wusuario.toUpperCase());
				lblip      = new JLabel();
				lblip.setText(wip);
							
				panel0       = new JPanel(new GridLayout(4,1,5,5));
				panel1 	     = new JPanel(new GridLayout(4,2,5,5));
				panel2 	     = new JPanel(new GridLayout(11,2,5,5));
				panel3       = new JPanel(new GridLayout(1,1,5,5));
				
				label1 		 = new JLabel("CHAT");
				label2 		 = new JLabel("IP del Servidor:");
				label3 		 = new JLabel("Puerto de la conexion:");
				label4 		 = new JLabel("Usuario:");
								
				label5       = new JLabel("Cedula ");
				label6       = new JLabel("Primer Nombre ");
				label7       = new JLabel("Segundo Nombre ");
				label8 		 = new JLabel("Email ");
				label9       = new JLabel("fecha Nacimiento ");
				label10      = new JLabel("Sueldo ");
				label11      = new JLabel("comision ");
				label12      = new JLabel("Departamento ");
				label13      = new JLabel("Cargo ");
				label14      = new JLabel("Ced. Gerente ");
				label16      = new JLabel("Estado");
				
				lblpuerto 	  = new JLabel();
				areamensajes  = new JTextArea(10,40);
				comboacciones = new JComboBox();
				comboacciones.addItem("Insert");
				comboacciones.addItem("Query");
				comboacciones.addItem("Update");
				comboacciones.addItem("Delete");
				lblpuerto.setText("9999");
				txtcedula     = new JTextField();
				txtpnombre    = new JTextField(20);
				txtsnombre    = new JTextField(20);
				txtmail       = new JTextField();
				txtfnacim     = new JTextField();
				txtsueldo     = new JTextField();
				txtcomision   = new JTextField();
				txtdepa       = new JTextField();
				txtcargo      = new JTextField();
				txtgerente    = new JTextField();
				txtestado     = new JTextField();
				txtestado.setEnabled(false);
				txtmensaje	  = new JTextField(10);
				miboton		  = new JButton("Enviar");
				
				panel1.add(label2);
				panel1.add(lblip);
				
				panel1.add(label3);
				panel1.add(lblpuerto);
				panel1.add(label4);
				panel1.add(lblusuario);
				
				panel2.add(label5);
				panel2.add(txtcedula);
				panel2.add(label6);
				panel2.add(txtpnombre);
				panel2.add(label7);
				panel2.add(txtsnombre);
				panel2.add(label8);
				panel2.add(txtmail);
				panel2.add(label9);
				panel2.add(txtfnacim);
				panel2.add(label10);
				panel2.add(txtsueldo);
				panel2.add(label11);
				panel2.add(txtcomision);
				panel2.add(label12);
				panel2.add(txtdepa);
				panel2.add(label13);
				panel2.add(txtcargo);
				panel2.add(label14);
				panel2.add(txtgerente);
				panel2.add(label16);
				panel2.add(txtestado);
				
				add(panel1);
				add(panel2);
				add(panel3);
				add(comboacciones);
				add(miboton);
				add(areamensajes);
				
				enviaTexto mievento = new enviaTexto();
				miboton.addActionListener(mievento);
				Thread hilo = new Thread(this);
				hilo.start();
			}
		
		private class enviaTexto implements ActionListener
			{
				public  void actionPerformed(ActionEvent e)
					{
					   try 
							{
								Socket misocket = new Socket(lblip.getText(),Integer.parseInt(lblpuerto.getText()));
								Paquete paquete = new Paquete();
								
								paquete.setUsuario(lblusuario.getText());
								paquete.setMensaje(comboacciones.getSelectedItem().toString());
								paquete.setCedula(txtcedula.getText());
								paquete.setPrimernombre(txtpnombre.getText());
								paquete.setSegundonombre(txtsnombre.getText());
								paquete.setEmail(txtmail.getText());
								paquete.setFnacim(txtfnacim.getText());
								if (txtsueldo.getText().isEmpty())
								   paquete.setSueldo(0);
								else
								   paquete.setSueldo(Integer.parseInt(txtsueldo.getText()));
								if (txtcomision.getText().isEmpty())
								   paquete.setComision(0);
								else
								   paquete.setComision(Integer.parseInt(txtcomision.getText()));
								paquete.setDepartamento(txtdepa.getText());
								paquete.setCargo(txtcargo.getText());
								paquete.setCedulagerente(txtgerente.getText());
								paquete.setEstado(txtestado.getText());
								
								ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
								
								paquete_datos.writeObject(paquete);
								misocket.close();
							}
						catch (UnknownHostException e1)
							{
								e1.printStackTrace();
							} 
						catch (IOException e1) 
							{
								e1.printStackTrace();
							}
						if (txtmensaje.getText().toLowerCase().equals("chao"))
								System.exit(0);
					}
			}

		@Override
		public void run() {
			
					try 
						{
							ServerSocket servidor_cliente = new ServerSocket(9090);
							Socket cliente;
							Paquete paqueteRecibido;
							while (true)
								{
									cliente = servidor_cliente.accept();
									ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());
									paqueteRecibido = (Paquete) flujoentrada.readObject();
									areamensajes.append("\n"+comboacciones.getSelectedItem()+" "+ paqueteRecibido.getMensaje());
									if (comboacciones.getSelectedItem().equals("Query"))
									   {
										  txtpnombre.setText(paqueteRecibido.getPrimernombre());
									      txtsnombre.setText(paqueteRecibido.getSegundonombre());
									      txtmail.setText(paqueteRecibido.getEmail());
									      txtfnacim.setText(paqueteRecibido.getFnacim().toString());
									      txtsueldo.setText(String.valueOf(paqueteRecibido.getSueldo()));
									      txtcomision.setText(String.valueOf(paqueteRecibido.getComision()));
									      txtdepa.setText(paqueteRecibido.getDepartamento());
									      txtcargo.setText(paqueteRecibido.getCargo());
									      txtgerente.setText(paqueteRecibido.getCedulagerente());
									      txtestado.setText(paqueteRecibido.getEstado());
									   }
								}
						} 
					catch (IOException | ClassNotFoundException e) 
						{
							e.printStackTrace();
						}
				}
	}