package miniproject;
import java.io.*;
import java.net.*;
import java.util.*;

public class OutputClient {
	
	private static InetAddress host;
	private static final int PORT = 1235;
	public static void main(String[] args) {
        try {
            host = InetAddress.getLocalHost();
        }
        catch (UnknownHostException uhEx) {
            System.out.println("Host not found!");
            System.exit(1);
        }
        accessServer();
    }

private static void accessServer(){
    Socket link = null;
    
    try {
        link = new Socket(host, PORT);
        Scanner input = new Scanner(link.getInputStream());
        //PrintWriter output = new PrintWriter(link.getOutputStream(), true);
        System.out.print(input);
    }
    catch (IOException ioEx) {
        ioEx.printStackTrace();
    }
    finally {
        try {
            System.out.println("\n* Closing connection ... *");
            link.close();
        }
        catch (IOException ioEx) {
            System.out.println("Unable to disconnect!");
            System.exit(1);
        }
    }
}

}