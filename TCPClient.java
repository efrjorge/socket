import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
	
	Socket sCliente;
	Scanner entrada;
	PrintStream salida;
	String host;
	int puerto;
	String mensajeSolicitud = "";
	String mensajeRespuesta= "";
	
	public TCPClient (String h,int p) {
		host = h;
		puerto = p;
	}
	
	public void iniciar(){
		try {
			// estamos estableciendo conexion con el servidor
			// host: ej. localhost
			// puerto: puerto que dimos designado anteriormente
			sCliente = new Socket(host,puerto);
			//iniciada la conexion
			//imprimimo la direccion IP y el puerto
			System.out.println("Conectado a: " + sCliente.getRemoteSocketAddress());
			//obtenemos los flujos de datos de entrada y salida
			salida = new PrintStream(sCliente.getOutputStream());
			entrada = new Scanner(sCliente.getInputStream());
			
			
			//con este bloque de codigo nos encargamos de enviar mensajes al servidor
			Scanner lectura = new Scanner(System.in);
			System.out.print("\nCual es su numero :");

			mensajeSolicitud = lectura.nextLine();
			salida.println(mensajeSolicitud);
			mensajeRespuesta = entrada.nextLine();
			System.out.println("Respuesta del servidor: "+ mensajeRespuesta);
			
		} catch (Exception e) {
			e.printStackTrace();
			finalizar();
		}
		
	}
	
	public void finalizar(){
		try {
			salida.close();
			entrada.close();
			sCliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
