typedef int HTKey;
typedef struct HT {
  void** arr;
  int size;
} HT;



HT* initTable(int N);
int hash(HTKey key);
void store(HT* table, HTKey key, void* elem,void (*copyTo)(void** src, void* dest));
void* get(HT* table, HTKey key);
