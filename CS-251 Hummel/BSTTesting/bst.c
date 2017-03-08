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
int BSTInsert(BST *tree, BSTKey key, BSTValue value) {
  BSTNode *prev = NULL;
  BSTNode *cur = tree->Root;

  //
  // first we search the tree to see if it already contains key:
  //
  while (cur != NULL) {
    if (BSTCompareKeys(key, cur->Key) == 0)  // already in tree, failed:
      return 0;
    else if (BSTCompareKeys(key, cur->Key) < 0) {  // smaller, go left:
      prev = cur;
      cur = cur->Left;
    }
    else {  // larger, go right:

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

void setNode(BSTNode*node, BSTKey key, BSTValue value) {
  node->Value = value;
  node->Key = key;
  node->Right = NULL;
  node->Left = NULL;
  node->Height = -1;
}

int getHeight(BSTNode* root) {
  if(root==NULL)
    return -1;
  return root->Height;
}

int max(int a, int b) {
  return (a>b)?a:b;
}

BSTNode* rotateRight(BSTNode* k2) {
  BSTNode* k1 = k2->Left;
  BSTNode* y = k1->Right;
  k1->Right = k2;
  k2->Left = y;
  k2->Height = 1 + max(getHeight(k2->Left),getHeight(k2->Right));
  k1->Height = 1 + max(getHeight(k1->Left),getHeight(k1->Right));
  return k1;
}

BSTNode* rotateLeft(BSTNode* k1) {
  BSTNode* k2 = k1->Right;
  BSTNode* y = k2->Left;
  k1->Right = y;
  k2->Left = k1;
  k1->Height = 1 + max(getHeight(k1->Left),getHeight(k1->Right));
  k2->Height = 1 + max(getHeight(k2->Left),getHeight(k2->Right));
  return k2;
}

int _AVLInsert(BSTNode** root, BSTKey key, BSTValue value) {
  if(*root==NULL) {
    return -1;
  }
  int result = BSTCompareKeys(key,(*root)->Key);
  if(result==0) {
    return 0;
  } else if(result<0) {
    if(_AVLInsert(&((*root)->Left),key,value) == -1) {
      (*root)->Left = malloc(sizeof(BSTNode));
      setNode((*root)->Left,key,value);
    }
  } else {
    if(_AVLInsert(&((*root)->Right),key,value) == -1) {
      (*root)->Right = malloc(sizeof(BSTNode));
      setNode((*root)->Right,key,value);
    }
  }
  ((*root)->Height)++;

  if(abs(getHeight((*root)->Left) - getHeight((*root)->Right)) > 1) {
    if(getHeight((*root)->Left) > getHeight((*root)->Right)) {
      //printf("BALANCING Right\n");
      *root = rotateRight(*root);
    } else {
      //printf("BALANCING left\n");
      *root = rotateLeft(*root);
    }
  }
  return 1;
}

int AVLInsert(BST *tree, BSTKey key, BSTValue value) {
  int result = _AVLInsert(&(tree->Root),key,value); //Determines if the key and value was inserted
  if(result == -1) { //If there's nothing in the tree we have to do some special stuff
    tree->Root = malloc(sizeof(BSTNode));
    setNode(tree->Root,key,value);
    tree->Root->Height = 0;
    tree->Count = 1;
    return 1;
  }
  if(result) {
    tree->Count++;
  }
  return result;
}

/*
gma4 90
aolson7 101
lshu3 88
zwen5 0
rahsan3 99
aapel31 60
bvajhi2 99
bbear9 71
jhummel2 100
#
*/


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
void _BSTPrintInorder(BSTNode *root, void (*pf)(BSTNode*))
{
if (root == NULL)  // base case: empty tree
  return;
else  // recursive case: non-empty tree
{
  _BSTPrintInorder(root->Left, pf);
  pf(root);
  _BSTPrintInorder(root->Right, pf);
}
}

void BSTPrintInorder(BST *tree, void(*pf)(BSTNode*))
{
printf(">>Inorder: %d node(s)\n", tree->Count);

_BSTPrintInorder(tree->Root, pf);

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


//
// BSTHeight: returns the height of the tree.
//
int _max2(int x, int y)
{
return (x > y) ? x : y;
}

int _BSTHeight(BSTNode *root)
{
if (root == NULL)
  return -1;
else
  return 1 + _max2(_BSTHeight(root->Left), _BSTHeight(root->Right));
}

int BSTHeight(BST *tree)
{
return _BSTHeight(tree->Root);
}


//
// BSTDelete: deletes the node from the tree containing the matching key,
// returning the value stored in this node.  The behavior of this function
// is undefined if the tree does not contain a matching key, so it may
// return random values, or even crash.
//
BSTValue BSTDelete(BST *tree, BSTKey key)
{
BSTValue result;

result.X = NULL;
result.Y = -1;

//
// TODO:
//

return result;
}
