#include "stdio.h"
#include "stdlib.h"

void arrayCopy (int fromArray[], int toArray[], int size) {
  int i=0;
  for(i=0;i<size;i++) {
    toArray[i] = fromArray[i];
  }
}

void sort(int arr[], int size) {
  int i,x;
  for(i=0;i<size;i++) {
    for(x=0;x<size-1;x++) {
      if(arr[x] > arr[x+1]) {
        int temp = arr[x];
        arr[x] = arr[x+1];
        arr[x+1] = temp;
      }
    }
  }
}

int linSearch (int arr[], int size, int target, int *numComparisons) {
  int i;
  for(i=0;i<size;i++) {
    *numComparisons = *numComparisons + 1;
    if(arr[i]==target)
      return i;
  } 
  return -1;
}

int binSearch (int arr[], int size, int target, int* numComparisons) {
  int start = 0;
  int end = size-1;
  int mid;
  while(1) {
	mid = (end + start) / 2;
	*numComparisons = *numComparisons + 1;
    if(arr[mid] == target) {
      return mid;
    } else if(arr[mid] < target) {
      start=mid+1;
    } else {
      end = mid-1;
    }
    if(start > end) {
      return -1;
    }
  }
}

void growArray(int **arr, int *currSize) {
	int *temp;
	temp = (int *)malloc(*currSize * 2 * sizeof(int));
	int i;
	for (i = 0; i < *currSize; i++)
		temp[i] = *arr[i];
	free(*arr);
	*arr = temp;
	*currSize = *currSize * 2;
	
}


int main() {
	printf("Jacob Waller CS211\n673978936\n\n");
	printf("Enter integers seperated by spaces ending with a -999: ");
	int in;
	int count = 0;
	int size = 1;
	int *arr = (int*)malloc(sizeof(int) * size);
	while(1) {
		scanf("%d",&in);
		if(in==-999) {
			break;
		}
		if(count == size-1) {
		  int *temp;
		  temp = (int *)malloc(size * 2 * sizeof(int));
		  int i;
		  for (i = 0; i < size; i++)
			  temp[i] = arr[i];
		  free(arr);
		  arr = temp;
		  size = size * 2;
		}
		arr[count++] = in;
	}

	int *newArr;
	newArr = (int*)malloc(sizeof(int) * count);
	arrayCopy(arr, newArr, count);
	sort(newArr, count);
	int i;
	printf("Original Array: ");
	for (i = 0; i < count; i++) {
		printf("%d ", arr[i]);
	}
	printf("\n");
	printf("Sorted Array: ");
	for (i = 0; i < count; i++) {
		printf("%d ", newArr[i]);
	}
	printf("\n\n");

	while (1) {
		printf("Enter a value to search: ");
		int search;
		scanf("%d", &search);
		if (search == -999)
			break;
		int numComparisons = 0;
		int index = linSearch(arr, count, search, &numComparisons);
		if (index == -1) {
			printf("Linear Search: Value was not found but there was %d comparisons\n", numComparisons);
		}
		else {
			printf("Linear Search: Value was found at: %d with %d comparisons\n", index, numComparisons);
		}

		numComparisons = 0;
		index = binSearch(newArr, count, search, &numComparisons);
		if (index == -1) {
			printf("Binary Search: Value was not found but there was %d comparisons\n", numComparisons);
		}
		else {
			printf("Binary Search: Value was found at: %d with %d comparisons\n", index, numComparisons);
		}

	}


}
