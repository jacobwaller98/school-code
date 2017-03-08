/*main.c*/

//
// Creating binary search trees.
//
// Prof. Joe Hummel
// U. of Illinois, Chicago
// CS 251: Spring 2017
// HW 05
//

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


typedef int     BSTKey;
typedef double  BSTValue;

typedef struct BSTNode
{
  BSTKey    Key;
  BSTValue  Value;
  struct BSTNode  *Left;
  struct BSTNode  *Right;
} BSTNode;

typedef struct BST
{
  BSTNode *Root;
  int      Count;
} BST;


//
// BSTCreate: dynamically creates and returns an empty
// binary search tree:
//
BST *BSTCreate()
{
  BST *tree;

  tree = (BST *)malloc(sizeof(BST));
  tree->Root  = NULL;
  tree->Count = 0;

  return tree;
}

//
// BSTCompareKeys: compares key1 and key2, returning
//   value < 0 if key1 <  key2
//   0         if key1 == key2
//   value > 0 if key1 >  key2
//
int BSTCompareKeys(BSTKey key1, BSTKey key2)
{
  if (key1 < key2)
    return -1;
  else if (key1 == key2)
    return 0;
  else
    return 1;
}


//
// BSTInsert: inserts the given (key, value) pair into the binary search
// tree.  Returns true (non-zero) if the insert was successful, returns
// false (0) if the given key is already in the tree -- in which case the
// given (key, value) pair is not inserted.
//
int BSTInsert(BST *tree, BSTKey key, BSTValue value) {
  BSTNode * cur = tree->Root;
  BSTNode * prev = NULL;
  while(cur!=NULL) {
    if(cur->Key < key) {
      prev = cur;
      cur = cur->Right;
    } else if(cur->Key > key) {
      prev = cur;
      cur = cur->Left;
    } else {
      return 0;
    }
  }
  BSTNode * T = malloc(sizeof(BSTNode));
  T->Key = key;
  T->Value = value;
  T->Left = NULL;
  T->Right = NULL;
  if(prev == NULL) {
    tree->Root = T;
  } else if(prev->Key < key) {
    prev->Right = T;
  } else {
    prev->Left = T;
  }
  tree->Count++;
  return 1;
}


//
// PrintInorder: prints the nodes of the given binary search
// tree inorder to the console.
//
void _PrintInorder(BSTNode *root)
{
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    _PrintInorder(root->Left);
    printf(">>(%d, %f)\n", root->Key, root->Value);
    _PrintInorder(root->Right);
  }
}

void PrintInorder(BST *tree)
{
  printf(">>Inorder: %d node(s)\n", tree->Count);

  _PrintInorder(tree->Root);

  printf(">><<\n");
}

//
// PrintPreorder: prints the nodes of the given binary search
// tree pre-order to the console.
//
void _PrintPreorder(BSTNode *root)
{
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    printf(">>(%d, %f)\n", root->Key, root->Value);
    _PrintPreorder(root->Left);
    _PrintPreorder(root->Right);
  }
}

void PrintPreorder(BST *tree)
{
  printf(">>Preorder: %d node(s)\n", tree->Count);

  _PrintPreorder(tree->Root);

  printf(">><<\n");
}


// inputs and discards the remainder of the current line for the
// given input stream, including the EOL character(s):
void skipRestOfInput(FILE *stream)
{
  char restOfLine[256];
  int rolLength = sizeof(restOfLine) / sizeof(restOfLine[0]);

  fgets(restOfLine, rolLength, stream);
}


int main()
{
  int    key;
  double value;
  int    inserted = 0;
  int    failed = 0;

  BST *tree = BSTCreate();

  //
  // Input (key, value) pairs until we see 0 0, inserting
  // each pair into the binary search tree:
  //

  fscanf(stdin, "%d %lf", &key, &value);
  skipRestOfInput(stdin);

  while (key != 0)
  {
    if (BSTInsert(tree, key, value))
    {
      inserted++;
    }
    else
    {
      failed++;
    }

    fscanf(stdin, "%d %lf", &key, &value);
    skipRestOfInput(stdin);
  }

  //
  // now print tree inorder and pre-order to confirm:
  //
  printf("**Inserted: %d\n", inserted);
  printf("**Failed:   %d\n", failed);

  PrintInorder(tree);
  PrintPreorder(tree);

  return 0;
}
