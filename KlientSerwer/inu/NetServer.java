package inu;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class NetServer {
	private int portNumber;
	private ServerSocket serverSocket;

	public NetServer(int portNumber) {
		this.portNumber = portNumber;
	}

	public void run() throws IOException {
		serverSocket = new ServerSocket(portNumber);
		try {
			for (;;) {
				Socket socket = serverSocket.accept();
				try {
					boolean autoFlushOutputBuffer = true;
					PrintWriter pW = new PrintWriter(socket.getOutputStream(), autoFlushOutputBuffer);
					pW.println(LocalDateTime.now());
				} finally {
					socket.close();
				}
			}
		} finally {
			serverSocket.close();
		}
	}

}
