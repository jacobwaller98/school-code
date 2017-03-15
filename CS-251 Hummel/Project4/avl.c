/*avl.c*/

//
// AVL Tree ADT implementation file.
//
// Jacob Waller
// 673978936
// U. of Illinois, Chicago
// CS251, Spring 2017
//

// ignore stdlib warnings if working in Visual Studio:
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "avl.h"


/*
  Returns a pointer to an empty AVL
*/
AVL *AVLCreate() {
  AVL *tree;
  tree = (AVL*)malloc(sizeof(AVL));
  tree->root = NULL;
  tree->count = 0;
  return tree;
}


/*
  Frees an individual node from memory
*/
void _AVLFree(AVLNode* root) {
  if(root == NULL) {
    return;
  }
  _AVLFree(root->Left);
  _AVLFree(root->Right);
  free(root);
}


/*
  Frees entire tree from memory
*/
void AVLFree(AVL *tree) {
  AVLNode* root = tree->root;
  _AVLFree(root);
  free(tree);
}


/*
  Returns height of given sub-tree
*/
int _height(AVLNode *cur) {
  if (cur == NULL)
    return -1;
  return cur->height;
}


/*
  Returns max of x and y
*/
int _max2(int x, int y) {
  return (x > y) ? x : y;
}


/*
  Rotates right and returns pointer to the new root of sub-tree
*/
AVLNode *rightRotate(AVLNode *k2) {
  AVLNode *k1 = k2->Left;
  AVLNode *Y = k1->Right;

  k1->Right = k2;
  k2->Left = Y;

  k2->height = 1 + _max2(_height(k2->Left), _height(k2->Right));
  k1->height = 1 + _max2(_height(k1->Left), _height(k1->Right));

  return k1;
}


/*
  Rotates left and returns pointer to the new root of sub-tree
*/
AVLNode *leftRotate(AVLNode *k1) {
  AVLNode *k2 = k1->Right;
  AVLNode *Y = k2->Left;

  k2->Left = k1;
  k1->Right = Y;

  k1->height = 1 + _max2(_height(k1->Left), _height(k1->Right));
  k2->height = 1 + _max2(_height(k2->Left), _height(k2->Right));

  return k2;
}


/*
  Inserts the given (key, value) into the AVL tree, rebalancing
  the tree as necessary.  Returns true (non-zero) if successful,
  false (0) if not --- insert fails if the key is already in the
  tree (no changes are made to the tree in this case).
*/
int AVLInsert(AVL *tree, AVLKey key, AVLValue value)
{
  AVLNode *prev = NULL;
  AVLNode *cur = tree->root;

  AVLNode *stack[64];
  int      top = -1;

  while (cur != NULL) {
    top++;
    stack[top] = cur;
    if (AVLCompareKeys(key, cur->key) == 0)
      return 0;
    else if (AVLCompareKeys(key, cur->key) < 0) {
      prev = cur;
      cur = cur->Left;
    } else {
      prev = cur;
      cur = cur->Right;
    }
  }

  //Make new node with correct values
  AVLNode *newNode = (AVLNode *)malloc(sizeof(AVLNode));
  newNode->key = key;
  newNode->value = value;
  newNode->Left = NULL;
  newNode->Right = NULL;
  newNode->height = 0;

  //Put node where it belongs
  if (prev == NULL) {
    tree->root = newNode;
  } else if (AVLCompareKeys(key, prev->key) < 0) {
    prev->Left = newNode;
  } else {
    prev->Right = newNode;
  }
  tree->count++;

  /*
    Walk back up tree and see if we need to fix it
  */
  AVLNode *N;
  int rebalance = 0;

  while (top >= 0) {
    N = stack[top];  // N = pop();
    top--;

    int hl = _height(N->Left);
    int hr = _height(N->Right);
    int newH = 1 + _max2(hl, hr);

    if (newH == N->height) {
      rebalance = 0;
      break;
    } else if (abs(hl - hr) > 1) {
      rebalance = 1;
      break;
    } else {
      N->height = newH;
    }
  }

  // Okay, does the tree need to be rebalanced?
  if (rebalance)
  {
    cur = N;

    if (top < 0)     // stack is empty, ==> N is root
      prev = NULL;   // flag this with prev == NULL
    else  // stack not empty, so obtain ptr to cur's parent:
      prev = stack[top];

    //Determine case and rotate
    if (AVLCompareKeys(newNode->key, cur->key) < 0) {
      AVLNode *L = cur->Left;
      if (AVLCompareKeys(newNode->key, L->key) > 0) {
        cur->Left = leftRotate(L);
      }

      if (prev == NULL)
        tree->root = rightRotate(cur);
      else if (prev->Left == cur)
        prev->Left = rightRotate(cur);
      else
        prev->Right = rightRotate(cur);
    } else {
      AVLNode *R = cur->Right;

      if (AVLCompareKeys(newNode->key, R->key) < 0) {
        cur->Right = rightRotate(R);
      }

      if (prev == NULL)
        tree->root = leftRotate(cur);
      else if (prev->Left == cur)
        prev->Left = leftRotate(cur);
      else
        prev->Right = leftRotate(cur);
    }
  }
  return 1;  // Ayyeee lmao
}


/*
  returns greater than 0 if key1>key2
  returns 0 if key1==key2
  returns less than 0 if key1<key2
*/
int AVLCompareKeys(AVLKey key1, AVLKey key2)
{
  if (key1 < key2)
    return -1;
  else if (key1 == key2)
    return 0;
  else
    return 1;
}


/*
  Returns number of nodes in tree
*/
int AVLCount(AVL *tree)
{
  return tree->count;
}


/*
  Returns the node with the given key, if it exists
  otherwise, returns NULL
*/
AVLNode *AVLSearch(AVL *tree, AVLKey key) {
  AVLNode* cur = tree->root;
  while(cur!=NULL) {
    if(AVLCompareKeys(cur->key,key) > 0) {
      cur = cur->Left;
    } else if(AVLCompareKeys(cur->key,key) < 0) {
      cur = cur->Right;
    } else {
      return cur;
    }
  }
  return NULL;
}


/*
  Returns the height of the tree
*/
int AVLHeight(AVL *tree)
{
  if (tree->root == NULL)
    return -1;
  else
    return tree->root->height;
}
