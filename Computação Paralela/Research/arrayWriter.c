#include<stdio.h>
#include<omp.h>
#include<math.h>
#include <stdlib.h>

int main() 
{ 

	int arr1[1000000];
   int arr2[250000];
   int arr3[100000];
   int arr4[50000];

    for( int i = 0; i < 1000000; i++ )
    {
       arr1[i] = rand() % 50 +1;
      // printf("%d\n", arr[i]);

    }

      for( int i = 0; i < 250000; i++ )
    {
       arr2[i] = rand() % 50 +1;
      // printf("%d\n", arr[i]);

    }

      for( int i = 0; i <= 100000; i++ )
    {
       arr3[i] = rand() % 50 +1;
      // printf("%d\n", arr[i]);

    }

      for( int i = 0; i <= 50000; i++ )
    {
       arr4[i] = rand() % 50 +1;
      // printf("%d\n", arr[i]);

    }


    FILE *f1 = fopen("array1.txt", "a");
    FILE *f2 = fopen("array2.txt", "w");
    FILE *f3 = fopen("array3.txt", "w");
    FILE *f4 = fopen("array4.txt", "w");

    for( int i = 0; i<400000; i++ )
    {
       fprintf(f1, "%d\n",arr1[i]);

    }
    
    fclose(f1);

    for( int i = 0;i < 250000; i++ )
    {
       fprintf(f2, "%d\n",arr2[i]);

    }
    fclose(f2);

    for( int i = 0; i <= 100000; i++ )
    {
       fprintf(f3, "%d\n",arr3[i]);

    }
    fclose(f3);
    for( int i = 0; i <= 10000; i++ )
    {
       fprintf(f4, "%d\n",arr4[i]);

    }
    fclose(f4);

	return 0; 
} 