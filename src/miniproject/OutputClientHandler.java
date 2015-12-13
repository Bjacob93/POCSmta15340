/**/
package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputClientHandler extends Thread {
	private Socket client;
	private PrintWriter output;
	

	public OutputClientHandler(Socket socket, LinkedList<String> strings, LinkedList<Integer> numbers){
		client = socket;
		
		try{
			output = new PrintWriter(client.getOutputStream(), true);
			try{
			sort(numbers, strings);

			
			for(int i = 0; i < numbers.size(); i++){
				output.println(strings.get(i) + ": " + numbers.get(i) + ".");
			}
			} catch(Exception e){
				System.out.println("The list is empty."); 
			}
			
		} catch(IOException ioEx){
			System.out.println("IOException!");
			ioEx.printStackTrace();
		}
		finally {
			try{
				System.out.println("\n Closing connection...");
				socket.close();
			}catch(IOException ioEx){
				System.out.println("Unable to close connection!");
				System.exit(1);
			}
		}
		
	}
	
	LinkedList<Integer> numbers;
	LinkedList<String> strings;
	private int[] numbersHelper;
	private String[] stringsHelper;
	
	public void sort(LinkedList<Integer> numbers, LinkedList<String> strings){
		

		this.numbersHelper = new int[numbers.size()];
		mergeSort(0, numbers.size() - 1);
	}
	
	private void mergeSort(int l, int h){
	
		if(l < h){
			int m = l + (h - l) / 2;
			mergeSort(l, m);
			mergeSort(m + 1, h);
			merge(l, m, h);
			}		
		
	}	
	
	private void merge(int l, int m, int h){
		
		for(int i = l; i <= h; i++){
			numbersHelper[i] = numbers.get(i);
			stringsHelper[i] = strings.get(i);
		}
		
		int i = l;
		int j = m + 1;
		int k = l;
		
		while(i <= m && j <= h){
			
			if (numbersHelper[i] <= numbersHelper[j]){
				numbers.set(k, numbersHelper[i]);
				strings.set(k, stringsHelper[i]);
				i++;
			} else {
				numbers.set(k, numbersHelper[j]);
				strings.set(k, stringsHelper[j]);
				j++;
			}
			k++;
		}
		while(i <= m){
			numbers.set(k, numbersHelper[j]);
			strings.set(k, stringsHelper[i]);
			k++;
			j++;
		}
	}
	
}
