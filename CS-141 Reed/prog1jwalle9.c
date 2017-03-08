/*
  Name: Jacob Waller
  Description: This program will print out all of the combinations
  of numbers and operators that will, when evaluated, equal twenty-four
  Then it will allow the user to play a game of twenty four using a
  randomly generated set of numbers

  Stage: Two
  Class: CS-141 3PM MWF
  System: Windows 10
  Editor: Atom
  ToDo: Done
  Date: 8/25/2016
  Last Edit: 8/29/16
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//Change to 0 to hide debug info, change to 1 to show debug info.
const int SHOW_DEBUG = 1;


/*
  Returns a random number between 0 and "max"
*/
int rand(int max) {
  srand(time(NULL));
  return rand() % (max+1);
}


/*
  Used to convert a number from 0 to 63 to base 4
  When passed "original" the function will return
  the base four representation "original"
*/
int convertToBase4(int original) {
  int num = original;
  int dig1 = 0, dig2 = 0, dig3 = 0; //Create the different digits of the number
  while(num >= 16) {//Create the 16's digit
    dig1++;
    num-=16;
  }
  while(num >= 4) {//Create the 4's digit
    dig2++;
    num-=4;
  }
  while(num > 0) {//create the 1's digit
    dig3++;
    num-=1;
  }
  return (dig1*100) + (dig2*10) + dig3; //Add all the digits together to make one number
}


/*
  Translates from "num" to a char and then
  returns the char
  0 being +
  1 being -
  2 being *
  and 3 being /
*/
char translateChar(int num) {
  if(num == 0) {
    return '+';
  }
  if(num == 1) {
    return '-';
  }
  if(num == 2) {
    return '*';
  }
  if(num == 3) {
    return '/';
  }
}


/*
  Will return the value of 'a' and 'b' evaluated with the char 'op'
*/
double solveOp(char op, double a, int b) {
	double result;
	if (op == '+')
		result = a + b;
	if (op == '-')
		result = a - b;
	if (op == '*')
		result = a * b;
	if (op == '/')
		result = a / b;
	return result;
}


/*
  Returns true if 'op1', 'op2', and 'op3' are all valid operators
*/
bool isCorrectOps(char op1, char op2, char op3) {
  if(!(op1 == '*' || op1 == '/' || op1 == '+' || op1 =='-')) {
    return false;
  }
  else if(!(op2 == '*' || op2 == '/' || op2 == '+' || op2 =='-')) {
    return false;
  }
  else if(!(op3 == '*' || op3 == '/' || op3 == '+' || op3 =='-')) {
    return false;
  } else {
    return true;
  }
}


/*
  When given three operators 'op1', 'op2', and 'op3' and four numbers 'dig1',
  'dig2', 'dig3', and 'dig4', this will return the value of the all of the numbers
  evaluated with the given operators
*/
bool isCorrect(char op1, char op2, char op3, int dig1, int dig2, int dig3, int dig4) {
  //If operators are all valid
  if(isCorrectOps(op1,op2,op3)) {
    //Calculate final value
    double value = solveOp(op1,(double)dig1,(double)dig2);
    value = solveOp(op2,value,(double)dig3);
    value = solveOp(op3,value,(double)dig4);

    //Check if value is 24
    if(value == 24) {
      return true;
    } else {
      return false;
    }
  } else {
    return false;
  }
}


/*
  This will generate and print all the combinations of 4 digits 1-9 and 3
  operators that equal 24
*/
void generateAllOptions() {
  //Create ints for each digit
  int digit1 = 1, digit2 = 1, digit3 = 1, digit4 = 1;
  //Counter for the number of correct combinations
  int counter = 1;
  //Number that stores which combination of operators are being used.
  int operationCounter = 0;

  /*
    While the leftmost digit is less than or equal to 9
    Used to go through every combination of numbers
  */
  while(digit1 <= 9) {
    //Used to go through every combination of operators
    for(operationCounter = 0; operationCounter < 64; operationCounter++) {
      /*
        Using operationCounter, get 3 differen operators by converting operationCounter
        into a 3 digit base 4 number. Then assign those digits to different opNumbers.
      */
      int charSet = convertToBase4(operationCounter);
      int firstOpNumber = charSet/100;
      charSet  = charSet - firstOpNumber*100;
      int secondOpNumber = charSet/10;
      charSet  = charSet - secondOpNumber*10;
      int thirdOpNumber = charSet;

      //Check and see if the current set of numbers and operators gives 24
      if(isCorrect(translateChar(firstOpNumber),translateChar(secondOpNumber),translateChar(thirdOpNumber), digit1, digit2, digit3, digit4)) {
        printf("%d: %d %c %d %c %d %c %d\n", counter, digit1, translateChar(firstOpNumber), digit2, translateChar(secondOpNumber), digit3, translateChar(thirdOpNumber), digit4);
        counter++;
      }
    }
    //Count the numbers up by one.
    digit4++;
    if(digit4 == 10) {
      digit4 = 1;
      digit3++;
    }
    if(digit3 >= 10) {
      digit3 = 1;
      digit2++;
    }
    if(digit2 >= 10) {
      digit2 = 1;
      digit1++;
    }
  }
  printf("***END DEBUG DISPLAY***\n\n");
}


/*
  This function will return and print the four digits of the
  option chosen by the 'optionNumber'
*/
int getSingleOption(int optionNumber) {
  //Create ints for each digit
  int digit1 = 1, digit2 = 1, digit3 = 1, digit4 = 1;
  //Counter for the number of correct combinations
  int counter = 1;
  //Number that stores which combination of operators are being used.
  int operationCounter = 0;

  /*
    While the leftmost digit is less than or equal to 9
    Used to go through every combination of numbers
  */
  while(digit1 <= 9) {
    //Used to go through every combination of operators
    for(operationCounter = 0; operationCounter < 64; operationCounter++) {
      /*
        Using operationCounter, get 3 differen operators by converting operationCounter
        into a 3 digit base 4 number. Then assign those digits to different opNumbers.
      */
      int charSet = convertToBase4(operationCounter);
      int firstOpNumber = charSet/100;
      charSet  = charSet - firstOpNumber*100;
      int secondOpNumber = charSet/10;
      charSet  = charSet - secondOpNumber*10;
      int thirdOpNumber = charSet;

      //Check and see if the current set of numbers and operators gives 24
      if(isCorrect(translateChar(firstOpNumber),translateChar(secondOpNumber),translateChar(thirdOpNumber), digit1, digit2, digit3, digit4)) {
        if(counter == optionNumber) {
          printf("The numbers to use are: %d, %d, %d, %d ", digit1, digit2, digit3, digit4);
          return (digit1*1000) + (digit2*100) + (digit3*10) + digit4;
        }
        counter++;
      }
    }
    //Count the numbers up by one.
    digit4++;
    if(digit4 == 10) {
      digit4 = 1;
      digit3++;
    }
    if(digit3 >= 10) {
      digit3 = 1;
      digit2++;
    }
    if(digit2 >= 10) {
      digit2 = 1;
      digit1++;
    }
  }
}


/*
  The beginning of the program. Returns 0.
*/
int main() {
  printf("Author: Jacob Waller\n");
	printf("Program: 1, Twenty-Four\n");
  printf("Lab: 11am Tuesday\n");
	printf("TA: Minh \n\n");

	printf("Welcome to the game of TwentyFour.  Using each of the four numbers shown\n");
	printf("below exactly once, combining them somehow with the basic mathematical\n");
	printf("operators (+,-,*,/) to yield the value twenty-four.\n");

  if(SHOW_DEBUG) {
    //Do the debug display
    generateAllOptions();
  }

  //Start a loop; Used for playing again
  bool done = false;
  while(!done) {
    int num1, num2, num3, num4;
    //Pick a random number set
    int randomSelection = rand(3185);
    //Use the printSingleOption function to find and return the option numbers
    int numSet = getSingleOption(randomSelection);
    //Translates the four digit number into its individual 4 digits.
    num1 = numSet / 1000;
    numSet = numSet - (num1 * 1000);
    num2 = numSet / 100;
    numSet = numSet - (num2 * 100);
    num3 = numSet / 10;
    numSet = numSet - (num3 * 10);
    num4 = numSet;
    char lengthChecker;
    bool correct = false;
    while(!correct) {
      printf("Enter the three operators to be used: ");
      //Get the operators from the player
      char op1, op2, op3;
      do {
        scanf(" %c%c%c%c", &op1, &op2, &op3, &lengthChecker);
        if(!isCorrectOps(op1,op2,op3)) { //Checks that all characters are valid
          printf("Please only use the characters +, -, *, and /\n");
          fflush(stdin);
        }

        if(lengthChecker!='\n') {
          printf("Only enter in three characters!\n");
          fflush(stdin);
        }

      } while((!isCorrectOps(op1,op2,op3)) || lengthChecker!='\n' );

      //Print out and solve the math
      double result = num1;
      printf("%.1lf %c %d = %.1lf\n", result, op1, num2, solveOp(op1,result,num2));
      result = solveOp(op1,result,num2);
      printf("%.1lf %c %d = %.1lf\n", result, op2, num3, solveOp(op2,result,num3));
      result = solveOp(op2,result,num3);
      printf("%.1lf %c %d = %.1lf\n", result, op3, num4, solveOp(op3,result,num4));
      result = solveOp(op3,result,num4);
      //Did I win or lose?
      if(result == 24) {
        printf("Great job genius!\n");
        correct = true;
      } else {
        printf("Well that's not quite right you silly goose!\n");
        printf("Please try again!\n\n");
        printf("Remember, the numbers are: %d, %d, %d, and %d\n", num1, num2, num3, num4);
      }
    }

    //Determine if the player wants to play again.
    char playAgain;
    do {
      printf("Would you like to play again? Y/N: \n");
      scanf(" %c", &playAgain);
      //fflush(stdin);
    } while(playAgain != 'Y' && playAgain != 'N');
    if(playAgain == 'N') {
      done = true;
    } else {
      done = false;
      printf("\n");
    }
  }
  return 0;
}
