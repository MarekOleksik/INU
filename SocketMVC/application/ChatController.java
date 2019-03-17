package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.web.WebView;

public class ChatController {
	@FXML
	TextField messageTextField;
	@FXML
	Label welcomeLabel;
	@FXML
	WebView webViewMessages;
	@FXML
	Circle circleImage;
	@FXML
	ImageView sendImageView;

	private String userName = ""; // nazwa wybrana przez użytkownika
	private int UID; // identyfikator użytkownika nadany przez serwer
	private String senderName; // nazwa nadawcy wiadomości
	private int senderUID; // identyfikator nadawcy
	private String host; // adres serwera
	private final int PORT = 9001; // numer portu
	private Socket socket; // obiekt gniazda
	private BufferedReader inputBufferedReader; // bufor wejściowy (dane
												// odebrane z serwera)
	private PrintWriter outputPrintWriter; // bufor wyjściowy (dane do wysłania)
	private final int PROTOCOL_PREFIX_LENGTH = 3; // długość słów kluczowych
													// komunikatów
	private Document messagesLayout;

	@FXML
	private void initialize() {
		String welcome = "Nice to see you there!This is a welcome message. " + "Say hello to other users.";
		messagesLayout = Jsoup.parse("<html><head><meta charset='UTF-8'>"
				+ "</head><body><ul><li class=\"welcome\"><div class=\"message\"><div class=\"content\">" + welcome
				+ "</div></div></li></ul></body></html>", "UTF-16", Parser.xmlParser());
		webViewMessages.getEngine().loadContent(messagesLayout.html());
		webViewMessages.getEngine().setUserStyleSheetLocation(getClass().getResource("application.css").toString());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		welcomeLabel.setText("Hello " + this.userName + "!");
		Image myImage = new Image(new File("res/harveyspecter.png").toURI().toString());
		ImagePattern pattern = new ImagePattern(myImage);
		circleImage.setFill(pattern);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void closeSocket() {

		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() throws UnknownHostException, IOException {
		socket = new Socket(host, PORT);
		inputBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outputPrintWriter = new PrintWriter(socket.getOutputStream(), true);
		Task<Void> task = new Task<Void>() {
			@Override
			public Void call() throws IOException {
				while (true) {
					String msg = inputBufferedReader.readLine();
					System.out.println(msg);
					if (msg.startsWith("RDY")) {
						outputPrintWriter.println(userName);
					} else if (msg.startsWith("UID")) {
						UID = Integer.parseInt(msg.substring(PROTOCOL_PREFIX_LENGTH));
					} else if (msg.startsWith("MSG")) {
						addMessage(toHTML(decodeUID(msg)));
					}
				}
			}
		};
		new Thread(task).start();
	}

	@FXML
	private void sendImageView_MouseReleased() {
		if (messageTextField.getLength() == 0)
			return;
		outputPrintWriter.println(messageTextField.getText());
		messageTextField.clear();
	}

	@FXML
	private void messageTextField_KeyPressed(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			sendImageView_MouseReleased();
		}
	}

	private void addMessage(Element message) {
		Element wrapper = messagesLayout.getElementsByTag("ul").first();
		wrapper.appendChild(message);
		Platform.runLater(new Runnable() {
			public void run() {
				webViewMessages.getEngine().loadContent(messagesLayout.html());
			}
		});
	}

	private Element toHTML(String message) {
		System.out.println("toHTML:" + message);
		String msgClass = (UID == senderUID) ? "request" : "response";
		Element wrapper = new Element("li").attr("class", msgClass);
		Element image = new Element("img").attr("class", "avatar").attr("src",
				new File("res/harveyspecter.png").toURI().toString());
		if (UID != senderUID) {
			image.attr("src", new File("res/mikeross.png").toURI().toString());
			new Element("span").attr("class", "author").append(senderName).appendTo(wrapper);
		}

		image.appendTo(wrapper);
		Element message_div = new Element("div").attr("class", "message").appendTo(wrapper);
		new Element("div").attr("class", "content").append(message).appendTo(message_div);
		return wrapper;
	}
	
	private String decodeUID(String msg) {
		msg = msg.substring(PROTOCOL_PREFIX_LENGTH);
		String[] param = msg.split("\t");
		this.senderUID = Integer.parseInt(param[0]);
		this.senderName = param[1];
		return msg.substring(param[0].length() + param[1].length() + 2);
		}

}
