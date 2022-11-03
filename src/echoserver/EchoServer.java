package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {
	
	public static final int PORT_NUMBER = 0; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
			while (true) {
				Socket socket = serverSocket.accept();

				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		}
	}
}