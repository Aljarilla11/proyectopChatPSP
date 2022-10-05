package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class ClientHandler extends Thread {
	
	private Socket socketCliente;
	private Mensajes mensajes;
	private DataInputStream input;
	private DataOutputStream output;
	
	public ClientHandler (Socket socketCliente,Mensajes mensajes) {
		this.socketCliente = socketCliente;
		this.mensajes = mensajes;
		

		try {
			input = new DataInputStream(socketCliente.getInputStream());
			output = new DataOutputStream(socketCliente.getOutputStream());
		} catch (IOException ex) {
		System.out.println("Error al crear la entrada y salida de datos "+ex.getMessage());
		}
    }
	
	public void run() 
	{
		String mensajeRecibido = null;
		boolean conectado = true;
		
		  while (conectado) 
		  {
	            try 
	            {
	                mensajeRecibido = output.readUTF();
	                mensajes.setMensaje(mensajeRecibido);
	            } 
	            catch (IOException ex) 
	            {
	                System.out.println("Cliente con la IP " + socketCliente.getInetAddress().getHostName() + " desconectado.");
	                conectado = false;
	                try {
	                    output.close();
	                    input.close();
	                } catch (IOException ex2) {
	                    System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
	                }
	            }
	        }   
	    }
	
	
	
	
   public void update(Object arg) {
   
	   try {
        // Envia el mensaje al cliente
       input.writeUTF(arg.toString());
    
	   } catch (IOException ex) {
        System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
    
	   }
   }
} 	








