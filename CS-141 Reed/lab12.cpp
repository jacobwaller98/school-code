//
//  main.cpp
//  Lab 12
//
//

#include <iostream>
using namespace std;

struct Node{
    int data;
    Node *pNext;
};

void displayList(Node *head){
    Node *pTemp = head;
    do{
        cout << pTemp->data << " ";
        pTemp = pTemp->pNext;
    }while(pTemp != NULL);
    cout << endl;
}

int main() {
    char input;
    int number;
    Node *pHead = NULL;
    Node *pTemp;
    
    cout << "=====Question 1=====" << endl;
    
    cout << "Enter an integer to add to linked list: ";
    cin >> number;
    pTemp = new Node;
    pTemp->data = number;
    pTemp->pNext = pHead;
    pHead = pTemp;
    displayList(pHead);
    
    cout << "Enter an integer to add to linked list: ";
    cin >> number;
    pTemp = new Node;
    pTemp->data = number;
    pTemp->pNext = pHead;
    pHead = pTemp;
    displayList(pHead);
    
    cout << "=====Question 2=====" << endl;
    
    pTemp = pHead;
    pHead = pHead->pNext;
    free(pTemp);
    displayList(pHead);
    
    cout << "=====Question 3=====" << endl;
    
    while(true){
        cout << "Enter 'a' to add or 'd' to delete the front of the list: ";
        cin >> input;
        if(input == 'a'){
            cout << "Enter value: ";
            cin >> number;
            pTemp = new Node;
            pTemp->data = number;
            pTemp->pNext = pHead;
            pHead = pTemp;
            displayList(pHead);
        }else{
            pTemp = pHead;
            pHead = pHead->pNext;
            free(pTemp);
            displayList(pHead);
        }
    }
    
    return 0;
}
