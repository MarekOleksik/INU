package inu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NetClient {
	private String serverAddress;
	private int portNumber;
	private Socket socket;
	private InputStream inputStream;
	private String response;

	NetClient(String serverAddress, int portNumber) {
		this.serverAddress = serverAddress;
		this.portNumber = portNumber;
	}

	public void openSocket() throws IOException {
		this.socket = new Socket(serverAddress, portNumber);
		this.inputStream = socket.getInputStream();
	}

	public String readLine() throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		this.response = bufferedReader.readLine();

		bufferedReader.close();
		inputStreamReader.close();
		return this.response;
	}

	public String getResponse() {
		return this.response;
	}

	public void closeSocket() throws IOException {
		this.inputStream.close();
		this.socket.close();
	}
}
