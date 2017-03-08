/* lab11.cpp

Welcome to lab 11, dealing with the topic of recursion.  In this lab you will take
functions that work non-recursively, and you will rewrite them to work recursively.
See further details below for each of the three functions.

Running this program looks like:

Enter the limit: 5
0 1 2 3 4
0 1 2 3 4
Program ended with exit code: 0
*/

/*
	Pawel Klimek 671153576
	Jacob Waller 673978936
*/


#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <cctype>
#include <cstring>
using namespace std;


//------------------------- Sample -----------------------------
// Iteratively (non-recursively) display numbers up to some limit
void sampleIterative(int counter, int limit)
{
	// Display numbers up to the limit
	while (counter < limit) {
		cout << counter << " ";
		counter++;
	}

	cout << endl;   // display a new line
}//end sampleFcn0Iterative()


 // Same thing, now using recursion
void sampleRecursive(int counter, int limit)
{
	// Check for the base condition
	if (counter == limit) {
		return;
	}
	else {
		cout << counter << " ";
		// make the recursive call
		sampleRecursive(++counter, limit);
	}
}//end sampleFunction0Recursive()


 //------------------------- Problem 1 --------------------------
 // Display odd numbers less than some limit
void problemOneOddNumbersIterative(int counter, int limit)
{
	// Display numbers up to the limit
	while (counter < limit) {
		if (counter % 2 == 1) {
			cout << counter << " ";
		}
		counter++;
	}

	cout << endl;   // display a new line
}//end problemOneOddNumbersIterative()


 // Same thing, now done recursively.   >>> YOU SUPPLY CODE IN THIS FUNCTION <<<
void problemOneOddNumbersRecursive(int counter, int limit)
{
	if (counter % 2 == 1 && counter < limit) {
		cout << counter << " ";
		problemOneOddNumbersRecursive(counter + 2, limit);
	}
	else if (counter % 2 == 0 && counter < limit) {
		problemOneOddNumbersRecursive(counter + 1, limit);
	}
	else {
		return;
	}
}//end problemOneOddNumbersRecursive()


 //------------------------- Problem 2 --------------------------
 // Count the number of upper-case letters in an array
int problemTwoCountUpperIterative(int counter, char arrayOfLetters[])
{
	char *pCurrent = &arrayOfLetters[0];   // address of first element

	while (strlen(pCurrent) > 0) {
		// If the current character is upper case, increment counter
		if (isupper(*pCurrent)) {
			counter++;
		}
		// advance pointer to next character
		pCurrent++;
	}

	return counter;
}//end problemTwoCountUpperIterative()


 // Same thing, now done recursively.
int problemTwoCountUpperRecursive(int counter, char arrayOfLetters[])
{
	if (strlen(arrayOfLetters) > 0) {
		if (isupper(arrayOfLetters[0])) {
			return problemTwoCountUpperRecursive(counter + 1, &arrayOfLetters[1]);
		}
		else {
			return problemTwoCountUpperRecursive(counter, &arrayOfLetters[1]);
		}
	}
	else {
		return counter;
	}
}//end problemTwoCountUpperRecursive()


 //------------------------- Problem 3 --------------------------
 // Reverse a string of characters.
void problemThreeReverseIterative()
{
	// declare space for some letters
	char arrayOfLetters[81];
	// read in user input into the letters
	cin.getline(arrayOfLetters, 81);    // read in an entire line

										// start at end of string and display characters
	for (long i = strlen(arrayOfLetters); i>0; i--) {
		cout << arrayOfLetters[i - 1];
	}

	cout << endl;
}//end problemThreeReverseIterative()


 // Same thing, now done recursively.
void problemThreeReverseRecursive()
{
	char letter;
	scanf("%c", &letter);
	if (letter != '\n') {
		problemThreeReverseRecursive();
		printf("%c", letter);
	}
	
}//end problemThreeReverseRecursive()


int main()
{
	cout << "Pawel Klimek 671153576 \nJacob Waller 673978936";


	//------------------------------------------------------
	// Problem 0:  This one is an already completed example.
	// Count to some limit using a while loop.
	// Then the recursive version does the same thing.
	// For the other functions below you will be given the iterative (non-recursive)
	// version, and will have to create the recursive version.
	int counter = 0;
	int limit;
	cout << "Sample Problem \n";
	cout << "Enter the limit: ";
	cin >> limit;
	sampleIterative(counter, limit);    // Iterative (non-recursive) version
	sampleRecursive(counter, limit);    // Recursive version
	cout << "\n\n";

	//------------------------------------------------------
	// Problem 1: Display the odd numbers less than some limit
	counter = 0;
	cout << "Problem 1 \n";
	cout << "Enter the limit: ";
	cin >> limit;
	problemOneOddNumbersIterative(counter, limit);
	problemOneOddNumbersRecursive(counter, limit);  // <-- you supply the code for this one
	cout << "\n\n";

	//------------------------------------------------------
	// Problem 2: Given a C string, count how many characters are upper-case
	char arrayOfLetters[81];
	cout << "Problem 2 \n";
	cout << "Enter a line of input with mixed-case letters: ";
	counter = 0;
	// First get rid of carriage-return lingering on input buffer from previous question
	char c;
	cin.get(c);
	// Now read in user input
	cin.getline(arrayOfLetters, 81);    // read in an entire line
	cout << "Number of upper-case done Iteratively is: "
		<< problemTwoCountUpperIterative(counter, arrayOfLetters) << endl;
	cout << "Number of upper-case done Recursively is: "
		<< problemTwoCountUpperRecursive(counter, arrayOfLetters)  // <-- you supply the code for this one
		<< endl;
	cout << endl;

	//------------------------------------------------------
	// Problem 3: Enter letters to be displayed in reverse order
	cout << "Enter input to be reversed Iteratively: ";
	problemThreeReverseIterative();
	cout << endl;
	cout << "Enter input to be reversed Recursively: ";
	problemThreeReverseRecursive();
	cout << endl;

}//end main()
