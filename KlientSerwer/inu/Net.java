package inu;

import java.io.*;

public class Net {

	public static void main(String[] args) throws IOException, Exception {
		if (args.length < 2)
			return;
		int portNumber = Integer.parseInt(args[1]);

		if (args[0].equalsIgnoreCase("server")) {
			NetServer netServer = new NetServer(portNumber);
			System.out.println("Server is running .");
			netServer.run();
		} else if (args[0].equalsIgnoreCase("client")) {

			String serverAddress = args[2];
			NetClient netClient = new NetClient(serverAddress, portNumber);
			netClient.openSocket();
			netClient.readLine();
			netClient.getResponse();
			System.out.println(netClient.getResponse());
			netClient.closeSocket();

		} else {
			return;
		}

	}

}
