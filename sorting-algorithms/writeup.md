# Sorting Algorithms Writeup

## Introduction

For this assignment, I decided to implement and analyze _Heap Sort_ ($\Theta(n\ log\ n)$),  _Radix Sort_ ($\Theta(d\times n)$), _Merge Sort_ ($\Theta(n\ log\ n)$), and _Insertion Sort_ ($\Theta(n^2)$).  

_Heap Sort_ sorts a list by iteratively adding each element in a list to a min heap, then it removes each element from the heap one by one and reorganizes them into a list. Adding an element to a heap takes $\Theta(log\ n)$ time, as does removing the root element. Both actions occur once per element, meaning both parts of the process take $\Theta(n\ log\ n)$ time, so the process should logically take $\Theta(n\ log\ n)$ time overall.  

_Radix Sort_ sorts a list of integers digit by digit. For the first round, elements are placed into "buckets" based on their least significant digit and put back into a list in that order, effectively ordering them by their least significant digit. The process is then repeated for the next-least significant digit, ordering elements by their second-to-last digit and maintaining the previous order in case of a tie. This is repeated once per digit, until the most significant digit of the largest number has been processed. This process runs through each element for each digit, so the time complexity can be written as $\Theta(d\times n)$ where $d$ is the number of digits on the greatest number.  

_Merge Sort_ sorts a list of integers recursively, by splitting the list into two even-ish parts and reordering them. The list is split into parts until each remaining sub-list has just one element, where each sub-list can already be considered ordered. Then, these ordered lists are re-combined into larger, ordered lists by adding elements one-by-one to the merged list in order. This can be analyzed with the master theorem, written as $T(n)=2T(\frac{n}{2})+n$, and the end behavior shows a runtime of $\Theta(n\ log\ n)$.  

_Insertion Sort_ sorts a list of integers by splitting the list into a sorted and an unsorted part, and swapping elements backwards one-by-one into the sorted part of the list. In a best-case scenario where the list is already sorted, this would take just $\Theta(n)$ time, since it would simply iterate through every element. In the worst-case scenario, a backwards-organized list where each element needs to be shifted as far as possible, it would take $\Theta(n^2)$ time, since each element needs to be shifted across the entire array. This is the slowest approach, and is currently still running on my computer as I type this.  


## Methodology

For each sorting algorithm, the runtime was calculated on lists of size 10 through 1,000,000, increasing by a factor of 10 each time. The items in each list are randomly generated, as integers for _Radix Sort_ (since digits matter) and doubles for all others.



![Comparison Plot](./graphs/comparison.png)