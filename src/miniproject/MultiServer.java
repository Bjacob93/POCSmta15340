package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class MultiServer {
   	private static ServerSocket serverSocket;
    private static final int PORT = 1234;
       
    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
    	
    	Scanner input;
    	
        try{
            serverSocket = new ServerSocket(PORT);
        }
        catch(IOException ioEx){
            System.out.println("\nUnable to set up port!");
            System.exit(1);
        }

        String clientMessage;
                
        do{
        	System.out.println("Server is running");
            Socket client = serverSocket.accept();
            System.out.println("\nClient accepted\n");
            boolean IOSwitch = false;
            input = new Scanner(client.getInputStream());
                   
            if(input.hasNextBoolean()){
            IOSwitch = input.nextBoolean();
            }
            
            System.out.println("Switch is now " + IOSwitch);

            if(IOSwitch == true){
            	
            	System.out.println("Inputclient detected");
            	InputClientHandler handler = new InputClientHandler(client);
                handler.start();
            	
            } else if (IOSwitch == false){
            	
            	System.out.println("Outputclient detected");
            	OutputClientHandler handler = new OutputClientHandler(client, string, numbers);
            	handler.start();
            }

        } while(true);
    }
    
    

	public static void TheCollectedWordsOfShiaLabeouf(String input) {
        LinkedList<String> strings = null;
        LinkedList<Integer> numbers = null;
        
        boolean wordFound = false;
        
        if(numbers.size() < 1){
        	numbers.addFirst(1);
        	strings.addFirst(input);
        }
        else {
        	for(int i = 0; i < numbers.size(); i++){
        		if(input.equals(strings.get(i))){
        			numbers.set(i, numbers.get(i) + 1);
        			wordFound = true;
        			break;
        		}
        	}
        	if(wordFound == false){
        		numbers.addLast(1);
        		strings.addLast(input);
        	}
        }      
  	}
}