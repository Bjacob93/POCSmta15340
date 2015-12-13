package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputHandler extends Thread{
    private static LinkedList<String> strings = MultiServer.strings;
	private static LinkedList<Integer> numbers = MultiServer.numbers;
	private PrintWriter output;
	private Socket client;
    
    private void OutputHandler(Socket socket, LinkedList<String> strings, LinkedList<Integer> numbers) {
        try {
        	System.out.println(strings.size());
        	client = socket;
        	output = new PrintWriter(socket.getOutputStream(), true);
            int swap;
            String swapS;
            for (int i = 0; i < numbers.size() - 1; i++){
            	for(int j = 0; j < numbers.size() - i - 1; j++){
                	if(numbers.get(j) < numbers.get(j+1)){
                		//swap strings
                		swapS = strings.get(j);
                		strings.set(j, strings.get(j+1));
                		strings.set(j+1, swapS);
            			//swap integers
            			swap = numbers.get(j);
            			numbers.set(j, numbers.get(j+1));
            			numbers.set(j+1, swap);
            		}
            	}
            }
    		for(int i = 0; i < strings.size(); i++){
            	output.print(strings.get(i) + " " + numbers.get(i) + ", ");
            }
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
       // finally {
            try {
                System.out.println("\n* Closing connection ... *");
                socket.close();
            }
            catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
//}
