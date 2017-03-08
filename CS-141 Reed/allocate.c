#include <iostream>   // For general IO
#include <fstream>    // For file input and output
#include <cassert>    // For the assert statement
using namespace std;

const int NumberOfWords = 500000;   // Number of dictionary words
const int WordLength = 17;	    // Max word size + 1 for null 


// Allocate memory for dictionary words array.
// Note that the '&' is needed so that the new array 
// address is passed back as a reference parameter.
void allocateArray( char ** & matrix)
{
   // Allocate space for large array of C-style strings
   matrix = new char*[ NumberOfWords];

   // For each array entry, allocate space for the string to be stored there
   for (int i=0; i < NumberOfWords; i++) {
      matrix[i] = new char[ WordLength];

      // just to be safe, initialize C-string to all null characters 
      for (int j=0; j < WordLength; j++) {
         matrix[i][j] = NULL; 
      }//end for (int j=0...
   }//end for (int i...
}//end allocateArray()


// Deallocate memory for the dictionary words array. I suppose
// the '&' to make matrix a reference parameter is not strictly 
// necessary, since the memory is freed up in either case.
void deallocateArray( char ** & matrix)
{
   // Deallocate dynamically allocated space for the array
   for (int i=0; i < NumberOfWords; i++) {
      delete [] matrix[ i];
   }
   delete [] matrix; // delete the array at the outermost level
}


int main()
{
   // Now illustrate allocating (and later deallocating) 
   // the big dictionary array
   // First declare the pointers to the array. Think of the name of an 
   // array as the address of the first element.  This means an array
   // of char (a C-style string) is equivalent to a char *.  We have an 
   // array of strings, which makes the big array a char **.
   char ** dictionary;
   
   // allocate memory
   allocateArray( dictionary);

   // Now read the words from the dictionary
   ifstream inStream;                          // declare an input stream for my use
   int wordRow = 0;                            // Row for the current word
   inStream.open( "dictionary.txt");
   assert( ! inStream.fail() );  // make sure file open was OK

   // Keep repeating while input from the file yields a word
   while ( inStream >> dictionary[ wordRow]) {
      wordRow++;
   }
   cout << wordRow << " words were read in." << endl;

   // show how we can use "normal" array notation to access the memory 
   cout << "Enter an array index number from which to display a word: ";
   long index;
   cin >> index;
   // Display the word at that memory address 
   cout << dictionary[ index] << endl;
   
   // deallocate memory
   deallocateArray( dictionary);
   
   return 0;
}
