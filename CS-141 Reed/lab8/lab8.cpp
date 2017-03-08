/*
  Jacob Waller - 637978936
  Pawel Klimek - 671153576
*/

#include <iostream>
#include <string.h>
#include <stdio.h>
#include <ctype.h>

//function to uppercase a character in C++, parameter is a reference
void toUpperCaseInCpp(char &c){
    c = toupper(c);
}

//function to uppercase a character in C, parameter is a pointer
void toUpperCaseInC(char *c){
    *c = toupper(*c);
}

//function to add 1 to each element of an integer array
void addOneToEachElement(int a[]){
    int i = 0;
    for(i = 0; i < 5; i++){
        a[i] += 1;
    }
}

//function to ask user to input a word from keyboard, and store it in a character array
void readWordIntoCharArray(char w[]){
    printf("Enter a word to be stored: ");
    scanf("%s", w);
}

//function to sum all element in a integer matrix, and return the result
int sumOfAllTableValues(int t[][3]){
    int sum = 0;
    int i = 0;
    int j = 0;
    for(i = 0; i < 2; i++){
        for(j = 0; j < 3; j++){
            sum += t[i][j];
        }
    }
    return sum;
}

//function to ask user to input 3 words from keyboard, and store them in a 2D character array
void readMultipleWordsIntoList(char w[][20]){
    int i = 0;
    for(i = 0; i < 3; i++){
        printf("Enter a word: ");
        scanf("%s", w[i]);
    }
}

int main() {
    std::cout << "Jacob Waller 637978936" << std::endl << "Pawel Klimek 671153576";

    char c1 = 'a';
    char c2 = 'b';
    char letters[20];
    int i = 0;
    int numbers[5] = {1, 2, 3, 4, 5};
    int table[2][3] = {{1, 2, 3}, {4, 5, 6}};
    int total = 0;
    char wordList[3][20];

    //Uppercase variable c1 in C++
    toUpperCaseInCpp(c1);

    //Uppercase variable c2 in C
    toUpperCaseInC(&c2);

    //Now c1 should be A
    std::cout << "1. c1 = " << c1 <<std::endl;

    //Now c2 should be B
    std::cout << "2. c2 = " << c2 <<std::endl;

    //Add 1 to each element of numbers[] array
    addOneToEachElement(numbers);

    std::cout << "3." <<std::endl;

    //numbers[] array should now be {2, 3, 4, 5, 6}
    for(i = 0; i < 5; i++){
        std::cout << numbers[i] << ", ";
    }
    std::cout << std::endl;

    //Ask user to input a word from keyboard, and store it in letters[] array
    readWordIntoCharArray(letters);

    std::cout << "4." <<std::endl;

    //Print out each character to make sure it works
    for(i = 0; i < strlen(letters); i++){
        std::cout << letters[i] << " ";
    }
    std::cout << std::endl;

    std::cout << "5." <<std::endl;

    //Take the sum of all element in the table[] array and assign the result to the total variable
    total = sumOfAllTableValues(table);

    //Printout value of total, it should be "total = 21"
    std::cout << "total = " << total <<std::endl;

    //Ask user to input 3 words from keyboard, and store them in the wordList[][] array
    readMultipleWordsIntoList(wordList);

    std::cout << "6." <<std::endl;

    //Printout each word, to make sure you did correctly
    for(i = 0; i < 3; i++){
        std::cout << wordList[i] << std::endl;
    }
    return 0;
}
