#include <stdio.h>
#include <string.h>
#include <math.h>

/*
  Jacob Waller - 673978936
*/

int convertBinaryArrayToDecimal( int digits[8]) {
  int i,result = 0;
  for(i = 0; i < 8; i++) {
    if(digits[i] == 1) {
      result += pow(2,7-i);
    }
  }
  return result;
}

void convertDecimalToBinaryArray(int numberToConvert, int binaryDigits[]) {
  int i = 0;
  for(i = 7;i>=0;i--) {
    binaryDigits[i]=numberToConvert%2;
    numberToConvert=numberToConvert/2;
  }
}

char convert4BinaryBitsToHex( int binaryDigits[4]) {
char hexadecimal = ' ';
//your code goes here
int nums[8] = {0,0,0,0,0,0,0,0};
int i;
for(i = 4; i < 8; i++) {
  nums[i] = binaryDigits[i-4];
}
int num = convertBinaryArrayToDecimal(nums);
switch (num) {
  case 0: return '0';
  case 1: return '1';
  case 2: return '2';
  case 3: return '3';
  case 4: return '4';
  case 5: return '5';
  case 6: return '6';
  case 7: return '7';
  case 8: return '8';
  case 9: return '9';
  case 10: return 'A';
  case 11: return 'B';
  case 12: return 'C';
  case 13: return 'D';
  case 14: return 'E';
  case 15: return 'F';
}
return hexadecimal;
}

int main() {
printf("Jacob Waller - 673978936\nPawel Klimek - 671153576 \n");

  int digits[8]; // Binary numbers used as input for stage 1
  int decimalResult;
  int decimalNumber; // Decimal number used as input for stage 2
  int binaryDigits[8]; //the binaryDigits of stage 2
  int fourBinaryDigits[4];
  int i=0;

  printf("NetIDs: jwalle9, pklime4 \n");
  printf("Lab section: 11 am Tues \n");

  // Perform Stage 1
  printf("==============Stage 1==============\n");
  printf("Please enter 8 binary digits\n");
  for(i = 0; i < 8 ; i++)
  {
  	scanf("%d",&(digits[i]));        //read in the binary digits, suppose the user's input is good
  }

  decimalResult = convertBinaryArrayToDecimal(digits);    //call the function to convert binary digits to decimal
  printf("The corresponding decimal is : %d \n", decimalResult);

  // Perform Stage 2
  printf("==============Stage 2==============\n");
  printf("Please enter any single digit decimal number \n");
  scanf("%d", &decimalNumber);

  convertDecimalToBinaryArray(decimalNumber, binaryDigits);   //call the function to fill in binaryDigits array with the corresponding binary digits of decimalNumber

  printf("The corresponding Binary is: ");
  for(i = 0; i < 8; i++)
  {
  	printf("%d",binaryDigits[i]);     //read in the binary digits, suppose the input is good
  }


  // Perform Stage 3 - Extra credit
  printf("\n ==============Stage 3==============\n");
  printf("\nPlease enter 4 binary digits: ");

  for(i = 0; i<4; i++)
  {
  	scanf("%d",&(fourBinaryDigits[i]));
  }
  printf("The corresponding Hexadecimal is: %c \n",convert4BinaryBitsToHex(fourBinaryDigits));

  printf("\nEnd of Base conversion program \n");
  printf("=========================\n");

  return 0;
}
