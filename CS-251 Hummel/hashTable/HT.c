#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "HT.h"

HT* initTable(int N) {
  HT* ret = malloc(sizeof(HT));
  ret->arr = malloc(sizeof(void*) * N);
  int i;
  for(i=0;i<N;i++) {
    ret->arr[i] = NULL;
  }
  ret->size = N;
  return ret;
}

int hash(HTKey key) {
  return key;
}

void store(HT* table,HTKey key ,void* elem,void (*copyTo)(void** src, void* dest)) {
  int i = hash(key);
  copyTo(&table->arr[i],elem);
}

void* get(HT* table, HTKey key) {
  return table->arr[hash(key)];
}
