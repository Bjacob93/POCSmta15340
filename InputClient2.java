
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class InputClient2 {


	
	private static InetAddress host;
	private static final int PORT = 1234;
	
	private static void sendMessage(){
		Socket socket = null;
		try{
			socket = new Socket(host, PORT);
			Scanner input = new Scanner(socket.getInputStream());
			PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
			Scanner userEntry = new Scanner(System.in);
			String message, response;
			do{
				System.out.print("Enter message (QUIT to exit); ");
				message = userEntry.nextLine();
				String ressage = message.replaceAll("[-+.^:;_~!`¨'#¤%&/()=?@£$€{}|*\"\\[\\]<>½§,\\\\]","");
				message = ressage.toLowerCase();
				output.println(message);
				response = input.nextLine();
				System.out.println("\nSERVER> "+ response);
				System.out.println("\n Parts ");
				System.out.println(message);
				
				
			}while (!message.equals("quit"));
		} catch (IOException ioEx){
			ioEx.printStackTrace();
		}
		finally{
			try{
				System.out.println("\nClosing connection ...");
				socket.close();
			}
			catch(IOException ioEx){
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
	
	
	public static void main(String[] args) {
		try{
			host = InetAddress.getLocalHost();
		} catch (UnknownHostException uhEx){
			System.out.println("\nHost ID not found.");
			System.exit(1);
		}
		sendMessage();
	}

}