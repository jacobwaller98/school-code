/*bst.h*/

//
// Binary Search Tree ADT header file.
//
// Prof. Joe Hummel
// U. of Illinois, Chicago
// CS251, Spring 2017
//

// make sure this header file is #include exactly once:
#pragma once


//
// BST type declarations:
//
typedef char*  BSTKey;
typedef struct BSTValue
{
  long long val;
} BSTValue;

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
// BST API: function prototypes
//
BST *BSTCreate();
int  BSTCompareKeys(BSTKey key1, BSTKey key2);
BSTNode *BSTSearch(BST *tree, BSTKey key);
int  BSTSearchDepth(BST *tree, BSTKey key);
int  BSTInsert(BST *tree, BSTKey key, BSTValue value);

void BSTPrintInorder(BST *tree);
void BSTPrintPreorder(BST *tree, void(*pf)(BSTNode*));

int       BSTCount(BST *tree);
BSTValue* BSTSort(BST *tree);
void printNode(BSTNode* a);
BSTNode* _BSTSort(BSTNode* root);
BSTNode* inOrderSomethingNode(int *i, BSTNode *values, BSTNode* root);

int height(BST*tree);
BSTNode* preFind(BST* tree, BSTKey prefix);
