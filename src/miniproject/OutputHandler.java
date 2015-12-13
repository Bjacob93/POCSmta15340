package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputHandler extends Thread{
	private Socket client;

	
	private static LinkedList<String> strings = MultiServer.strings;
	private static LinkedList<Integer> numbers = MultiServer.numbers;
	LinkedList<String> stringsCopy = (LinkedList<String>)strings.clone();
	LinkedList<Integer> numbersCopy = (LinkedList<Integer>)numbers.clone();
	private static PrintWriter output;

    
    public OutputHandler(Socket socket) {
    	client = socket;
       try {
    	   
       
        	System.out.println(stringsCopy.size());
        	output = new PrintWriter(socket.getOutputStream(), true);
        	
       }catch (IOException ioEx) {
                 ioEx.printStackTrace();
             }
            int swap;
            String swapS;
            for (int i = 0; i < numbersCopy.size() - 1; i++){
            	for(int j = 0; j < numbersCopy.size() - i - 1; j++){
                	if(numbers.get(j) < numbersCopy.get(j+1)){
                		//swap strings
                		swapS = stringsCopy.get(j);
                		stringsCopy.set(j, stringsCopy.get(j+1));
                		stringsCopy.set(j+1, swapS);
            			//swap integers
            			swap = numbersCopy.get(j);
            			numbersCopy.set(j, numbersCopy.get(j+1));
            			numbersCopy.set(j+1, swap);
            		}
            	}
            }
    		for(int i = 0; i < stringsCopy.size(); i++){
            	output.println(stringsCopy.get(i) + " " + numbersCopy.get(i) + ", ");
            }
    

       // finally {
	try{
		System.out.println("\n Closing connection...");
		socket.close();
	}catch(IOException ioEx){
		System.out.println("Unable to close connection!");
		System.exit(1);
	
       }
    }
}


//}
