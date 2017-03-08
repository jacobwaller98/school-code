// program5.cpp : Defines the entry point for the console application.
//

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <ctime>
#include <cstdlib>
#include <fstream>
#include <cassert>

using namespace std;

bool timerUsed = true;

char ** dictionary;
char board[4][4];
char wordsOnBoard[256][17];
int  numWordsOnBoard = 0;
char usedWords[64][17];
int  secondsStart;
int  secondsEnd;
int numWordsScored = 0;
int  numWords; // Actual number of words
const int NumberOfWords = 500000;   // Number of dictionary words
const int WordLength = 17;	    // Max word size + 1 for null 
int score = 0;


double letterMults[] = {0.07680, 0.09485,0.13527,0.16824,0.28129,0.32033,0.29299,0.34499,0.43625,0.43783,
						0.44627,0.49865,0.52743,0.59567,0.66222,0.69246,0.69246,0.76380,0.86042,0.92666,
						0.95963,0.96892,0.97616,0.97892,0.99510,1.00000 };


// Allocate memory for dictionary words array.
// Note that the '&' is needed so that the new array 
// address is passed back as a reference parameter.
void allocateArray(char ** & matrix)
{
	// Allocate space for large array of C-style strings
	matrix = new char*[NumberOfWords];

	// For each array entry, allocate space for the string to be stored there
	for (int i = 0; i < NumberOfWords; i++) {
		matrix[i] = new char[WordLength];

		// just to be safe, initialize C-string to all null characters 
		for (int j = 0; j < WordLength; j++) {
			matrix[i][j] = NULL;
		}//end for (int j=0...
	}//end for (int i...
}//end allocateArray()


 // Deallocate memory for the dictionary words array. I suppose
 // the '&' to make matrix a reference parameter is not strictly 
 // necessary, since the memory is freed up in either case.
void deallocateArray(char ** & matrix)
{
	// Deallocate dynamically allocated space for the array
	for (int i = 0; i < NumberOfWords; i++) {
		delete[] matrix[i];
	}
	delete[] matrix; // delete the array at the outermost level
}


/*
	returns the letter of the alphabet based off of the number in letterNults and num
*/
char getLetter(double num) {
	for (int i = 0; i < 26; i++) {
		if (num < letterMults[i]) {
			return i + 97;
		}
	}
}

/*
	generates the Boggle board
*/
void generateBoard(char board[4][4]) {
	for (int x = 0; x < 4; x++) {
		for (int y = 0; y < 4; y++) {
			double random = ((double)rand() / (RAND_MAX));
			board[x][y] = getLetter(random);
		}
	}
}

/*
	Determines whether or not the game is over
*/
bool gameOver() {
	if(timerUsed) 
		return (secondsEnd - time(NULL)) <= 0;
	return false;
}

/*
	Displays the board, score, and anything else that may need to be displayed regularly
*/
void displayBoard(char board[4][4]) {
	if (timerUsed) {
		cout << "Seconds remaining: " << secondsEnd - time(NULL) << endl;
	}

	for (int x = 0; x < 4; x++) {
		for (int y = 0; y < 4; y++) {
			cout << board[x][y];
		}
		cout << endl;
	}
	cout << endl;
	cout << "Score: " << score << endl;
	if (numWordsScored > 0) {
		cout << "Words so far are: ";
		for (int x = 0; x < numWordsScored; x++) {
			cout << usedWords[x] << " ";
		}
		cout << endl;
	}

}

/*
	Checks if a word is in the dictionary
*/
bool validWord(char word[]) {
	int min = 0;
	int max = numWords - 1;
	int mid = 1;
	int found = 0;
	while (!found) {
		mid = (max + min) / 2;
		int num = strcmp(word, dictionary[mid]);
		if (num == 0) {
			return true;
		}
		if (min == max - 1) {
			return false;
		}
		if (num != 0) {
			if (num > 0) {
				min = mid;
			}
			else {
				max = mid;
			}
		}
		else {
			return true;;
		}
	}
	
}

/*
	Checks if a word is in a list
*/
bool isInList(char list[][17], char word[], int numWords) {
	for (int x = 0; x < numWords; x++) {
		if (strcmp(list[x], word) == 0) {
			return true;
		}
	}
	return false;
}

/*
	Sorts the words in a list
*/
void sortWords(char listWords[][17], int numWords) {
	for (int x = 0; x < numWords; x++) {
		for (int y = 0; y < numWords-1; y++) {
			if (strcmp(listWords[y], listWords[y + 1]) > 0) {
				char tempWord[17];
				strcpy(tempWord, listWords[y]);
				strcpy(listWords[y], listWords[y+1]);
				strcpy(listWords[y+1], tempWord);
			}
		}
	}
	
	int counter = 0;
	for (int n = 3; n <= 16; n++) {
		for (int i = 0; i < 256; i++) {
			if (strlen(listWords[i]) == n) {
				char tempWord[17];  
				strcpy(tempWord,listWords[i]);
				strcpy(listWords[i], listWords[counter]);
				strcpy(listWords[counter], tempWord);
				counter++;
			}
		}
	}	
}

/*
	Adds a word to a list
*/
void addToList(char list[][17], char word[], int &numInList) {
	strcpy(list[numInList], word);
	numInList++;
}

/*
	adds a word to the found words list
*/
void addToFoundWords(char word[]) {
	for (int x = 0; x < numWordsOnBoard; x++) {
		if (strcmp(word, wordsOnBoard[x]) == 0) {
			return;
		}
	}
	strcpy(wordsOnBoard[numWordsOnBoard], word);
	numWordsOnBoard++;
}

/*
	Helper function for findAllWords()
*/
void findAllWordsRec(bool seen[][4], int x, int y, char tempWord[]) {
	if (strlen(tempWord) >= 17) {
		return;
	}
	seen[x][y] = true;

	int i = strlen(tempWord);
	tempWord[i] = board[x][y];
	tempWord[i + 1] = '\0';

	if (validWord(tempWord)) {
		addToFoundWords(tempWord);
	}
	
	for (int tx = x - 1; tx <= x + 1 && tx < 4; tx++) {
		for (int ty = y - 1; ty <= y + 1 && ty < 4; ty++) {
			if (tx >= 0 && ty >= 0 && !seen[tx][ty]) {
				findAllWordsRec(seen, tx, ty,tempWord);
			}
		}
	}
	strcpy(tempWord, "");
	seen[x][y] = false;
}

/*
	Finds all the words on the boggle board
*/
void findAllWords() {
	bool seen[4][4] = { {false,false,false,false},
						{false,false,false,false},
						{false,false,false,false},
						{false,false,false,false} };

	char tempWord[64] = "";

	for (int x = 0; x < 4; x++) {
		for (int y = 0; y < 4; y++) {
			findAllWordsRec(seen, x, y, tempWord);
		}
	}
}

/*
	Returns the score of the given word
*/
int getScore(char word[]) {
	int l = strlen(word);
	if (l >= 6) {
		return l;
	}
	else if (l == 3) {
		return 1;
	}
	else if (l == 4) {
		return 2;
	}
	else if (l == 5) {
		return 4;
	}
	else return 0;
}

/*
	Puts the dictionary words into the array
*/
void putWordsInArray(char ** &dictionary) {
	// allocate memory
	allocateArray(dictionary);

	// Now read the words from the dictionary
	ifstream inStream;                          // declare an input stream for my use
	int wordRow = -1;                            // Row for the current word
	inStream.open("dictionary.txt");

	assert(!inStream.fail());

	while (inStream >> dictionary[++wordRow])
		if (strlen(dictionary[wordRow]) > 16 || strlen(dictionary[wordRow]) < 3)
			wordRow--;
	numWords = wordRow;


	cout << wordRow << " words were read in." << endl;
}

/*
	Checks if the word is on the board
*/
bool onBoard(char word[]) {
	for (int x = 0; x < numWordsOnBoard; x++) {
		if (strcmp(word, wordsOnBoard[x]) == 0) {
			return true;
		}
	}
	return false;
}

int main() {
	srand(time(NULL));
	generateBoard(board);

	for (int x = 0; x < 64; x++) { // Makes all of the used words into nothing
		strcpy(usedWords[x], "");
	}
	putWordsInArray(dictionary); // Puts all words in dictionary.txt into dictionary the array

	int counter = 1;
	char testWord[64];

	findAllWords();
	sortWords(wordsOnBoard, numWordsOnBoard);

	secondsStart = time(NULL);
	secondsEnd = secondsStart + 60;

	while (!gameOver()) {
		displayBoard(board);
		cout << counter << ". Enter a word: ";
		cin >> testWord;
		if (strcmp(testWord, "t") == 0) {
			timerUsed = !timerUsed;
		}
		else if (strcmp(testWord, "s") == 0) {
			cout << "enter minimum and maximum word lengths to display: ";
			int min, max;
			cin >> min;
			cin >> max;
			for (int x = 0; x < numWordsOnBoard; x++) {
				if (strlen(wordsOnBoard[x]) >= min && strlen(wordsOnBoard[x]) <= max) {
					cout << wordsOnBoard[x] << " ";
				}
			}
			cout << endl;
			return 0;
		}
		else if (strcmp(testWord, "r") == 0) {
			char newB[32];
			do {
				cout << "Enter 16 new characters to be used to set the board: ";
				cin >> newB;
			} while (strlen(newB) != 16);
			for (int x = 0; x < 4; x++) {
				strncpy(board[x], newB, 4);
				memmove(newB, newB + 4, strlen(newB));
			}
			for (int x = 0; x < 256; x++) {
				strcpy(wordsOnBoard[x], "");
			} 
			numWordsOnBoard = 0;
			findAllWords();
			sortWords(wordsOnBoard, numWordsOnBoard);
		}
		else {

			if (onBoard(testWord) && !isInList(usedWords, testWord, numWordsScored)) {
				score += getScore(testWord);
				addToList(usedWords, testWord, numWordsScored);
				cout << "Worth " << getScore(testWord) << " points." << endl << endl;
			}
			else {
				cout << "That word is not valid, please try again!" << endl << endl;
			}
			counter++;
		}
		
	}

	cout << "Game over, exiting program" << endl;
	
    return 0;
}

