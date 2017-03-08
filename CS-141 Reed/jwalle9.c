#include <stdio.h>
#include <string.h>

/*
  Jacob Waller 673978936
*/


// Global constant
const int MAX_LENGTH = 81;   // global constant for max input line length

int isVowel(char c) {
  char l = tolower(c);
  return l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u';
}

// This is the same function given to you in program 2.
// Parse allWords to find the next word, updating the current index in allWords
// and returning the new word in the newWord array.
void extractNextWordFromLine(
            char allWords[],    // The line of all words
            int *wordsIndex,    // The current index postion in allWords, with changes reflected back
            char newWord[])     // The new word is placed in this array, to be returned and used
{
    int j = 0;     // Set the index within the newWord array to start at the beginning
    char c='\0';   // Temporary storage for each character as we read it

    // Initialize c to be the first character, if there is one
    if( allWords[ *wordsIndex] != '\0') {
        c = allWords[ *wordsIndex];     // Get first character
        (*wordsIndex)++;                // Advance index to the next character
    }

    // Skip leading spaces if there are any
    while( c==' ') {
        c = allWords[ *wordsIndex];   // advance to the next character
        (*wordsIndex)++;              // Advance index to the next character
    }

    // Copy characters into the word until we hit a space or null
    while( c!=' ' && c!='\0') {
        newWord[ j++] = c;
        // Get the next character
        c = allWords[ *wordsIndex];
        // advance to the next character if we're not at the end of the list
        if( c!='\0') {
            (*wordsIndex)++;          // Advance index to the next character
        }
    }//end while(...

    // NULL terminate the word so that we can use string functions
    newWord[ j] = '\0';
}//end extractNextWordFromLine()


int main()
{
	char inputLine[MAX_LENGTH];    // An input line with some number of words on it
	char aWord[MAX_LENGTH];        // A single word
	int i=0;                        // Current index position in inputLine
	int numberOfWords = 0;
	int numberOfWordsBeginningInVowel = 0;

	printf("Name: Jacob Waller\nUIN: 673978936\nMidterm 1, CS 141, Fall 2016 \n");
	printf("Enter a line of input: ");
	// Read a line from the keyboard
	fgets(inputLine, MAX_LENGTH, stdin);

	// Extract and display each word from the input line, one at a time
	i=0;
	while( inputLine[ i] != '\0') {
	    extractNextWordFromLine( inputLine, &i, aWord);
      numberOfWords++;
      if(isVowel(aWord[0])) {
        numberOfWordsBeginningInVowel++;
      }
	}

	// Display results
	printf("Number of words: %d \n", numberOfWords);
	printf("Number of words beginning with a vowel: %d \n", numberOfWordsBeginningInVowel);

	printf("Done. \n");
	return 0;
}//end main()
