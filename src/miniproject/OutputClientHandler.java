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
			sort(numbers, strings);
			
			for(int i = 0; i < numbers.size(); i++){
				//Send the i'th word + " "
				//Send the i'th number + ".\n"
			}
			
		} catch(IOException ioEx){
			System.out.println("IOException!");
			ioEx.printStackTrace();
		}
		
	}
	
	LinkedList<Integer> numbers;
	LinkedList<String> strings;
	private int[] numbersHelper;;
	private String[] stringsHelper;
	
	public void sort(LinkedList<Integer> numbers , LinkedList<String> strings){
		

		this.numbersHelper = new int[numbers.size()];
		mergeSort(0, numbers.size() - 1);
	}
	
	private void mergeSort(int low, int high){
	
		if(low < high){
			int middle = low + (high - low) / 2;
			mergeSort(low, middle);
			mergeSort(middle + 1, high);
			merge(low, middle, high);
			}		
		
	}	
	
	private void merge(int low, int middle, int high){
		
		for(int i = low;i <= high; i++){
			numbersHelper[i] = numbers.get(i);
			stringsHelper[i] = strings.get(i);
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high){
			
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
		while(i <= middle){
			numbers.set(k, numbersHelper[j]);
			strings.set(k, stringsHelper[i]);
			k++;
			j++;
		}
	}
	
}
