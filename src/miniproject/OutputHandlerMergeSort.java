package miniproject;

import java.io.*;
import java.net.*;
import java.util.*;

public class OutputHandlerMergeSort extends Thread {

	private int[] numbers;
	private String[] strings;
	private int[] numbersHelper;
	private String[] stringHelper;
	
	private int number;
	
	public void sort(int[] values){
		
		this.numbers = values;
		number = values.length;
		this.numbersHelper = new int[number];
		mergeSort(0, number - 1);		
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
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high){
			
			if (numbersHelper[i] <= numbersHelper[j]){
				numbers[k] = numbersHelper[i];
				i++;
			} else {
				numbers[k] = numbersHelper[j];
				j++;
			}
			k++;   
		}
		while(i <= middle){
			numbers[k] = numbersHelper[i];
			k++;
			j++;
		}
	}
	
}
