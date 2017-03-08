#include <stdio.h>

/*
Pawel Klimek UIN: 671153576
Jacob Waller UIN: 673978936
*/

int length(char *arr) {
  int index = 0;
  while(arr[index] != '\0') {
    index++;
  }
  return index;
}

int contains(char c, char *arr) {
  int len = length(arr);
  int x;
  for(x = 0; x < len; x++) {
    if(arr[x] == c) {
      return x;
    }
  }
  return -1;
}

int isPalindrome(char *arr) {
  int len = length(arr);
  int x;
  for(x = 0; x < len/2;x++) {
    if(arr[x] != arr[(len-1)-x]) {
      return 0;
    }
  }
  return 1;
}

int main() {
  printf("Pawel Klimek UIN: 671153576\n");
  printf("Jacob Waller UIN: 673978936\n");

  char test[256];
  printf("Please enter a string:\n");
  scanf(" %s", test);
  int done = 0;
  while(!done) {
    printf("Which function do you want to use? 1:Length, 2:contains, 3:isPalindrome, 0:Exit\n");
    char option;
    scanf(" %c", &option);
    char tester;
    switch (option) {
      case '1':
        printf("Length is: %d\n", length(test));
        break;
      case '2':
        printf("Please enter a character to search for: ");
        scanf(" %c", &tester);
        printf("The character is at position: %d\n", contains(tester,test)+1);
        break;
      case '3':
        if(isPalindrome(test)) {
          printf("\"%s\" is a palindrome\n",test);
        } else {
          printf("\"%s\" is not a palindrome\n",test);
        }
        break;
      case '0':
        done = 1;
        break;
    }
  }


}

/*

Line 0File 0Project 0No Issueslab5.c59:15
CRLFUTF-8C3 updates
C:\Users\Jacob Waller\Desktop\School Stuff\CS-141\lab5.c: In function 'main':
C:\Users\Jacob Waller\Desktop\School Stuff\CS-141\lab5.c:45:9: error: a label can only be part of a statement and a declaration is not a statement
char tester;
^
*/
