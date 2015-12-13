package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class InputClientHandler extends Thread{

	private Socket client;
	private Scanner input;
	private PrintWriter output;
	
	public InputClientHandler(Socket socket){
		client = socket;
		try{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);
			//run();
		}catch(IOException ioEx){
			ioEx.printStackTrace();
		}	
	}
	public void run(){
		String received;
		String[] splitRecived;
		do{
			received = input.nextLine();
		
		splitRecived = received.split("\\s+");
		
		for (int i = 0; i < splitRecived.length; i++)
		{
			String word = splitRecived[i];
			output.println(word);

		}
	}while(!received.equals("quit"));
		
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