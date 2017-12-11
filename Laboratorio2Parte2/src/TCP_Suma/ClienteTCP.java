package TCP_Suma;

//Codigo referenciado obtenido de: http://royerjava.es.tl/Sockets.htm
//Adaptado al requerimiento del laboratorio por: Andres Huertas/Sergio Villacres/ Grace Borja


import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;
public class ClienteTCP{
	private static int SERVER_PORT=9090;  
	
	public static void main(String []args)throws IOException {
            String serverAddress= JOptionPane.showInputDialog("Ingrese la direccion IP del servidor: ");//Pide la direccion IP para establecer la conexion
            
            //Establece la conexion con el servidor mediante un socket
            Socket clientSocket = new Socket(serverAddress, SERVER_PORT);
            
            //obtiene el mensaje enviado por el servidor a traves del socket
            InputStreamReader inputStream= new InputStreamReader(clientSocket.getInputStream());
            
            ServerSocket ss=null;
		try{

			ss=new ServerSocket(9091);  // instanciamos un socket por el que recibiremos la suma de los numeros
			
			//Pedimos los numeros en un solo String para envirlo y que el servidor se encargue de tokenizarlos
			String cad1=JOptionPane.showInputDialog("Escriba los numeros para que sume el servidor separado por coma");  
			
			Socket sc1=new Socket("192.168.100.159",SERVER_PORT); // establecemos conexion son el servidor en este caso para uso practico el localhost
			OutputStream os1=sc1.getOutputStream();	// creamos un output para enviar la cadena para que sea tokenizada
			DataOutputStream dos1=new DataOutputStream(os1);   //envia la cadena en datos primitivos hacia el servidor
			dos1.writeUTF(cad1);


			Socket s1=ss.accept();  // serealiza la conexion con el servidor por otro puerto para la recepcion de la respuesta
			
			//extraemos los datos y los presentamos por una ventana de mensaje con la suma de dos numeros
			InputStream is=s1.getInputStream();
			DataInputStream dis=new DataInputStream(is);
			String answer=dis.readUTF();
		    JOptionPane.showMessageDialog(null, answer);

	    
		    //cerramos las conexiones creadas
		    dis.close();
		    s1.close(); 
		    sc1.close();
		    dos1.close();
    
		}

		catch(IOException e){
			System.err.println("Error: no se encontro el servidor");
		}

	}
}

