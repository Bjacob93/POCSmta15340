package miniproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
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
		String received;
		String[] splitRecived;
		do{
			received = input.nextLine();
		
		splitRecived = received.split("\\s+");
		
		for (int i = 0; i < splitRecived.length; i++)
		{
			String word = splitRecived[i];
			TheCollectedWisdomOfShiaLabeouf(word);
		}
	}while(!received.equals("quit"));
		try{
		if(client!=null){
			System.out.println("Closing connection");
			client.close();
		}
	}catch (IOException ioEx){
			System.out.println("Unable to close connection");
		}
	}

	
	public void TheCollectedWisdomOfShiaLabeouf(String input) {
        
        boolean wordFound = false;
                                
        if(MultiServer.numbers.isEmpty() == true){
        	MultiServer.numbers.addFirst(1);
        	MultiServer.strings.addFirst(input);
        }
        else {
        	for(int i = 0; i < MultiServer.numbers.size(); i++){
        		if(input.equals(MultiServer.strings.get(i))){
        			MultiServer.numbers.set(i, MultiServer.numbers.get(i) + 1);
        			wordFound = true;
        			break;
        		}
        	}
        	if(wordFound == false){
        		MultiServer.numbers.addLast(1);
        		MultiServer.strings.addLast(input);
        	}
        }  
  	}
}