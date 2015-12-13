package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;



public class MultiServer {
   	private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    
    public static  LinkedList<String> strings = new LinkedList<String>();
    public static  LinkedList<Integer> numbers = new LinkedList<Integer>();
    
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
                
        do{
        	System.out.println("Server is running");
            Socket client = serverSocket.accept();
            System.out.println("\nClient accepted\n");
            boolean IOSwitch = false;
            input = new Scanner(client.getInputStream());
                   
            if(input.hasNextBoolean()){
            IOSwitch = input.nextBoolean();
            }
            

            if(IOSwitch == true){
            	
            	System.out.println("Inputclient detected");
            	InputClientHandler handler = new InputClientHandler(client);
                handler.start();

                
            	
            } else if (IOSwitch == false){
            	
            	System.out.println("Outputclient detected");
            	OutputHandler handler = new OutputHandler(client);
            	handler.start();
            }

        } while(true);
    }
    
}