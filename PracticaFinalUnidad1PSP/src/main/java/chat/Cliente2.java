package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente2 
{
	
	private static final String HOST = "localHost";

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		
		Socket socketServidor = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		try 
		{
			socketServidor = new Socket(HOST, Servidor.PORT);
			
			in = new DataInputStream(socketServidor.getInputStream());
			out = new DataOutputStream(socketServidor.getOutputStream());
			
			String mensaje = in.readUTF();
			System.out.println(mensaje);
			
			mensaje = in.readUTF();
			
			System.out.println(mensaje);
			
			out.writeUTF(sc.nextLine());
			
		} 
		catch (UnknownHostException unknownHostException) 
		{
			unknownHostException.printStackTrace();
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		}
		finally
		{
			try 
			{
				out.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			try 
			{
				in.close();
			} 
			catch (IOException ioException)
			{
				ioException.printStackTrace();
			}
			
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
