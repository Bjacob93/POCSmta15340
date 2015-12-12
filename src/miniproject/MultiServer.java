package miniproject;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
            Socket client = serverSocket.accept();
            System.out.println("\nNew client accepted.\n");
            input = new Scanner(client.getInputStream());
            boolean IOSwitch = input.nextBoolean();
            input.close();
            
            if(IOSwitch == true){
            	
            	InputClientHandler handler = new InputClientHandler(client);
                handler.start();
            	
            } else if (IOSwitch){
            	
            	OutputHandlerMergeSort handler = OutputHandlerMergeSort(client);
            	handler.start();       	
            }

        } while(true);
    }

}