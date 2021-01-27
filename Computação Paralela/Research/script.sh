#!/bin/sh
module load gcc/5.3.0

cd $PBS_O_WORKDIR


if [[ -z $1 ]]; then
  export OMP_NUM_THREADS=8
  echo "Num threads foi para 8";
else
  export OMP_NUM_THREADS=$1
fi


echo "\nSequencial Array 1\n"
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000
./serialQuicksort array1.txt 500000

echo "\nSequencial Array 2\n"
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000
./serialQuicksort array5.txt 250000

echo "\nSequencial Array 3\n"
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000
./serialQuicksort array3.txt 100000

echo "\nSequencial Array 4\n"
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000
./serialQuicksort array4.txt 50000

echo "\nParalelo Simples Array 1\n"
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000
./paralelo array1.txt 500000

echo "\nparaleloSimples Array 2\n"
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000
./paralelo array5.txt 250000

echo "\nparaleloSimples Array 3\n"
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000
./paralelo array3.txt 100000

echo "\nparaleloSimples Array 4\n"
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000
./paralelo array4.txt 50000

echo "\nParalelo Single Array 1\n"
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000
./paraleloSingle array1.txt 500000


echo "\nparaleloSingle Single Array 2\n"
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000
./paraleloSingle array5.txt 250000

echo "\nparaleloSingle Single Array 3\n"
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000
./paraleloSingle array3.txt 100000

echo "\nparaleloSingle Single Array 4\n"
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000
./paraleloSingle array4.txt 50000

echo "\nParalelo Taskwait Array 1\n"
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000
./paraleloTaskwait array1.txt 500000

echo "\nParalelo Taskwait Array 2\n"
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000
./paraleloTaskwait array5.txt 250000

echo "\nParalelo Taskwait Array 3\n"
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000
./paraleloTaskwait array3.txt 100000

echo "\nParalelo Taskwait Array 4\n"
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000
./paraleloTaskwait array4.txt 50000


echo "\nQuicksort Array 1\n"
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000
./quicksort array1.txt 500000

echo "\nQuicksort Array 2\n"
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000
./quicksort array5.txt 250000

echo "\nQuicksort Array 3\n"
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000
./quicksort array3.txt 100000

echo "\nQuicksort Array 4\n"
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
./quicksort array4.txt 50000
