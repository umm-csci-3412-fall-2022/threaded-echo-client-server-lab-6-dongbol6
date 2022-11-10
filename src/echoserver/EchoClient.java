package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {
	public static final int PORT_NUMBER = 6013;

	public static void main(String[] args) throws IOException {
		EchoClient client = new EchoClient();
		client.start();
	}

	// This method is called when the client is started
	private void start() throws IOException {
		Socket socket = new Socket("localhost", PORT_NUMBER);
		InputStream socketInputStream = socket.getInputStream();
		OutputStream socketOutputStream = socket.getOutputStream();

		// Read from the keyboard and send to the server
		int inputByte;
		while ((inputByte = System.in.read()) != -1) {
		  socketOutputStream.write(inputByte);
		}


		// Read from the socket
		socketOutputStream.flush();
		socket.shutdownOutput();
		System.out.write(socketInputStream.readAllBytes());

		// Close the socket
		socketInputStream.close();
		socketOutputStream.close();
		socket.close();
	}
}