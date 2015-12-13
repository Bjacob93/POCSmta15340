package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputHandler extends Thread{
    private static LinkedList<String> strings = multiserver.strings;
	private static LinkedList<Integer> numbers = multiserver.numbers;
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;
    
    public static void main(String[] args) {
        System.out.println("Opening port ...\n");
        try {
            serverSocket = new ServerSocket(PORT);
        }
        catch (IOException ioEx) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
        
        do {
            handleClient();
        } while(true);
    }

    private static void handleClient() {
        Socket link = null;
        try {
            link = serverSocket.accept();
            int swap;
            String swapS;
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            for (int i = 0; i < numbers.size() - 1; i++){
            	for(int j = 0; j < numbers.size() - i - 1; j++){
                	if(repeats[j] < repeats[j+1]){
                		//swap strings
                		swapS = strings.get(j);
                		strings.set.(j, strings.get(j+1));
                		strings.set(j+1, swapS);
            			//swap integers
            			swap = numbers.get(j);
            			numbers.set(j, numbers.get(j+1));
            			numbers.set(j+1, swap);
            		}
            	}
            }
    		for(int i = 0; i < strings.size(); i++){
            	output.print(strings[i] + " " + numbers[i] + ", ");
            }
            input.close();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("\n* Closing connection ... *");
                link.close();
            }
            catch (IOException ioEx) {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}
