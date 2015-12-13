/**/
package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputClientHandler extends Thread {
	private Socket client;

	
	private static LinkedList<String> strings = MultiServer.strings;
	private static LinkedList<Integer> numbers = MultiServer.numbers;
	private int[] numbersHelper;
	private String[] stringsHelper;
	
	
	public OutputClientHandler(Socket socket, LinkedList<String> stringsCopy, LinkedList<Integer> numbersCopy){
		client = socket;
	
		System.out.println("Output handler called");

			sort(numbers, strings);
			
			for(int i = 0; i < numbers.size(); i++){
				System.out.println(strings.get(i)+ " " + numbers.get(i));
			}

			try{
				System.out.println("\n Closing connection...");
				socket.close();
			}catch(IOException ioEx){
				System.out.println("Unable to close connection!");
				System.exit(1);
			}
		}
		
//	}
	

		
	public void sort(LinkedList<Integer> numbers, LinkedList<String> strings){
		

		this.numbersHelper = new int[MultiServer.numbers.size()];
		this.stringsHelper = new String[MultiServer.numbers.size()];
		mergeSort(0, MultiServer.numbers.size() - 1);
	}
	
	private void mergeSort(int l, int h){
			
		if(l < h){
			int m = l + (h - l) / 2;
			mergeSort(l, m);
			mergeSort(m + 1, h);
			merge(l, m, h);
			}		
		
	}	
	
	private void merge(int l, int m, int h){{
				
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
			numbers.set(k, numbersHelper[i]);
			strings.set(k, stringsHelper[i]);
			k++;
			i++;
		}
	
	}

	}
}
	
