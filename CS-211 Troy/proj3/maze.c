#include <stdio.h>
#include <stdlib.h>

typedef struct mazeStruct
{
 char **arr;  /* allows for a maze of size 30x30 plus outer walls */
 int  **visited;
 int xsize, ysize;
 int xstart, ystart;
 int xend, yend;
} maze;

typedef struct LLValue {
  int x;
  int y;
} LLValue;

typedef struct _LinkedNode {
  LLValue value;
  struct _LinkedNode *next;
} LinkedNode;

typedef struct _LinkedList {
  LinkedNode *head;
  int count;
} LinkedList;

void push(LinkedList *list, LLValue value) {
  LinkedNode *temp = list->head;
  if(temp == NULL) {
    list->head = (LinkedNode*)malloc(sizeof(LinkedNode));
    list->head->value = value;
    list->head->next = NULL;
  } else {
    LinkedNode *insert = (LinkedNode*)malloc(sizeof(LinkedNode));
    insert->next = temp;
    insert->value = value;
    list->head = insert;
  }
}

void pop(LinkedList *list) {
  if(list->head == NULL) {
    return;
  }
  LinkedNode*temp = list->head;
  list->head = list->head->next;
  free(temp);
}

int isEmpty(LinkedList *list) {
  return list->head == NULL;
}

LLValue top(LinkedList *list) {
  if(!isEmpty(list))
    return list->head->value;
  return ;
}

void reset(LinkedList *list) {
  LinkedNode *cur = list->head;
  LinkedNode *prev = NULL;
  while(cur!=NULL) {
    prev = cur;
    cur = cur->next;
    free(prev);
  }
  if(list->head!=NULL) {
    free(list->head);
  }
}

LinkedList* initList() {
  LinkedList* l = (LinkedList*)malloc(sizeof(LinkedList));
  l->head = NULL;
  l->count = 0;
  return l;
}

void printBoard(maze m1) {
  int i,j;
  for (i = 0; i < m1.xsize+2; i++) {
    for (j = 0; j < m1.ysize+2; j++)
      printf ("%c", m1.arr[i][j]);
    printf("\n");
  }
}

int hasUnvisitedAndEmptyNeighbor(LLValue val, maze m1) {
  int x = val.x;
  int y = val.y;
  int emptyAtAll = m1.arr[y+1][x] != '*' || m1.arr[y-1][x] != '*' || m1.arr[y][x+1] != '*' || m1.arr[y][x-1] != '*';

  int leftU_E  = m1.arr[y-1][x] != '*' && !m1.visited[y-1][x];
  int rightU_E = m1.arr[y+1][x] != '*' && !m1.visited[y+1][x];
  int upU_E    = m1.arr[y][x-1] != '*' && !m1.visited[y][x-1];
  int downU_E  = m1.arr[y][x+1] != '*' && !m1.visited[y][x+1];

  return leftU_E || rightU_E || upU_E || downU_E;
}

LLValue getUnvisitedAndEmptyNeighbor(LLValue val, maze m1) {
  int x = val.x;
  int y = val.y;
  if(m1.arr[y+1][x] != '*' && m1.visited[y+1][x] == 0) {
    val.y = y+1;
    return val;
  } else if(m1.arr[y-1][x] != '*' && m1.visited[y-1][x] == 0) {
    val.y = y-1;
    return val;
  } else if(m1.arr[y][x+1] != '*' && m1.visited[y][x+1] == 0) {
    val.x = x+1;
    return val;
  } else if(m1.arr[y][x-1] != '*' && m1.visited[y][x-1] == 0) {
    val.x = x-1;
    return val;
  } else {
    val.x = -1;
    val.y = -1;
    return val;
  }
}


int main (int argc, char **argv)
{
  maze m1;

  int xpos, ypos;
  int i,j;

  FILE *src;

  LLValue val;
  LinkedList* myList = initList();

  /* verify the proper number of command line arguments were given */
  // if(argc != 2) {
  //    fprintf(stderr, "Usage: %s <input file name>\n", argv[0]);
  //    exit(-1);
  // }
  //
  // /* Try to open the input file. */
  // if ( ( src = fopen( argv[1], "r" )) == NULL )
  // {
  //   fprintf (stderr, "Can't open input file: %s", argv[1] );
  //   exit(-1);
  // }
  src = fopen("inputfile.txt","r");

  /* read in the size, starting and ending positions in the maze */
  if(fscanf (src, "%d %d", &m1.xsize, &m1.ysize) == EOF) {
    fprintf(stderr, "PLEASE SUPPLY MORE INPUT\n");
    exit(-1);
  }
  if(fscanf (src, "%d %d", &m1.xstart, &m1.ystart) == EOF) {
    fprintf(stderr, "PLEASE SUPPLY MORE INPUT\n");
    exit(-1);
  }
  if(fscanf (src, "%d %d", &m1.xend, &m1.yend) == EOF) {
    fprintf(stderr, "PLEASE SUPPLY MORE INPUT\n");
    exit(-1);
  }

  /* print them out to verify the input */
  printf ("size: %d, %d\n", m1.xsize, m1.ysize);
  printf ("start: %d, %d\n", m1.xstart, m1.ystart);
  printf ("end: %d, %d\n", m1.xend, m1.yend);

  m1.arr = malloc(sizeof(char*) * (m1.xsize+2));
  m1.visited = malloc(sizeof(int*) * (m1.xsize+2));

  for(i=0;i<m1.xsize+2;i++) {
    m1.arr[i] = malloc(sizeof(char) * (m1.ysize+2));
    m1.visited[i] = malloc(sizeof(int) * (m1.ysize+2));
  }


  /* initialize the maze to empty */
  for (i = 0; i < m1.xsize+2; i++) {
     for (j = 0; j < m1.ysize+2; j++) {
       m1.arr[i][j] = '.';
       m1.visited[i][j] = 0;
     }
   }

  /* mark the borders of the maze with *'s */
  for (i=0; i < m1.xsize+2; i++) {
     m1.arr[i][0] = '*';
     m1.arr[i][m1.ysize+1] = '*';
  }
  for (i=0; i < m1.ysize+2; i++) {
     m1.arr[0][i] = '*';
     m1.arr[m1.xsize+1][i] = '*';
   }

  /* mark the starting and ending positions in the maze */
  m1.arr[m1.xstart][m1.ystart] = 's';
  m1.arr[m1.xend][m1.yend] = 'e';

  /* mark the blocked positions in the maze with *'s */
  while (fscanf (src, "%d %d", &xpos, &ypos) != EOF) {
    if(xpos > m1.xsize || xpos <= 0 || ypos > m1.ysize || ypos <= 0) {
      fprintf(stderr, "INVALID COORDINATE: (%d,%d)\n",xpos,ypos);
    } else
      m1.arr[xpos][ypos] = '*';
  }

  /* print out the initial maze */
  printBoard(m1);

  int found = 0;
  int currX = m1.xstart,currY = m1.ystart;
  val.x = m1.xstart;
  val.y = m1.ystart;
  push(myList,val);

  m1.visited[currY][currX] = 1;
  while(!isEmpty(myList) && !found) {
    if(hasUnvisitedAndEmptyNeighbor(top(myList),m1)) {
      LLValue unVisit = getUnvisitedAndEmptyNeighbor(top(myList),m1);
      push(myList,unVisit);
      if(unVisit.x == m1.yend && unVisit.y == m1.xend) { // Found end
        break;
        return 1;
      }
      m1.visited[unVisit.y][unVisit.x] = 1;
    } else {
      pop(myList);
    }
  }
  LinkedList *backwardsList = initList();
  if(isEmpty(myList)) {
    printf("No Solution\n");
    return 0;
  } else {
    while(!isEmpty(myList)) {
      LLValue sol = top(myList);
      push(backwardsList,sol);
      pop(myList);
    }
  }
  printf("Solution:\n");
  while(!isEmpty(backwardsList)) {
    LLValue sol = top(backwardsList);
    pop(backwardsList);
    printf("(%d,%d)\n",sol.x,sol.y);
  }
  return 0;
}
