package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		// Read from the keyboard
		PrintWriter printWriter = new PrintWriter(socketOutputStream);
		printWriter.println("Hello World!");
		printWriter.flush();

		socket.shutdownOutput();

		// Read from the socket
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketInputStream));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}

		bufferedReader.close();
		socketInputStream.close();
		printWriter.close();
		socketOutputStream.close();
		socket.close();
	}
}