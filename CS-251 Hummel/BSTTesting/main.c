#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "bst.h"


//
// skipRestOfInput:
//
// Inputs and discards the remainder of the current line for the
// given input stream, including the EOL character(s).
//
void skipRestOfInput(FILE *stream)
{
  char restOfLine[256];
  int rolLength = sizeof(restOfLine) / sizeof(restOfLine[0]);

  fgets(restOfLine, rolLength, stream);
}


//
// BuildTree:
//
// Inputs data from keyboard, stores in a BST, and returns tree.
//
BST *BuildTree()
{
  char x[64];
  int  y = 1;
  BSTValue  value;
  FILE* myFile = fopen("dictionary.txt","r");
  BST *tree = BSTCreate();

  //
  // input:
  //   Key Value
  //   Key Value
  //   ...
  //   #
  //
  printf("Starting read\n");
  fscanf(myFile,"%s", x);
  while (fscanf(myFile,"%s", x)!=EOF)
  {
    //scanf("%d",&y);
    //printf("%s\n",x);

    //skipRestOfInput(stdin);

    //
    // store (key, value) into a BST value struct to insert:
    //
    value.X = (char *)malloc((strlen(x) + 1) * sizeof(char));
    strcpy(value.X, x);

    value.Y = y;

    //
    // now that we have (key, value) pair, call BSTInsert to
    // copy this (key, value) pair into a new node of the tree:
    //
    if(!AVLInsert(tree, value.X, value) ) {
      printf("NOT INSERTING: %s\n",x);
    }

    //fscanf(myFile,"%s", x);  // get next netid or #:
  }
  printf("Done Reading\n");

  //
  // done, return tree:
  //
  return tree;
}


//
// PrintOneNode:
//
// Prints the info in one node of the tree.  This function is passed
// to BST inorder/preorder functions to control printing when a node
// is "visited".
//
void PrintOneNode(BSTNode *cur)
{
  printf("%s: %d\n", cur->Value.X, cur->Value.Y);
}


int main()
{
  BST *tree;

  //
  // input and build tree:
  //
  tree = BuildTree();

  //
  // ouput before and after:
  //
  char i[8];
  printf(">>Height: %d\n", BSTHeight(tree));
  printf(">>Node Count: %d\n", tree->Count);
  printf("Want to print out 'InOrder?': ");
  scanf("%s",i);

  if(i[0]=='y'||i[0]=='Y') BSTPrintInorder(tree, PrintOneNode);


  return 0;
}
