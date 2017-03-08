/*
  Name: Jacob Waller
  Description: This program will play the game of TwentyFour
    It will print out a set of four numbers and
    then it is the user's job to come up with the
    three operators that can be applied to those numbers
    to reult in TwentyFour.
  Stage: One
  Date: 8/25/2016
  Last Edit: 8/27/16
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/*
  Creates a random number between 0 and "max"
*/
int rand(int max) {
  srand(time(NULL));
  return rand() % (max+1);
}

/*
  Returns the result of a +,-,*, or / b. Depending on the value of op
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
  Determines if all 3 chars are one of the following: * / - +
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
    printf("You good");
    return true;
  }
}

/*
  The beginning of the program.
*/
int main() {
  printf("Author: Jacob Waller\n");
	printf("Program: 1, Twenty-Four\n");
	printf("TA: *&^  \n\n");

	printf("Welcome to the game of TwentyFour.  Using each of the four numbers shown\n");
	printf("below exactly once, combining them somehow with the basic mathematical\n");
	printf("operators (+,-,*,/) to yield the value twenty-four.\n");

  //Start a loop; Used for playing again
  bool done = false;
  while(!done) {
    int num1, num2, num3, num4;
    int random = rand(5);
    switch (random) {
      case 0:
      num1 = 8;
      num2 = 5;
      num3 = 8;
      num4 = 1;
      break;
      case 1:
      num1 = 6;
      num2 = 1;
      num3 = 5;
      num4 = 1;
      break;
      case 2:
      num1 = 2;
      num2 = 8;
      num3 = 7;
      num4 = 8;
      break;
      case 3:
      num1 = 3;
      num2 = 9;
      num3 = 4;
      num4 = 1;
      break;
      case 4:
      num1 = 5;
      num2 = 2;
      num3 = 9;
      num4 = 2;
      break;
      case 5:
      num1 = 2;
      num2 = 6;
      num3 = 8;
      num4 = 4;
      break;
    }

    //Show the player the numbers
    printf("The numbers to be used are: %d, %d, %d, and %d\n\n", num1, num2, num3, num4 );
    printf("Enter the three operators to be used: ");

    //Get the operators from the player
    char op1, op2, op3;
    do {
      scanf(" %c%c%c", &op1, &op2, &op3);
      if(!isCorrectOps(op1,op2,op3)) {
        printf("Please only use the characters +, -, *, and /");
      }
    } while(!isCorrectOps(op1,op2,op3));

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
    } else {
      printf("Well that's not quite right you silly goose!\n");
    }

    //Determine if the player wants to play again.
    char playAgain;
    do {
      printf("Would you like to play again? Y/N: ");
      scanf(" %c", &playAgain);
    } while(playAgain != 'Y' && playAgain != 'N');
    if(playAgain == 'N') {
      done = true;
    } else {
      done = false;
    }
  }
  return 0;
}
