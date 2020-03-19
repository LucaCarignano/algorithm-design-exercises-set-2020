package util;

/**
 * Sorts arrays of comparable objects using a variety of options.
 * 
 * @author aguirre
 *
 * @param <E> is the type of the elements of the array.
 */
public class ArraySorter<E extends Comparable<E>> {

	/**
	 * The array to sort.
	 */
	private E[] array;
	
	/**
	 * The algorithm to use for sorting.
	 */
	private SortAlgorithm algorithm = SortAlgorithm.INSERTIONSORT;

	/**
	 * Default constructor. Sets the array to sort and sorting algorithm to INSERTION SORT.
	 * @param array is the array to sort.
	 */
	public ArraySorter(E[] array) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
	}

	/**
	 * Constructor that sets array and sorting algorithm.
	 * @param array is the array to sort.
	 * @param algorithm is the algorithm to use for sorting.
	 */
	public ArraySorter(E[] array, SortAlgorithm algorithm) {
		if (array == null) throw new IllegalArgumentException("array must be non-null");
		this.array = array;
		this.algorithm = algorithm;
	}

	/**
	 * Sets the algorithm to use for sorting.
	 * @param algorithm is the algorithm to set for sorting.
	 */
	public void setAlgorithm(SortAlgorithm algorithm) {
		if (algorithm == null) throw new IllegalArgumentException("algorithm can't be null");
		this.algorithm = algorithm;
	}
	
	/**
	 * Retrieves the (sorted or yet unsorted) array within the ArraySorter.
	 * @return the array stored within the ArraySorter object.
	 */
	public E[] getArray() {
		return this.array;
	}

	/**
	 * Sets the array to be sorted.
	 * @param array is the new array to sort.
	 */
	public void setArray(E[] array) {
		this.array = array;		
	}

	/**
	 * Sorts the array.
	 * The array can then be retrieved using getArray() method.
	 */
	public void sort() {
		switch (this.algorithm) {
		case INSERTIONSORT: 	
			insertionSort(array); 
			break;
		case BUBBLESORT:
			bubbleSort(array); 
			break;
		case MERGESORT:
			mergeSort(array, 0, array.length-1); 
			break;
		case SELECTIONSORT:
			selectionSort(array); 
			break;
		default:
            throw new UnsupportedOperationException("sorting method not yet implemented"); 
		}	
	}

	/**
	 * Sorts an array. Implements the selection sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void selectionSort(T[] array) {
		if (array == null) throw new IllegalArgumentException("array is null, can't sort");
		int i = 0;
		while (i < array.length) 
		{
			T min, aux;
			int minIndex, j;
			min = array[i];
			minIndex = i;
			j = i;
			while (j < array.length) {
				if (array[j].compareTo(min) < 0) 
				{
					min = array[j];
					minIndex = j;
				}
				j++;
			}
			aux = array[i];
			array[i] = array[minIndex];
			array[minIndex] = aux;
			i++;
		}
	}

	/**
	 * Sorts an array. Implements the mergesort sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void mergeSort(T[] array, int l, int r) 
	{
		if(l<r) //(l<r) condition will hold good until getting singleton arrays
		{
			int mid = (l+r)/2;   
			mergeSort(array,l,mid);     // calling merge sort on left sub array
			mergeSort(array,mid+1,r);  // calling merge sort on right sub array
			merge(array,l,mid,r);     // merge operation 
		}			
	}

	private static <T extends Comparable<T>> void merge(T[] array, int l, int mid, int r)
	{
		int n1 = (mid-l+1);   //getting size of left sub array
		int n2 = r-mid;      //getting size of right sub array
		T[] left  = (T[])new Comparable[n1];  
		T[] right = (T[])new Comparable[n2];
		int i;
		
		for (i = 0; i < n1 ; i++ )
		{
			left[i] = array[l+i];   
		}
		
		for (i = 0; i < n2 ; i++)
		{
			right[i] = array[mid+1+i];
		}
		
		int li,ri,ai;     
		li=0;	//left index
		ri=0;	//right index
		ai=l;	//array index
		
		while (li < n1 && ri < n2)
		{
			if(!(left[li].compareTo(right[ri]) == 1 ))	// minimum element will be placed in sorted sub array
			{
				array[ai]= left[li];
				ai++;
				li++;
			}
			else
			{
				array[ai] = right[ri];
				ai++;
				ri++;
			}
		}
		
		while(li < n1)	// copy remaining elements of left sub array into the merged array
		{
			array[ai] = left[li];
			ai++;
			li++;
		}
		
		while(ri < n2)	//copy remaining elements of right sub array into the merged array
		{
			array[ai] = right[ri];
			ai++;
			ri++;
		}
	}


	/**
	 * Sorts an array. Implements the bubblesort sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void bubbleSort(T[] array) 
	{		
		T aux;
		for(int i=0; i < array.length; i++)
		{ 
			if (!isSorted(array))
			{
				
	            for(int j=1; j < (array.length-i); j++)
	            {  
	                if (array[j-1].compareTo(array[j]) > 0) 
	                { 
	                 	aux = array[j];
	                 	array[j] = array[j-1];
	                 	array[j-1] = aux;
	                }
	        	}
	        }
	    }
	}

	/**
	 * Sorts an array. Implements the insertion sort algorithm.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void insertionSort(T[] array) 
	{
        for (int j = 1; j < array.length; j++) 
        {  
            T key = array[j];  
            int i = j-1;  
            while ( (i > -1) && ( array[i].compareTo(key) > 0 ) )
            {  
                array [i+1] = array [i];  
                i--;  
            }  
            array[i+1] = key;  
        } 		
	}

	/**
	 * Checks if a given array is sorted.
	 * @param <T> is the type of the elements in the array.
	 * @param array is the array to be checked for sortedness.
	 * @return true iff the array is sorted.
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] array)
	{	
		boolean sorted = true;
		int i = array.length-1;
		while (sorted && i >= 1 ){
			if (array[i].compareTo(array[i-1]) < 0)
			{
				sorted = false;
			}
			i--;
		}
		return sorted;
		//throw new UnsupportedOperationException("method not yet implemented");		
	}

}
