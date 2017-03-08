#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
Author: Jacob Waller
Program: 2
Lab: 11am Tuesday
TA: Nilanjana Basu
Date: 9/18/16
*/


/*
  Prints all the info I'm supposed to have
*/
void printIDInfo() {
  printf("Author: Jacob Waller\n");
	printf("Program: 2\n");
  printf("Lab: 11am Tuesday\n");
	printf("TA: Nilanjana Basu \n");
}


/*
  Returns 1 if the given string contains the character c
  Returns 0 otherwise
*/
int contains(char *arr, char c) {
  int x;
  for(x = 0 ; x < strlen(arr); x++) {
    if(arr[x] == c) {
      return 1;
    }
  }
  return 0;
}

/*
  Returns 1 if the given word can be made with only characters from category
  Returns 0 otherwise
*/
int canBeMadeWithOnly(char *word, int length, char *category) {
  int x;
  for(x = 0; x < length; x++) {
    if(!contains(category,word[x])) {
      return 0;
    }
  }
  return 1;
}


/*
  Main, this is what runs.
*/
int main() {
  printIDInfo();

  /*
    Create all the Strings for checking where characters are
  */
  char homeRowQ[]          = "asdfghjkl;':\"ASDFGHJKL"; // Obvious zero inches
  char middleKeysQ[]       = "gh"; // .75 inches
  char straightUpQ[]       = "qweruiop"; // .75 inches
  char straightDownQ[]     = "zxcvm,./n";
  char middleVerticalQ[]   = "ty"; // 1.5 inches
  char rightHandQ[]        = "67890-_=+uiopUIOP[{}]\\|hjklHJKLNM;:'\"nm,<.>/?"; //Removed periods, commas, and QM
  char leftHandQ[]         = "12345!@#$%%qwertasdfgzxcvQWERTASDFGZXCVB";
  char bQ                  = 'b'; // 1.25 inches

  char homeRowD[]          = "aoeuhtnsid"; // Obvious zero inches
  char middleKeysD[]       = "id"; // .75 inches
  char straightUpD[]       = "'\",<.>pgcrl"; // .75 inches
  char straightDownD[]     = ";:qjkmwvzn";
  char middleVerticalD[]   = "yf"; // 1.5 inches
  char rightHandD[]        = "fgcrl/?+=\\|dhtns-_bmwvz";
  char leftHandD[]         = "'\",<.>pyaoeui;:qjkx";
  char bD                  = 'x'; // 1.25 inches

  char numbersleft[]       = "12345!@#$%%"; // 1.5 inches
  char numbersRight[]      = "7890-_=+&*()"; // 1.5 inches
  char numbersMiddle[]     = "6^"; // 1.75 inches

  /*
    Declare and initialize all the variables used for storing important infoa
  */
  int totalWordCount       = 0;
  int totalNonBlankChars   = 0;
  int charsOnHomeRowQ      = 0;
  int charsOnHomeRowD      = 0;
  int wordsOnHomeRowQ      = 0;
  int wordsOnHomeRowD      = 0;
  int wordsTypedOnOneHandQ = 0;
  int wordsTypedOnOneHandD = 0;
  double inchesTraveledQ   = 0.0;
  double inchesTraveledD   = 0.0;

  //Get the file going with our text file as read only
  FILE *fp;
  fp = fopen("20000LeaguesUnderTheSeaJulesVerne.txt","r");

  //Max word length is 256
  char tempWord[256];

  //Loop to go through every word
  while(fscanf(fp, "%s", tempWord) != EOF) {
    //Convert tempWord to lowercase
    int i;
    for(i = 0; tempWord[i]; i++){
      tempWord[i] = tolower(tempWord[i]);
    }

    //Count words and characters
    totalWordCount++;
    int lengthOfWord = strlen(tempWord);
    totalNonBlankChars+=lengthOfWord;

    //Count Inches traveled
    int x;
    for(x = 0; x < lengthOfWord; x++) {
      char letter = tempWord[x];

      if(contains(middleKeysQ,letter)) {
        inchesTraveledQ += 0.75;
      }
      if(contains(straightUpQ,letter)) {
        inchesTraveledQ += 0.75;
      }
      if(contains(straightDownQ,letter)) {
        inchesTraveledQ += 0.75;
      }
      if(contains(middleVerticalQ,letter)) {
        inchesTraveledQ += 1.5;
      }
      if(letter == bQ) {
        inchesTraveledQ += 1.25;
      }

      if(contains(middleKeysD,letter)) {
        inchesTraveledD += 0.75;
      }
      if(contains(straightUpD,letter)) {
        inchesTraveledD += 0.75;
      }
      if(contains(straightDownD,letter)) {
        inchesTraveledD += 0.75;
      }
      if(contains(middleVerticalD,letter)) {
        inchesTraveledD += 1.5;
      }
      if(letter == bD) {
        inchesTraveledD += 1.25;
      }

      if(contains(homeRowQ,letter)) {
        charsOnHomeRowQ++;
      }
      if(contains(homeRowD,letter)) {
        charsOnHomeRowD++;
      }
    }


    //Strip punctuation and any other nasty characters from our word
    x = 0;
    char notGoodChars[] = ".,/?\"\'\\-_[]!@#$%%^&*()";
    while(tempWord[x] != '\0') {
      if(contains(notGoodChars,tempWord[x])) {
          int y = x;
          while(tempWord[y] != '\0') {
            tempWord[y] = tempWord[y+1];
            y++;
            x=0;
          }
        }
        x++;
    }
    //Check if the word can be written from individual sections
    lengthOfWord = strlen(tempWord);
    if(canBeMadeWithOnly(tempWord,lengthOfWord,homeRowQ)) { // Only home row in QWERTY
      wordsOnHomeRowQ++;
    }
    if(canBeMadeWithOnly(tempWord,lengthOfWord,rightHandQ)) { // Only Right Hand in QWERTY
      wordsTypedOnOneHandQ++;
    }
    if(canBeMadeWithOnly(tempWord,lengthOfWord,leftHandQ)) { // Only left hand in QWERTY
      wordsTypedOnOneHandQ++;
    }
    if(canBeMadeWithOnly(tempWord,lengthOfWord,homeRowD)) { // Only Home Row in Dvorak
      wordsOnHomeRowD++;
    }
    if(canBeMadeWithOnly(tempWord,lengthOfWord,rightHandD)) { //Only Right hand in Dvorak
      wordsTypedOnOneHandD++;
    }
    if(canBeMadeWithOnly(tempWord,lengthOfWord,leftHandD)) { //Only Left Hand in Dvorak
      wordsTypedOnOneHandD++;
    }

  }

  //Convert inches to miles
  double milesTraveledQ = ((double)inchesTraveledQ) / 63360.0;
  double milesTraveledD = ((double)inchesTraveledD) / 63360.0;

  //Do all of the printing with formatting and the such
  printf("\n");
  printf("Total Word Count:            %7d\n", totalWordCount);
  printf("Total Non-Blank Characters:  %7d\n\n", totalNonBlankChars);
  printf("                                Qwerty      Dvorak\n");
  printf("%% All Characters on Home Row:  %7.0lf     %7.0lf\n",(100.0*(double)charsOnHomeRowQ)/(double)totalNonBlankChars,(100.0*(double)charsOnHomeRowD)/(double)totalNonBlankChars);
  printf("     %% All words on home row:  %7.0lf     %7.0lf\n",(100.0*(double)wordsOnHomeRowQ)/(double)totalWordCount,(100.0*(double)wordsOnHomeRowD)/(double)totalWordCount);

  printf("   %% Words typed on one hand:  %7.0lf     %7.0lf\n",(100.0*(double)wordsTypedOnOneHandQ)/(double)totalWordCount,(100.0*(double)wordsTypedOnOneHandD)/(double)totalWordCount);

  printf("    Distance Traveled(miles):  %7.3lf     %7.3lf\n",milesTraveledQ,milesTraveledD);

  //Close file
  fclose(fp);
  return 0;
}
