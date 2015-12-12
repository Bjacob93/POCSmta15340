import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class InputClientHandler extends Thread{
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	
	public InputClientHandler(Socket socket){
		client = socket;
		try{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}	
	}
	public void run(){
		String recived;
		String[] splitRecived;
		do{
		recived = input.nextLine();
		
		splitRecived = recived.split("\\s+");
		
		for (int i = 0; i < splitRecived.length; i++)
		{
			String word = splitRecived[i];
			output.println(word);
		}
	}while(!recived.equals("quit"));
		try{
		if(client!=null){
			System.out.println("Closing connection");
			client.close();
		}
	}catch (IOException ioEx){
			System.out.println("unable to close connection");
		}
	}
}