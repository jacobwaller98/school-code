/*main.c*/

//
// Searching for movies in a binary search tree.
//
// << NAME >>
// U. of Illinois, Chicago
// CS251, Spring 2017
// Project #02
//

// ignore stdlib warnings if working in Visual Studio:
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

typedef int BSTKey;
typedef char* BSTValue;

typedef struct Movie_ {
	char title[256];
	int id;
	int yearReleased;
} Movie;

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

void parseMovieData(char *line, int lineLen, int *id, BSTValue title, int *year) {
	line[strcspn(line, "\r\n")] = '\0';
	const char s[2] = ",";
	char* substr;
	substr = (char*)malloc(sizeof(char)*(strlen(line) + 1));
	substr = strtok(line, s);
	int count = 0;
	while (substr != NULL) {
		if (count == 0) {
			*id = atoi(substr);
		}
		else if (count == 1) {
			strcpy(title, substr);
		}
		else {
			*year = atoi(substr);
		}
		count++;
		substr = strtok(NULL, s);
	}
}


//
// getFileName: inputs a filename from the keyboard, make sure the file can be
// opened, and returns the filename if so.  If the file cannot be opened, an
// error message is output and the program is exited.
//
char *getFileName()
{
	char filename[512];
	int  fnsize = sizeof(filename) / sizeof(filename[0]);

	// input filename from the keyboard:
	fgets(filename, fnsize, stdin);
	filename[strcspn(filename, "\r\n")] = '\0';  // strip EOL char(s):

												 // make sure filename exists and can be opened:
	FILE *infile = fopen(filename, "r");
	if (infile == NULL)
	{
		printf("**Error: unable to open '%s'\n\n", filename);
		exit(-1);
	}

	fclose(infile);

	// duplicate and return filename:
	char *s = (char *)malloc((strlen(filename) + 1) * sizeof(char));
	strcpy(s, filename);

	return s;
}

int BSTCompareKeys(BSTKey key1, BSTKey key2)
{
	if (key1 < key2)
		return -1;
	else if (key1 == key2)
		return 0;
	else
		return 1;
}

BSTNode *BSTSearch(BST *tree, BSTKey key)
{
	BSTNode * temp = tree->Root;
	while (temp != NULL) {
    printf(">>Visiting %d: '%s'\n",temp->Key,temp->Value);
		if (BSTCompareKeys(temp->Key, key) == 0) {
      printf("Movie %d: '%s'\n", key,temp->Value);
      return temp;
		}
		else if (BSTCompareKeys(temp->Key, key) < 0) {
			temp = temp->Right;
		}
		else {
			temp = temp->Left;
		}
	}
  printf("Movie %d: not found\n", key);
	return NULL;
}

int BSTInsert(BST *tree, BSTKey key, BSTValue value)
{
	BSTNode * cur = tree->Root;
	BSTNode * prev = NULL;
	while (cur != NULL) {
		if (BSTCompareKeys(cur->Key, key) < 0) {
			prev = cur;
			cur = cur->Right;
		}
		else if (BSTCompareKeys(cur->Key, key) > 0) {
			prev = cur;
			cur = cur->Left;
		}
		else {
			return 0;
		}
	}
	BSTNode * T = (BSTNode*)malloc(sizeof(BSTNode));
	T->Key = key;
	T->Value = (BSTValue)malloc(sizeof(char) * 256);
	strcpy(T->Value, value);
	//T->Value = value;
	T->Left = NULL;
	T->Right = NULL;
	if (prev == NULL) {
		tree->Root = T;
	}
	else if (BSTCompareKeys(prev->Key, key) < 0) {
		prev->Right = T;
	}
	else {
		prev->Left = T;
	}
	tree->Count++;
	return 1;
}

BST* BSTCreate() {
	BST *tree;

	tree = (BST *)malloc(sizeof(BST));
	tree->Root = NULL;
	tree->Count = 0;

	return tree;
}

void _inOrder(BSTNode *root) {
	if (root == NULL) {
		return;
	}
	_inOrder(root->Left);
	printf("%d:%s\n", root->Key, root->Value);
	_inOrder(root->Right);
}

void inOrder(BST *tree) {
	_inOrder(tree->Root);
}


int main()
{
	// get movies filename from the user/stdin:
	const int max_title_size = 128;
	char *MoviesFileName = getFileName();
	FILE*movieFile = fopen(MoviesFileName, "r");
	char line[256];
	int id = 0;
	BSTValue name = (BSTValue)malloc(sizeof(char)*max_title_size);
	int yearReleased;

	//Create our BST
	BST* tree = BSTCreate();

	//Don't need this line of text, so get rid of it
	fgets(line, 256, movieFile);
	fgets(line, 256, movieFile);
	while (!feof(movieFile)) {
		parseMovieData(line, 256, &id, name, &yearReleased);
		BSTInsert(tree, id, name);
		fgets(line, 256, movieFile);
	}
  int testId = 0;
  scanf("%d",&testId);
  BSTSearch(tree,testId);



	return 0;
}
