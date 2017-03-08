#include <stdio.h>
#include <stdlib.h>

#define TRUE   1
#define FALSE  0

#define COUNT 10

struct linkedStruct
{
 int elem;
 struct linkedStruct*  next;
};

typedef struct linkedStruct linked;
typedef linked* linkedPtr;

/* written with a pass-by-value parameter */
/* calling code:                          */
/*     linked* head = NULL;               */
/*      ...                               */
/*     listLength (head);                 */
int listLength (linked* hd)
{
 int length = 0;
 while (hd != NULL)
   {
    length++;
    hd = hd->next;
   }
 return length;
}

/* written with a C-style pass-by-"reference" parameter */
/* calling code:                                        */
/*     linked* head = NULL;                             */
/*      ...                                             */
/*     listLength (&head);                              */
int listLengthPBR (linked** hd)
{
 int length = 0;
 while (*hd != NULL)
   {
    length++;
    *hd = (*hd)->next;
   }
 return length;
}

/* the first parameter hd is C-style pass-by-reference */
void insertAtFront (linked** hd, int val)
{
 linked* ptr = (linked*) malloc (sizeof(linked));
 ptr->elem = val;
 ptr->next = *hd;       /* note that hd must be "de-referenced" when used */
 *hd = ptr;             /*   the unary * operator is the "de-reference" operator */
}

/* the first parameter, hd, is pass-by-value */
linked* insertAtFront2 (linked* hd, int val)
{
 linked* ptr = (linked*) malloc (sizeof(linked));
 ptr->elem = val;
 ptr->next = hd;	/* note that had is NOT de-referenced here */
 hd = ptr;
 return hd;
}

void removeFromFront (linked** hd)
{
 linked* ptr = *hd;

 if (ptr != NULL)
   {
    *hd = ptr->next;
    free (ptr);
   }
}

linked* removeFromFront2 (linked* hd) {
  if(hd==NULL)
    return hd;
  linked* tmp = hd->next;
  free(hd);
  return tmp;
}

int getFirstValue (linked* hd)
{
 if (hd != NULL)
   return hd->elem;
 else
   return -999;   /* returns -999 if list is empty */
}

int isEmpty (linked* hd)
{
 if (hd == NULL)
   return TRUE;
 else
   return FALSE;
}


void show (linked* hd)
{
 while (hd != NULL)
   {
    printf ("%d, ", hd->elem);
    hd = hd->next;
   }
 printf ("\n");
}

/* code to insert at the end of the linked list */
void insertAtEnd (linked** hd, int val)
{
 linked* curr = *hd;
 linked* prev = NULL;

 /* find the end of the list */
 while (curr != NULL)
   {
    prev = curr;
    curr = curr->next;
   }

 /* create the new node to be inserted into the list */
 linked* ptr = (linked*) malloc (sizeof(linked));
 ptr->elem = val;
 ptr->next = curr;   /* curr is always NULL at this line of code */

 /* if the list is empty the new node goes at the front (update head) */
 if (prev == NULL)
   {
    *hd = ptr;
   }
 else
   {
    prev->next = ptr;
   }
}


/* code to insert at the end of the linked list */
linked* insertAtEnd2 (linked* hd, int val)
{
  linked* ret = hd;
  linked*prev=NULL;
  while(hd!=NULL) {
    prev = hd;
    hd=hd->next;
  }
  hd = malloc(sizeof(linked));
  hd->elem = val;
  prev->next = hd;
  return ret;
}



/* code to insert values in increasing order               */
/*   smaller values placed closer to the front of the list */
/*   larger values placed closer to the end of the list    */
void insertInOrder (linked** hd, int val)
{
 linked* curr = *hd;
 linked* prev = NULL;

 /* set curr to refer to the node in the list that is >= val */
 /*   prev will be set to the node just before curr */
 while ((curr != NULL) && (curr->elem < val))
   {
    prev = curr;
    curr = curr->next;
   }

 /* create the node to add into the list */
 linked* ptr = (linked*) malloc (sizeof(linked));
 ptr->elem = val;
 ptr->next = curr;

 /* if prev is null, insert at the front of the list */
 if (prev == NULL)
   {
    *hd = ptr;
   }
 else
   {  /* otherwise insert right after prev */
    prev->next = ptr;
   }
}

void incBy100(linked* hd) {
  linked* temp = hd;
  while(temp!=NULL) {
    temp->elem+=200;
    temp = temp->next;
  }
}

int find(linked* hd, int target) {
  linked* temp = hd;
  while(temp!=NULL) {
    if(temp->elem == target) {
      return TRUE;
    }
    temp = temp->next;
  }
  return FALSE;
}

/* code to insert values in increasing order               */
/*   smaller values placed closer to the front of the list */
/*   larger values placed closer to the end of the list    */
linked* insertInOrder2 (linked* hd, int val)
{
  linked*ret = hd;
  linked*prev=NULL;
  while(hd!=NULL) {
    if(hd->elem > val) {
      break;
    }
  }
  if(prev==NULL) {
    ret = insertAtFront2(ret,val);
  }
  linked*ins = malloc(sizeof(linked));
  prev->next = ins;
  ins->next = hd;
  ins->elem = val;
  return ret;
}

linked* removeFromEnd(linked* hd) {
  linked*ret = hd;
  linked*prev=NULL;
  while(hd!=NULL) {
    if(hd->next==NULL) {
      break;
    }
    prev=hd;
    hd=hd->next;
  }
  free(prev->next);
  prev->next=NULL;
  return ret;
}


int main (int argc, char**argv)
{
  linked* head = NULL;
  linked* head2 = NULL;
  int i;
  int temp;

  /* adding values to the front of the list */
  for (i = 0 ; i < COUNT; ++i)
    {
     temp = rand() % 100;
     printf ("In main(): temp: %6d\n", temp);

     /* head being sent as C-style pass-by-reference */
     insertAtFront (&head, temp);		  /* must send the address of head here */

     /* head2 being sent as C pass-by-value */
     head2 = insertAtFront2 (head2, temp + 100);  /* must send the value of head2 here */

     printf ("The list has %d nodes\n", listLength(head));
     show (head);
     printf ("List 2 has %d nodes\n", listLength(head2));
     show (head2);
    }
    //incBy100(head);
    //show(head);

  printf ("\n\nRemoving items from the list\n");
  for (i = 0 ; i < COUNT/2 ; i++)
    {
     int val = getFirstValue (head);
     printf ("The first item in the list is: %d\n", val);
     removeFromFront (&head);

     /* to help show what is going on */
     printf ("The list has %d nodes\n", listLength(head));
     show (head);
    }

  printf ("\n");

  printf ("\n\nRemoving items from the list2\n");
  for (i = 0 ; i < COUNT/2 ; i++)
    {
     int val = getFirstValue (head2);
     printf ("The first item in the list is: %d\n", val);
     head2 = removeFromFront2 (head2);

     /* to help show what is going on */
     printf ("List 2 has %d nodes\n", listLength(head2));
     show (head2);
    }



  printf ("\n");


}
