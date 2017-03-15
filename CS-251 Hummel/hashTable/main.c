#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "HT.h"

void copyTo(void** dest, void* src) {
  *dest = malloc(strlen(src)+1);
  strcpy(*dest,src);
}

int main(int argc, char const *argv[]) {
  //
  HT* table = initTable(500);
  int num;
  char* str = malloc(sizeof(char)*64);
  scanf("%d %s",&num, str);
  while(num!=-1) {
    store(table,num,str,copyTo);
    scanf("%d",&num);
    if(num==-1) {
      break;
    }
    scanf("%s",str);
  }

  scanf("%d",&num);
  while(num!=-1) {
    printf("%s\n",get(table,num));
    scanf("%d",&num);
  }

  return 0;
}
