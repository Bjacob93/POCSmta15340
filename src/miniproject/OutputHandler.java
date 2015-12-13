package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputHandler extends Thread{

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
            Scanner input = new Scanner(link.getInputStream());
            PrintWriter output = new PrintWriter(link.getOutputStream(), true);
            String message = input.nextLine();
    		String[] outMess = new String[0];
    		String[] memoryS = new String[0];
    		int[] memoryI = new int[0];
    		int size = 1;
    		int[] repeats = {1};
    		boolean contains = false;
    		
    		int swap;
    		String swapS;
    		while(!message.equals("/close")) {
    			contains = false;
                System.out.println("Message received.");
                String[] splitMess = message.split(" ");
                for(int i = 0; i < splitMess.length; i++){
                	for(int j = 0; j < outMess.length; j++){
                		if(splitMess[i].equals(outMess[j])){
                			contains = true;
                			repeats[j] += 1;
                			System.out.println("is already in: " + splitMess[i] + repeats[j]);
                		}
                	}
                	if(!contains){
                		memoryS = new String[size];
                		memoryI = new int[size];
                		for(int k = 0; k < outMess.length; k++){
                			memoryS[k] = outMess[k];
                			memoryI[k] = repeats[k];
                		}            
                		repeats = new int[size];
                		outMess = new String[size];            		
                		System.out.println("size of string: " + outMess.length);
                		for(int k = 0; k < memoryS.length; k++){
                			outMess[k] = memoryS[k];
                			repeats[k] = memoryI[k];
                		}
                		outMess[size - 1] = splitMess[i];
                		size++;
                	}
                }
                for (int i = 0; i < repeats.length - 1; i++){
                	for(int j = 0; j < repeats.length - i - 1; j++){
                		if(repeats[j] < repeats[j+1]){
                			//swap strings
                			swapS = outMess[j];
                			outMess[j] = outMess[j+1];
                			outMess[j+1] = swapS;
                			//swap integers
                			swap = repeats[j];
                			repeats[j] = repeats[j+1];
                			repeats[j+1] = swap;
                		}
                	}
                }
                message = input.nextLine();
    		}
    		for(int i = 0; i < outMess.length; i++){
    			repeats[i] += 1;
            	output.print(outMess[i] + " " + repeats[i] + ", ");
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
