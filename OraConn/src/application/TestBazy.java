package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestBazy {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		try {
		Class.forName( "oracle.jdbc.driver.OracleDriver" );
		}
		catch (ClassNotFoundException ex) {
			System.out.println("Sterownik nie został znaleziony.");
			return;
		}
		
		System.out.println("Sterownik został znaleziony.");
		Connection connection = null;


		String 	oracleURL	="jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf";
		String 	userName	= "temp03";
		String 	userPass	= "temp03";
		try {
			connection = 	DriverManager.getConnection	(oracleURL	, userName, userPass);
		}
		catch (SQLException ex) {
			System.out.println("Użytkownik lub hasło nieprawidłowe.");
			return;
		}
		System.out.println("Połączono.");
		connection.close();
		System.out.println("Rozłączono.");
	}
	

}
