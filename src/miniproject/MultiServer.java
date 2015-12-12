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

        do{
        	System.out.println("Server is running");
            Socket client = serverSocket.accept();
            System.out.println("\nClient accepted\n");
            input = new Scanner(client.getInputStream());
            String IOSwitch = input.nextLine();
            input.close();
            
            if(IOSwitch == "0"){
            	
            	InputClientHandler handler = new InputClientHandler(client);
                handler.start();
            	
            } else if (IOSwitch == "1"){
            	
            	OutputHandlerMergeSort handler = new OutputHandlerMergeSort(client);
            	handler.start();
            }

        } while(true);
    }

}