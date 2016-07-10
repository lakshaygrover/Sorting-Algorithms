import java.util.Scanner;

public class sortingAlgorithms {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter size of array");
		int size = s.nextInt();
		int[] arr = new int[size];
		System.out.println("Enter "+size+" elements:");
		for(int i = 0;i<size;i++){
			arr[i] = s.nextInt();
		}
		System.out.println("1 Sorting by bubble sort");
		System.out.println("2 Sorting by selection sort");
		System.out.println("3 Sorting by insertion sort");
		System.out.println("4 Sorting by quick sort");
		System.out.println("5 Sorting by merge sort");
		int option = s.nextInt();
		int[] solution = new int[size];
		switch(option){
		case 1:
			bubbleSort(arr);
			break;
		case 2:
			selectionSort(arr);
			break;
		case 3:
			insertionSort(arr);
			break;
		case 4:
			quickSort(arr, 0, arr.length-1);
			showArray(arr);
			break;
		case 5:
			solution = mergeSort(arr);
			showArray(solution);
			break;
			
		}
	}
//Function to print the elements of an array
	private static void showArray(int[] solution) {
		System.out.println("Sorted array is:");
		for(int i = 0;i<solution.length;i++){
			System.out.print(solution[i]+" ");
		}
		System.out.println();
		
	}

	public static void bubbleSort(int[] arr){
		int temp;	
		for(int j =1; j<(arr.length); j++){
			for(int i =0; i<(arr.length-1); i++){
				if(arr[i] > arr[i+1]){
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
		}
		showArray(arr);
	}

	public static void insertionSort(int[] arr){
		for(int i = 1; i<arr.length; i++){
			int position = i;
			int temp = arr[position];
			while(position>0&&arr[position-1]>temp){
				arr[position]=arr[position-1];
				position--;
			}
			arr[position] = temp;
		}
		showArray(arr);
	}

	public static void selectionSort(int[] arr){

		for(int i = 0;i<arr.length;i++){
			int  index = i, temp = arr[i], minimum = arr[i];
			for(int j = i+1;j<arr.length;j++){
				if(arr[j]<minimum){
					minimum = arr[j];
					index= j;
				}
			}
			arr[i] = arr[index];
			arr[index] = temp;

		}
		showArray(arr);

	}

	public static void quickSort(int[] arr, int begin_index, int end_index){
		if(begin_index>=end_index){
			return;
		}
		int index = findPivot(arr, begin_index, end_index);
		quickSort(arr, begin_index, index-1);
		quickSort(arr, index+1, end_index);
	}


	/*Helping function for sorting an array using quick sort.
	 * This function assumes element at begin_index to be pivot
	 * and then places all the elements lesser than pivot to its
	 * left and those greater than pivot to its right.*/
	public static int findPivot(int[] arr, int begin_index, int end_index){

		int pivotIndex = begin_index;
		for(int i = begin_index+1;i<=end_index;i++){
			if(arr[i]<arr[begin_index]){
				pivotIndex++;
			}
		}
		int temp = arr[pivotIndex];
		arr[pivotIndex]= arr[begin_index];
		arr[begin_index]=temp;

		int i =begin_index,j = end_index;
		while(i<pivotIndex&&j>pivotIndex){
			if(arr[i]<arr[pivotIndex]){
				i++;
			}
			else if(arr[j]>arr[pivotIndex]){
				j--;
			}
			else{
				int temp2 = arr[i];
				arr[i] = arr[j];
				arr[j]= temp2;
				i++;
				j--;
			}

		}
		return pivotIndex;
	}

	public static int[] mergeSort(int[] arr){
		if(arr.length <= 1){
			return arr;
		}
		int[] arr1 = new int[(arr.length/2)];
		int[] arr2 = new int[arr.length-arr1.length];
		for(int i =0;i<arr1.length;i++){
			arr1[i] = arr[i];
		}
		for(int i =0;i<arr2.length;i++){
			arr2[i] = arr[arr1.length+i];
		}
		arr1 = mergeSort(arr1);
		arr2 = mergeSort(arr2);
		return merge(arr1, arr2);
	}
	
	//Helping function for sorting an array using merge sort. 
	public static int[] merge(int[] arr1, int[] arr2){
		int[] arr = new int[arr1.length+arr2.length];
		int first_index = 0,second_index = 0,combined_index = 0;
		while(first_index<arr1.length && second_index<arr2.length){
			if(arr1[first_index]<arr2[second_index]){
				arr[combined_index] = arr1[first_index];
				first_index++;
				combined_index++;
			}
			else if(arr2[second_index]<arr1[first_index]){
				arr[combined_index] = arr2[second_index];
				second_index++;
				combined_index++;
			}
		}
		while(first_index<=arr1.length-1){
			arr[combined_index] = arr1[first_index];
			first_index++;
			combined_index++;
		}
		while(second_index<=arr2.length-1){
			arr[combined_index] = arr2[second_index];
			second_index++;
			combined_index++;
		}
		return arr;
	}
}