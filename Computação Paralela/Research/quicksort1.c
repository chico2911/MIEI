#include<stdio.h>
#include<omp.h>
#include<math.h>
#include <stdlib.h>

#define ARRAY_MAX_SIZE 500000

/*
void quicksortParallel(int array[],int lo, int hi){
    int i=lo,j=hi,h;
    int x=array[(lo+hi)/2];
    //partition
    do  {
        while(array[i]<x) i++;
        while(array[j]>x) j--;
        if(i<=j){
        h=array[i]; array[i]=array[j]; array[j]=h;
        i++; j--;
        }
    }while(i<=j);
    //recursion
    //if(lo<j) quicksort(array,lo,j);
   // if(i<hi) quicksort(array,i,hi);
    //#pragma omp parallel num_threads(2)
    //#pragma omp parallel sections
    #pragma omp parallel
    {
        #pragma omp single
        {        
            //#pragma omp section
            #pragma omp task
            {
                if(lo<j) quicksortParallel(array,lo,j);
            }
            //#pragma omp section
            #pragma omp task
            {
                if(i<hi) quicksortParallel(array,i,hi);
            }
        }
    }
}
*/

void quicksortSequencial(int array[],int lo, int hi){
    int i=lo,j=hi,h;
    int x=array[(lo+hi)/2];
    //partition
    do  {
        while(array[i]<x) i++;
        while(array[j]>x) j--;
        if(i<=j){
        h=array[i]; array[i]=array[j]; array[j]=h;
        i++; j--;
        }
    }while(i<=j);
    //recursion
    if(lo<j) quicksortSequencial(array,lo,j);
    if(i<hi) quicksortSequencial(array,i,hi);
}


void printArray(int arr[], int size)
{
	int i;
	for (i=0; i < size; i++)
		printf("%d ", arr[i]);
	printf("\n");
}

int main(int argc, char* argv[])
{
    FILE *fp = fopen(argv[1],"r");

    int arraySize;

    arraySize = atoi(argv[2]);


	int arr[ARRAY_MAX_SIZE];

    for( int i = 0; i < arraySize-1; i++ )
    {
        fscanf (fp, "%d", &arr[i]);

    }
    printf("done");
    int n = ARRAY_MAX_SIZE;
    double start_time, run_time;
    
    /*start_time = omp_get_wtime();
	quicksortParallel(arr, 0, n-1); 
    run_time = omp_get_wtime() - start_time;
	printf("\n Parallel time was %lf seconds\n ",run_time);
*/
    start_time = omp_get_wtime();
	quicksortSequencial(arr, 0, n-1); 
    run_time = omp_get_wtime() - start_time;
	printf("\n Sequencial time was %lf seconds\n ",run_time);
	
	return 0; 
} 