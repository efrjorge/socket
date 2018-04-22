
public class MainTCPClient {

	public static void main(String[] args) {
		TCPClient C = new TCPClient("127.0.0.1", 8888);
		C.iniciar();
	}

}
