package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputClientHandler extends Thread {
	private Socket client;
	private Scanner input;
	private PrintWriter output;
	

	public OutputClientHandler(Socket socket){
		client = socket;
		try{
			input = new Scanner(client.getInputStream());
			output = new PrintWriter(client.getOutputStream(), true);
			
		} catch(IOException ioEx){
			System.out.println("IOException!");
			ioEx.printStackTrace();
		}
		
	}

	private int[] numbers;
	private String[] strings;
	private int[] numbersHelper;
	private String[] stringsHelper;
	
	public void sort(int[] values){
		
		this.numbers = values;
		this.numbersHelper = new int[values.length];
		mergeSort(0, values.length - 1);
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
			numbersHelper[i] = numbers[i];
			stringsHelper[i] = strings[i];
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high){
			
			if (numbersHelper[i] <= numbersHelper[j]){
				numbers[k] = numbersHelper[i];
				strings[k] = stringsHelper[i];
				i++;
			} else {
				numbers[k] = numbersHelper[j];
				strings[k] = stringsHelper[j];
				j++;
			}
			k++;   
		}
		while(i <= middle){
			numbers[k] = numbersHelper[i];
			strings[k] = stringsHelper[i];
			k++;
			j++;
		}
	}
	
}
