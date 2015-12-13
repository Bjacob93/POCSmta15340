package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class MultiServer {
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    
	public static void main(String[] args) throws IOException{
    	
    	Scanner input;
    	
        try{
            serverSocket = new ServerSocket(PORT);
        }
        catch(IOException ioEx){
            System.out.println("\nUnable to set up port!");
            System.exit(1);
        }
        
    	ArrayList<String> words = new ArrayList<String>();

        do{
        	System.out.println("Server is running");
            Socket client = serverSocket.accept();
            System.out.println("\nClient accepted\n");
            input = new Scanner(client.getInputStream());
            boolean IOSwitch = input.nextBoolean();
            input.close();
            
            
            if(IOSwitch == true){
            	
            	System.out.println("Inputclient detected");
            	InputClientHandler handler = new InputClientHandler(client);
                handler.start();
            	
            } else if (IOSwitch == false){
            	
            	System.out.println("Outputclient detected");
            	OutputClientHandler handler = new OutputClientHandler(client);
            	handler.start();
            }

        } while(true);
    }

}