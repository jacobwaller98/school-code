#include <stdio.h>
#include <string.h>

/*
  Author: Jacob Waller - 673978936
  Program: 3
  Lab: 11am Tuesday
  TA: Minh
*/

void printIDInfo() {
  printf("Author: Jacob Waller - 673978936\n");
	printf("Program: 3\n");
  printf("Lab: 11am Tuesday\n");
	printf("TA: Minh \n");
}


/*
  Will insert the given char c into the given index of the string word
  Ex. insert('a',"hello",2);
  "hello" will be "heallo"
*/
void insert(char c, char * word, int index) {
  char tempWord[64]; //Creates a string for the part after the index
  strcpy(tempWord,&word[index]); // Copies the string from index on to tempWord
  word[index] = '\0'; //Ends word by adding a null
  char addWord[] = {c,'\0'}; //Creates a string of just the char c
  strcat(word,addWord); //Adds the char
  strcat(word,tempWord); //Adds the end of the word
}


/* Will Check if deleting one character will make the word match */
int checkDeleteOnce(char word[], char list[][64]) {
  int i,result;
  int numIkeas = 1764;
  char tempWord[64];
  for(i = 0; i < strlen(word); i++) {
    strcpy(tempWord,word);
    memmove(&tempWord[i], &tempWord[i + 1], strlen(tempWord) - i); // Remove a character at i
    result = binarySearch(tempWord,list[i],numIkeas);
    if(result != -1) {
      printf("%s ",tempWord);
      return 1;
    }
  }
  return 0;
}


/* Will Check if deleting two characters will make the word match */
int checkDeleteTwice(char * word, char list[][64]) {
  int i,result;
  int numIkeas = 1764;
  char tempWord[64];
  for(i = 0; i < strlen(word); i++) {
    strcpy(tempWord,word);
    memmove(&tempWord[i], &tempWord[i + 1], strlen(tempWord) - i); // Remove a character at i
    if(checkDeleteOnce(tempWord,list)) {
      return 1;
    }
  }
  return 0;
}


/* Will Check if deleting three characters will make the word match */
int checkDeleteThrice(char * word, char list[][64]) {
  int i,result;
  int numIkeas = 1764;
  char tempWord[64];
  for(i = 0; i < strlen(word); i++) {
    strcpy(tempWord,word);
    memmove(&tempWord[i], &tempWord[i + 1], strlen(tempWord) - i); // Remove a character at i
    if(checkDeleteTwice(tempWord,list)) {
      return 1;
    }
  }
  return 0;
}


/* Will Check if Subbing one character will make the word match */
int checkSubbedOnce(char word[], char list[][64]) {
  int i,result = -1;
  char tempWord[64];
  // //Check for a substituted character
  char subbedChar;
  for(i = 0; i < strlen(word);i++) {
    strcpy(tempWord,word);
    for(subbedChar = 65;subbedChar<=90;subbedChar++) {
      tempWord[i] = subbedChar;
      result = binarySearch(tempWord,list,1764);
      if(result != -1) {
        printf("%s ",tempWord);
        return 1;
      }
    }
  }
  return 0;
}


/* Will Check if subbing two characters will make the word match */
int checkSubbedTwice(char word[], char list[][64]) {
  int i,result;
  char tempWord[64];
  // //Check for a substituted character
  char subbedChar;
  for(i = 0; i < strlen(word);i++) {
    strcpy(tempWord,word);
    for(subbedChar = 65;subbedChar<=90;subbedChar++) {
      tempWord[i] = subbedChar;
      if(checkSubbedOnce(tempWord,list)) {
          return 1;
      }
    }
  }
  return 0;
}


/* Will Check if subbing two characters will make the word match */
int checkSubbedThrice(char word[], char list[][64]) {
  int i,result;
  char tempWord[64];
  // //Check for a substituted character
  char subbedChar;
  for(i = 0; i < strlen(word);i++) {
    strcpy(tempWord,word);
    for(subbedChar = 65;subbedChar<=90;subbedChar++) {
      tempWord[i] = subbedChar;
      if(checkSubbedTwice(tempWord,list)) {
          return 1;
      }
    }
  }
  return 0;
}


/* Will Check if inserting one character will make the word match */
int checkInsertOnce(char word[], char list[][64]) {
  int i,result;
  char tempWord[64];
  char subbedChar;
  for(i = 0; i < strlen(word); i++) {
    for(subbedChar = 65; subbedChar <= 90; subbedChar++) {
      strcpy(tempWord,word);
      insert(subbedChar,tempWord,i);
      result = binarySearch(tempWord,list,1764);
      if(result != -1) {
        printf("%s ",tempWord);
        return 1;
      }
    }
  }
  return 0;
}


/* Will Check if inserting two characters will make the word match */
int checkInsertTwice(char word[], char list[][64]) {
  int i;
  char tempWord[64];
  char subbedChar;
  for(i = 0; i < strlen(word); i++) {
    for(subbedChar = 65; subbedChar <= 90; subbedChar++) {
      strcpy(tempWord,word);
      insert(subbedChar,tempWord,i);
      if(checkInsertOnce(word,list)) {
        return 1;
      }
    }
  }
  return 0;
}


/* Will Check if inserting Three characters will make the word match */
int checkInsertThrice(char word[], char list[][64]) {
  int i;
  char tempWord[64];
  char subbedChar;
  for(i = 0; i < strlen(word); i++) {
    for(subbedChar = 65; subbedChar <= 90; subbedChar++) {
      strcpy(tempWord,word);
      insert(subbedChar,tempWord,i);
      if(checkInsertOnce(word,list)) {
        return 1;
      }
    }
  }
  return 0;
}

/*  This will return the substring of word from begIndex inclusive to endIndex exclusive */
const char* substring(char word[], int begIndex, int endIndex) {
  static char tempWord[64];
  int i;
  for(i = begIndex; i < endIndex; i++) {
    tempWord[i - begIndex] = word[i];
  }
  tempWord[i] = '\0';
  return tempWord;
}


/* Returns the index of word in list */
int binarySearch(char word[], char list[][64], int numWords) {
  int min = 0;
  int max = numWords-1;
  int mid = 1;
  int found = 0;
  while(!found) {
    mid = (max+min)/2;
    int num = strcmp(word,strupr(list[mid]));
    if(num == 0) {
      return mid;
    }
    if(min == max-1) {
      return -1;
    }
    if(num != 0) {
      if(num > 0) {
        min = mid;
      } else {
        max = mid;
      }
    } else {
      return mid;
    }
  }
}


/* Will return 1 if word is a substring of a string in list */
int containsSubstring(char word[], char list[][64], int numWords) {
  int i,beginning,end;
  for(i = 0; i < numWords; i++) {
    for(beginning = 0; beginning < strlen(list[i]);beginning++) {
      for(end = beginning+1;end < strlen(list[i])+1;end++) {
        if(strcmp(strupr(word),substring(list[i],beginning,end)) == 0) {
          printf("%s ",list[i]);
          return 1;
        }
      }
    }
  }
  return 0;
}

/* Returns 1 if there are any characters in c that are also in word */
int contains(char word[], char c[]) {
  int i,x;
  for(i = 0; i < strlen(word); i++) {
    for(x = 0; x < strlen(c); x++) {
      if(word[i] == c[x]) {
        return 1;
      }
    }
  }
  return 0;
}

/* Strips all punctuation out of word */
void strip(char word[]) {
  int x = 0;
  char notGoodChars[] = ".,/?\"\'\\-_[]!@#$%%^&*()";
  while(word[x] != '\0') {
    if(contains(word,notGoodChars)) {
        int y = x;
        while(word[y] != '\0') {
          word[y] = word[y+1];
          y++;
          x=0;
        }
      }
      x++;
  }
}


int main() {

  printIDInfo();

  FILE *ikeaFile;
  FILE *dictionaryFile;
  FILE *inputFile;

  ikeaFile = fopen("IKEAwords.txt","r");
  dictionaryFile = fopen("dictionary.txt","r");
  inputFile = fopen("inputfile.txt","r+");

  int numIkeas = 1764,numDictionary = 40444,maxLength = 64;
  char IKEAwords[numIkeas][64]; //Declares a 2D array for all of our IKEA words
  char dictionaryWord[64]; // Declares an array for all of our dictionary word
  int x;
  int counter = 1;

  for(x = 0; x < numIkeas; x++) { //Puts all the words from IKEAwords.txt into an array
    fscanf(ikeaFile,"%s",IKEAwords[x]);
  }

  for(x = 0; x < numDictionary; x++) {
    fscanf(dictionaryFile,"%s",dictionaryWord);
    //Check if the dictionaryWord is directly in the file
    int result = binarySearch(strupr(dictionaryWord),IKEAwords,numIkeas);
    if(result != -1) {
      printf("%d: %s \n",counter,strupr(dictionaryWord));
      counter++;
      continue;
    }
  }

  printf("End dictionary file\n");
  printf("=======================\n");
  printf("Begin Stage 3\n");
  char word[64];
  char tempWord[64];
  while(fscanf(inputFile, "%s", tempWord) != EOF) {
    strip(tempWord);
    strcpy(word,tempWord);
    strupr(word);

    //Check if it's in exactly
    int result = binarySearch(strupr(word),IKEAwords,numIkeas);
    if(result != -1) {
      printf("%s ", word);
    } else if(checkDeleteOnce(word,IKEAwords)) {

    } else if(checkSubbedOnce(word,IKEAwords)) {

    } else if(checkInsertOnce(word,IKEAwords)) {

    } else if(containsSubstring(word,IKEAwords,numIkeas)) {

    } else {
      printf("%s ", tempWord);
    }
  }

  printf("\nEnd Stage 3\n");
  printf("=======================\n");
  printf("Begin Stage 4\n");

  //Restart the file
  fclose(inputFile);
  inputFile = fopen("inputfile.txt","r+");

  while(fscanf(inputFile, "%s", tempWord) != EOF) {
    strip(tempWord);
    strcpy(word,tempWord);
    strupr(word);
    //Check if it's in exactly
    int result = binarySearch(strupr(word),IKEAwords,numIkeas);
    if(result != -1) {
      printf("%s ", word);
    } else if(checkDeleteOnce(word,IKEAwords)) {

    } else if(checkSubbedOnce(word,IKEAwords)) {

    } else if(checkInsertOnce(word,IKEAwords)) {

    } else if(containsSubstring(word,IKEAwords,numIkeas)) {

    }
    else if(checkDeleteTwice(word,IKEAwords)) {

    } else if(checkSubbedTwice(word,IKEAwords)) {

    } else if(checkInsertTwice(word,IKEAwords)) {

    } else if(checkDeleteThrice(word,IKEAwords)) {

    } else if(checkSubbedThrice(word,IKEAwords)) {

    } else if(checkInsertThrice(word,IKEAwords)) {

    } else {
      printf("%s ", tempWord);
    }
  }
  printf("\n\nDone\n");

  fclose(dictionaryFile);
  fclose(ikeaFile);
  fclose(inputFile);

  return 0;
}
