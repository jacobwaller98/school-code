#include <stdio.h>
#include <stdlib.h>
#include <string.h>

 typedef struct LinkedNode {
   struct LinkedNode* next;
   char name[31];
   int groupSize;
   int isInResturant;
 } Node;

 typedef struct LinkedList {
   int count;
   Node* head;
 } LinkedList;

 int DEBUG_MODE = 0;


/*
  Initializes the list
*/
LinkedList* initList() {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Initializing list\n");
  }
  LinkedList* ret = malloc(sizeof(LinkedList));
  ret->head = NULL;
  ret->count = 0;
}


int isInList(LinkedList*queue, char name[]) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Checking for %s in the list\n",name);
  }
  Node*temp = queue->head;
  while(temp!=NULL) {
    if(strcmp(temp->name,name)==0) {
      return 1;
    }
    temp=temp->next;
  }
  return 0;
}


/*
  Adds a node to the end of the list
*/
void enqueue(LinkedList* queue, char name[], int groupSize, int isInResturant) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Adding %s to the list\n",name);
  }
  if(isInList(queue,name)) {
    printf("%s is already in list. Try again!\n",name);
    return;
  }
  Node *temp = queue->head;
  if(temp == NULL) {
    queue->head = malloc(sizeof(Node));
    //queue->head->name = malloc(sizeof(char)*31);
    strcpy(queue->head->name,name);
    queue->head->groupSize = groupSize;
    queue->head->isInResturant = isInResturant;
    queue->head->next = NULL;
  } else {
    while(temp->next != NULL)  {
      printf("Oi\n");
      temp = temp->next;
    }
    temp->next = malloc(sizeof(Node));
    strcpy(temp->next->name, name);
    temp->next->groupSize = groupSize;
    temp->next->isInResturant = isInResturant;
    temp->next->next = NULL;
  }
  queue->count = queue->count+1;
}

Node* removeFirstWithN(LinkedList* list, int n) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Removing first node with %d or \n");
  }
  Node* temp = list->head;
  Node* prev = NULL;
  while(temp!=NULL) {
    if(temp->groupSize <= n && temp->isInResturant) {
      break;
    }
    prev = temp;
    temp = temp->next;
  }
  if(temp!=NULL) {
    list->count = list->count-1;
  }
  if(prev!=NULL)
    prev->next = temp->next;
  if(prev == NULL && temp!=NULL) {
    list->head = temp->next;
  }
  return temp;
}

int markArrived(LinkedList* list, char name[]) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Marking %s as arrived\n",name);
  }
  Node* temp = list->head;
  while(temp!=NULL) {
    if(strcmp(temp->name,name) == 0) {
      temp->isInResturant = 1;
      return 1;
    }
    temp = temp->next;
  }
  return 0;
}

void  printList(LinkedList* list) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Printing list\n");
  }
  Node*temp = list->head;
  while(temp!=NULL) {
    printf("Name: %s Group Size: %d", temp->name, temp->groupSize);
    if(temp->isInResturant) {
      printf(" is waiting in resturant\n");
    } else {
      printf(" has not yet arrived\n");
    }
    temp=temp->next;
  }

}

void printAheadOf(LinkedList*queue, char name[]) {
  if(DEBUG_MODE) {
    printf("DEBUGGING: Printing all nodes ahead of %s\n",name);
  }
  if(!isInList(queue,name)) {
    printf("ERROR: %s is not in list\n", name);
    return;
  }
  Node*temp = queue->head;
  int cnt = 0;
  while(strcmp(temp->name,name) != 0) {
    cnt++;
    printf("%s, party of %d\n",temp->name,temp->groupSize);
    temp = temp->next;
  }
  printf("There are %d groups ahead of %s\n",cnt,name);
}



int main(int argc, char const *argv[]) {
  if(argc != 2) {
    int i=0;
    for(;i<argc;i++) {
      if(strcmp(argv[i],"-d") == 0) {
        DEBUG_MODE = 1;
      }
    }
  }
  LinkedList* queue = initList();
  char command;
  int size;
  char name[31];
  if(DEBUG_MODE) {
    printf("DEBUG_MODE is ACTIVE\n");
  }
  printf(">");
  scanf(" %c",&command);
  while(command!='q') {
    if(DEBUG_MODE) {
      printf("COMMAND IS: %c\n",command);
    }
    if(command=='?') { //Basically help
      printf("   HELP\n");
      printf("**********\n");
      printf("?: Display this help screen\n");
      printf("a <size> <name>: Add someone who is waiting in the resturant\n");
      printf("c <size> <name>: Add someone who is not in the resturant\n");
      printf("w <name>: Mark the group with the given name as arrived\n");
      printf("r <table-size>: Print the next group that will fit at the table and remove them from list\n");
      printf("l <name>: list all groups in front of the given group name\n");
      printf("d: Print all waiting parties\n");
    } else if(command=='a') {
      scanf("%d %s", &size, name);
      printf("Adding %s, party of %d\n",name,size);
      enqueue(queue,name,size,1);
    } else if(command=='c') {
      scanf("%d %s", &size, name);
      enqueue(queue,name,size,0);
    } else if(command=='w') {
      scanf("%s",name);
      if(!markArrived(queue,name)) {
        printf("ERROR: %s does not exist, try again!\n",name);
      }
    } else if(command=='r') {
      scanf("%d",&size);
      Node* t = removeFirstWithN(queue,size);
      if(t!=NULL) {
        printf("Call: %s: Party of %d\n",t->name,t->groupSize);
        free(t);
      } else {
        printf("No one in resturant for this table.\n");
      }
    } else if(command=='l') {
      scanf("%s", name);
      printAheadOf(queue, name);
    } else if(command=='d') {
      printf("%d groups currently waiting\n",queue->count);
      printList(queue);
    }
    printf(">");
    scanf(" %c",&command);
  }
}
