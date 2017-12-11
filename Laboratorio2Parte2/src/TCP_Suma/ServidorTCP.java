package TCP_Suma;

//Codigo referenciado obtenido de: http://royerjava.es.tl/Sockets.htm
//Adaptado al requerimiento del laboratorio por: Andres Huertas/Sergio Villacres/ Grace Borja

import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
public class ServidorTCP{

    private static int PORT=9090;
	public static void main(String [] args){
		int a,b,sum;
		ServerSocket serverSocket=null;   // Creamos un socket para extraer los datos que nos envia el cliente y procesarlos

		try{

			serverSocket=new ServerSocket(PORT); //por el puerto 90 recibiremos la data
                        System.out.println("Server listening on port "+PORT); // imprime en consola el puerto que el servidor se conecta.
		}
			catch(IOException e){}
		try{
			Socket socket=serverSocket.accept();
			InputStream input=socket.getInputStream();
			DataInputStream entrada =new DataInputStream(input);   //almacenamos los datos en un string
			StringTokenizer tokens = new StringTokenizer(entrada .readUTF(), ",");   // tokenizamos para obtener los dos numeros
			
			//extraemos los numeros en formato String
			a= Integer.parseInt(tokens.nextToken()); 
			b=Integer.parseInt(tokens.nextToken());
  
			// Realizamos la conversion a int para su suma
			//x=Integer.parseInt(a);
			//y=Integer.parseInt(b);
    
			sum=a+b;  // Realiza la suma

			// Para muestra de resultados inmediatos utilizamos como direccion del servidor el local host
			Socket socket2=new Socket("192.168.100.159",9091);  // Crea un nuevo socket para el envio de la respuesta por el puerto 5052
			OutputStream os=socket2.getOutputStream();
			DataOutputStream dos=new DataOutputStream(os); // creamos un paquete donde contendra nuestra respuesta
			dos.writeUTF("La suma total es:  " + sum); // enviamos la respuesta generando un String de resuesta

			// cerramos los sockets
			dos.close();
			socket.close();
			socket2.close();
        
		}
			catch(IOException e){}

	}
}
