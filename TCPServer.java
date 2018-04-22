import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPServer {
	
	ServerSocket sServidor;
	Socket sCliente;
	int puerto;
	PrintStream salida;
	Scanner entrada;
	String mensajeSolicitud = "";
	String mensajeRespuesta = "";
	
	public TCPServer(int p){
		puerto = p;
	}
	
	public void iniciar(){
		
		try {
			//se inicia el servidor
			sServidor = new ServerSocket(puerto);
			System.out.println("- Esperando Cliente -");
			
			//esperamos las peticiones del cliente
			while(true){
				//servidor es aceptado
				sCliente = sServidor.accept();
				entrada = new Scanner(sCliente.getInputStream());
				salida = new PrintStream(sCliente.getOutputStream());
				mensajeSolicitud = entrada.nextLine();
				
			
				
				if(" "== mensajeSolicitud){
					finalizar();
				}
				else{
					//parseamos la solicitud
					int num = Integer.parseInt(mensajeSolicitud);
					String es= primo(num);
					mensajeSolicitud=es;
					//imprimimos el mensaje
				System.out.println("Solicitud del Cliente :"+ mensajeSolicitud );
				mensajeRespuesta = "El numero "+ mensajeSolicitud;
				salida.println(mensajeRespuesta);}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		finally{
			finalizar();
		}
	}
	public void finalizar(){
		try {
			//finalizando el socket
			salida.close();
			entrada.close();
			sServidor.close();
			sCliente.close();
			System.out.println("Conexion Finalizada...");
		} catch (Exception e) {
			// Imprimir error
			e.printStackTrace();
		}
	}
	public static String primo(int numero){
		int contador = 0;
		 
        for(int I = 1; I <= numero/2; I++)
        {
            if((numero % I) == 0)
            {
                contador++;
            }
        }
 
        if(contador <= 1)
        {
            return "es primo";
        }else{
            return "no es primo";
        }
	}
}
