#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <ctype.h>

#include "bst.h"

void processFileLine(char line[], long long *weight, char title[]) {
  int i,n=0,m;
  for(i=0;i<strlen(line);i++) {
    if(isdigit(line[i]) && !n) {
      n=1;
      m=i;
    }
    if(n==1 && !isdigit(line[i])) {
      line[i]='\0';
      line++;
      break;
    }
  }
  *weight = atoll(&line[--m]);
  while(line[i] == ' ' || line[i] == '\t') i++;
  strcpy(title,&line[i]);
  //printf("%lld %s\n",*weight,title);
}

char* removeLeadTrailSpace(char line[]) {
  int i=0,m;
  while(isspace(line[i]) || line[i] == '\n' || line[i] == '\r') i++;
  m=strlen(line);
  while(isspace(line[m]) || line[m] == '\n' || line[i] == '\r') {
    line[m] = '\0';
    m--;
  }

  line[m] = '\0';
  return &line[i];
}

char* trimwhitespace(char* str)
{
  char *end;

  // Trim leading space
  while(isspace((unsigned char)*str)) str++;

  if(*str == 0)  // All spaces?
    return str;

  // Trim trailing space
  end = str + strlen(str) - 1;
  while(end > str && isspace((unsigned char)*end)) end--;

  // Write new null terminator
  *(end+1) = 0;

  return str;
}

void stringCopy(char* dest, char* src) {
   int i=0;
   for(i=0;i<strlen(src);i++) {
      dest[i] = src[i];
   }
   dest[i] = '\0';
}

void findFromInput(BST* tree) {
  char* title = malloc(sizeof(char)*256);
  fgets(title,255,stdin);
  //printf("FROM MAIN: %s\n",title);
  title[strcspn(title, "\r\n")] = '\0';
  //printf("FROM MAIN: %s\n",title);
  stringCopy(title,trimwhitespace(title));
  //printf("AFTER CPY FROM MAIN: %s\n",title);
  //printf("title: %s\n",title);
  BSTNode*temp = BSTSearch(tree,title);
  if(temp==NULL) {
    printf("**Not found...\n");
  } else {
    printf("%s: %lld\n",title,temp->Value.val);
  }
}

void addFromInput(BST* tree) {
  long long weight;
  BSTValue val;
  char* title = malloc(sizeof(char) * 512);
  scanf("%lld",&weight);
  fgets(title,511,stdin);
  title[strcspn(title, "\r\n")] = '\0';
  char* t = removeLeadTrailSpace(title);
  val.val = weight;
  if(BSTInsert(tree,t,val)) {
    printf("**Added\n");
  } else {
    printf("**Not added...\n");
  }
}

FILE* getInputFile() {
  char fileName[256];
  scanf("%s",fileName);
  return fopen(fileName,"r");
}

void fillTree(BST*tree,FILE*inputFile) {
  char line[512];
  char title[512];
  char* key;
  BSTValue val;
  long long weight;

  fgets(line,511,inputFile);
  while(!feof(inputFile)) {
    line[strcspn(line, "\r\n")] = '\0';
    processFileLine(line,&weight,title);
    key = malloc(sizeof(char)*256);
    strcpy(key,title);
    val.val = weight;
    BSTInsert(tree,key,val);
    fgets(line,511,inputFile);
  }
}

BSTNode* saveAllWithPrefix(BSTNode* values, char* pref, int *len) {
  int i=0,m=0,n=0;
  for(i=0;i<*len;i++) {
    if(strncmp(values[i].Key,pref,strlen(pref)) == 0) {
      m++;
    }
  }
  BSTNode* ret =(BSTNode*)malloc(sizeof(BSTNode)*m);
  for(i=0;i<*len;i++) {
    if(strncmp(values[i].Key,pref,strlen(pref)) == 0) {
      ret[n] = values[i];
      n++;
    }
  }
  *len = --n;
  return ret;
}

void sortNodes(BSTNode* vals, int len) {
  int x,y;
  for(x=0;x<len;x++) {
    for(y=0;y<len-1;y++) {
      if(vals[y].Value.val < vals[y+1].Value.val) {
        BSTNode temp = vals[y];
        vals[y] = vals[y+1];
        vals[y+1] = temp;
      } else if(vals[y].Value.val == vals[y+1].Value.val) {
        if(strcmp(vals[y].Key,vals[y+1].Key) < 0) {
          BSTNode temp = vals[y];
          vals[y] = vals[y+1];
          vals[y+1] = temp;
        }
      }
    }
  }
}

void suggestFromInput(BST* tree) {

  /*
  ** [Sub-tree root:  (Chittagong, Bangladesh,3920222)]
  ** [Sub-tree count: 68]
  ** [Num matches:    48]
  Chittagong, Bangladesh: 3920222
  */
  int k;
  char title[512];
  scanf("%d\n", &k);
  fgets(title,511,stdin);
  title[strcspn(title, "\r\n")] = '\0';
  char* t = removeLeadTrailSpace(title);
  BSTNode* subtree = preFind(tree,t);
  printf("** [Sub-tree root:  (%s,%lld)]\n",subtree->Key,subtree->Value.val);
  printf("** [Sub-tree count: %d]\n",BSTCountNodes(subtree));
  BSTNode* values = _BSTSort(subtree);
  int len = BSTCountNodes(subtree);
  BSTNode* l = saveAllWithPrefix(values,t,&len);
  printf("** [Num matches:    %d]\n",len+1);
  sortNodes(l,len);
  int f;
  for(f=0;f<len && f<k; f++) {
    printf("%s: %lld\n",l[f].Key,l[f].Value.val);
  }
}

int main(int argc, char const *argv[]) {
printf("** Starting Autocomplete **\n");

  /*
    Init variables
  */
  BST *tree = BSTCreate();
  char line[512];



  FILE* inputFile = getInputFile();

  fillTree(tree,inputFile);

  printf("** Ready **\n");

  //BSTPrintInorder(tree);

  /*
    Start taking commands
  */
  scanf("%s", line);
  while(strcmp(line,"exit")!=0) {
    if(strcmp(line,"stats")==0) {
      printf("**Tree count:  %d\n",BSTCount(tree));
      printf("**Tree Height: %d\n",height(tree));
    } else if(strcmp(line,"find")==0) {
      findFromInput(tree);
    } else if(strcmp(line,"add")==0) {
      addFromInput(tree);
    } else if(strcmp(line,"suggest")==0) {
      suggestFromInput(tree);
    } else {
      printf("UNKNOWN\n");
    }

    scanf("%s",line);
  }
  printf("** Done **\n");



  return 0;
}
