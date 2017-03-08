/*bst.c*/

//
// Binary Search Tree ADT implementation file.
//
// Prof. Joe Hummel
// U. of Illinois, Chicago
// CS251, Spring 2017
//

// ignore stdlib warnings if working in Visual Studio:
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "bst.h"


//
// BSTCreate: dynamically creates and returns an empty
// binary search tree:
//
BST *BSTCreate()
{
  BST *tree;

  tree = (BST *)malloc(sizeof(BST));
  tree->Root = NULL;
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
  if (strcmp(key1, key2) < 0)
    return -1;
  else if (strcmp(key1, key2) == 0)
    return 0;
  else
    return 1;
}


//
// BSTSearch: searches the binary search tree for a node containing the
// same key.  If a match is found, a pointer to the node is returned,
// otherwise NULL is returned.
//
BSTNode *BSTSearch(BST *tree, BSTKey key)
{
  BSTNode *cur = tree->Root;

  //
  // search the tree to see if it contains a matching key:
  //
  while (cur != NULL)
  {
    //printf("DEBUGGING: %s %s\n",cur->Key,key);
    if (BSTCompareKeys(key, cur->Key) == 0)  // found!
      return cur;
    else if (BSTCompareKeys(key, cur->Key) < 0)  // smaller, go left:
    {
      cur = cur->Left;
    }
    else  // larger, go right:
    {
      cur = cur->Right;
    }
  }

  // if we get here, we fell out of the tree, and didn't find it:
  return NULL;
}

int minLength(BSTKey a, BSTKey b) {
  return (strlen(a)<strlen(b))?strlen(a):strlen(b);
}

int BSTComparePre(BSTKey a, BSTKey b) {
  if (strncmp(a, b,minLength(a,b)) < 0)
    return -1;
  else if (strncmp(a, b, minLength(a,b)) == 0)
    return 0;
  else
    return 1;
}

BSTNode* preFind(BST* tree, BSTKey prefix) {
  BSTNode *cur = tree->Root;

  //
  // search the tree to see if it contains a matching key:
  //
  while (cur != NULL)
  {
    if (BSTComparePre(prefix, cur->Key) == 0) {  // found!
      return cur;
    }
    else if (BSTComparePre(prefix, cur->Key) < 0)  // smaller, go left:
    {
      cur = cur->Left;
    }
    else  // larger, go right:
    {
      cur = cur->Right;
    }
  }
  // if we get here, we fell out of the tree, and didn't find it:
  return NULL;
}


//
// BSTSearchDepth: searches the binary search tree for a node containing the
// same key, returning the depth of that node if found.  The root node is
// considered depth 0, the next level down is a depth of 1, and so forth.
// If a matching key is not found, the function returns a depth of -1.
//
int BSTSearchDepth(BST *tree, BSTKey key)
{
  BSTNode *cur = tree->Root;
  int depth = 0;

  //
  // search the tree to see if it contains a matching key:
  //
  while (cur != NULL)
  {
    if (BSTCompareKeys(key, cur->Key) == 0)  // found!
      return depth;
    else if (BSTCompareKeys(key, cur->Key) < 0)  // smaller, go left:
    {
      cur = cur->Left;
    }
    else  // larger, go right:
    {
      cur = cur->Right;
    }

    depth++;
  }

  // if we get here, we fell out of the tree, and didn't find it:
  return -1;
}


//
// BSTInsert: inserts the given (key, value) pair into the binary search
// tree.  Returns true (non-zero) if the insert was successful, returns
// false (0) if the given key is already in the tree -- in which case the
// given (key, value) pair is not inserted.
//
int BSTInsert(BST *tree, BSTKey key, BSTValue value)
{
  BSTNode *prev = NULL;
  BSTNode *cur = tree->Root;

  //
  // first we search the tree to see if it already contains key:
  //
  while (cur != NULL)
  {
    if (BSTCompareKeys(key, cur->Key) == 0)  // already in tree, failed:
      return 0;
    else if (BSTCompareKeys(key, cur->Key) < 0)  // smaller, go left:
    {
      prev = cur;
      cur = cur->Left;
    }
    else  // larger, go right:
    {
      prev = cur;
      cur = cur->Right;
    }
  }

  //
  // If we get here, tree does not contain key, so insert new node
  // where we fell out of tree:
  //
  BSTNode *T = (BSTNode *)malloc(sizeof(BSTNode));
  T->Key = key;
  T->Value = value;
  T->Left = NULL;
  T->Right = NULL;

  //
  // link T where we fell out of tree -- after prev:
  //
  if (prev == NULL)  // tree is empty, insert @ root:
  {
    tree->Root = T;
  }
  else if (BSTCompareKeys(key, prev->Key) < 0)  // smaller, insert to left:
  {
    prev->Left = T;
  }
  else  // larger, insert to right:
  {
    prev->Right = T;
  }

  tree->Count++;

  return 1;  // success:
}


//
// BSTPrintInorder: prints the nodes of the given binary search
// tree inorder to the console.
//
// NOTE: "pf" is a print function that must be declared like this
// (though the name "pf" doesn't really matter):
//
//  void pf(BSTNode *cur)
//  { ...}
//
// When you call, pass pf and then you'll be able to control
// what gets printed when a node is "visited".
//
void _BSTPrintInorder(BSTNode *root)
{
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    _BSTPrintInorder(root->Left);
    //pf(root);
    printf("%s %lld\n",root->Key,root->Value.val);
    _BSTPrintInorder(root->Right);
  }
}

void BSTPrintInorder(BST *tree)
{
  printf(">>Inorder: %d node(s)\n", tree->Count);

  _BSTPrintInorder(tree->Root);

  printf(">><<\n");
}


//
// BSTPrintPreorder: prints the nodes of the given binary search
// tree pre-order to the console.
//
// NOTE: "pf" is a print function that must be declared like this
// (though the name "pf" doesn't really matter):
//
//  void pf(BSTNode *cur)
//  { ...}
//
// When you call, pass pf and then you'll be able to control
// what gets printed when a node is "visited".
//
void _BSTPrintPreorder(BSTNode *root, void(*pf)(BSTNode*))
{
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    pf(root);
    _BSTPrintPreorder(root->Left, pf);
    _BSTPrintPreorder(root->Right, pf);
  }
}

void BSTPrintPreorder(BST *tree, void(*pf)(BSTNode*))
{
  printf(">>Preorder: %d node(s)\n", tree->Count);

  _BSTPrintPreorder(tree->Root, pf);

  printf(">><<\n");
}


//
// BSTCount: returns # of nodes in the tree.
//
int BSTCount(BST *tree)
{
  return tree->Count;
}

void inOrderSomething(int *i, BSTValue*values, BSTNode*root) {
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    inOrderSomething(i,values,root->Left);
    values[*i] = root->Value;
    *i=*i+1;
    inOrderSomething(i,values,root->Right);

  }
}

BSTNode* inOrderSomethingNode(int *i, BSTNode *values, BSTNode* root) {
  if (root == NULL)  // base case: empty tree
    return;
  else  // recursive case: non-empty tree
  {
    inOrderSomethingNode(i,values,root->Left);
    values[*i] = *root;
    *i=*i+1;
    inOrderSomethingNode(i,values,root->Right);

  }
}

//
// BSTSort: traverses the tree inorder so as to yield the (key, value)
// pairs in sorted order by key.  Returns a dynamically-allocated array
// of size N, containing a copy of the value in each tree node.  The
// value N can be obtained by calling BSTCount().
//
// NOTE: the caller is responsible for freeing the returning array.
//
BSTValue* BSTSort(BST* tree)
{
  BSTValue *values = (BSTValue *)malloc(BSTCount(tree) * sizeof(BSTValue));
  int i = 0;
  inOrderSomething(&i,values,tree->Root);
  return values;
}

int BSTCountNodes(BSTNode* root) {
  if(root==NULL) {
    return 0;
  }
  return 1+BSTCountNodes(root->Left)+BSTCountNodes(root->Right);
}

BSTNode* _BSTSort(BSTNode* root) {
  BSTNode *values = (BSTNode *)malloc(BSTCountNodes(root) * sizeof(BSTNode));
  int i = 0;
  inOrderSomethingNode(&i,values,root);
  return values;
}

int max(int a, int b) {
  return (a>b)?a:b;
}

int _height(BSTNode*root) {
  if(root == NULL) {
      return -1;
   }
   return 1+max(_height(root->Left),_height(root->Right));
}

int height(BST*tree) {
  return _height(tree->Root);
}
