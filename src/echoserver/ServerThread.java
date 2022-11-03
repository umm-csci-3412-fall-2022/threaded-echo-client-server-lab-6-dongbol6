package echoserver;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
        InputStream socketInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream socketOutputStream = null;
        PrintWriter printWriter = null;

        try {
            socketInputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(socketInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            socketOutputStream = socket.getOutputStream();
            printWriter = new PrintWriter(socketOutputStream);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
                printWriter.flush();
            }

            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (socketInputStream != null) {
                    socketInputStream.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (socketOutputStream != null) {
                    socketOutputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
