package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Servidor 
{

	public static final int PORT = 2001;

	public static void main(String[] args) 
	{
		
		ServerSocket socketServidor = null;
		Socket socketCliente = null;
		Mensajes mensajes = new Mensajes();

		
		try 
		{
			socketServidor = new ServerSocket(PORT);
			
			
			while (true) 
			{
				System.out.println("Esperando conexiones");
				socketCliente = socketServidor.accept();
				System.out.println("Clente con la IP "+socketCliente.getInetAddress().getHostName()+ " conectado");
				ClientHandler clientHandler = new ClientHandler(socketCliente,mensajes);
				clientHandler.start();
			}
				
						
		}
			
			catch (IOException ioException) 
			{
					ioException.printStackTrace();
			}
			finally {
					
				
			
			if(socketCliente != null)
			{
				try 
				{
					socketCliente.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
			if(socketServidor != null)
			{
				try 
				{
					socketServidor.close();
				} 
				catch (IOException ioException) 
				{
					ioException.printStackTrace();
				}
			}
			
		   }

	}
}
