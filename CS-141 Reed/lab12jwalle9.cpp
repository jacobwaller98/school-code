/*
	Jacob Waller 673978936
	Pawel Klimek 671153576
*/

#include <iostream>
#include <cstdlib>
using namespace std;

//Node struct definition
struct Node {
	int data;
	Node *pNext;
};

//function to display nodes in linked list
void displayList(Node *head) {
	Node *pTemp = head;
	do {
		cout << pTemp->data << " ";
		pTemp = pTemp->pNext;
	} while (pTemp != NULL);
	cout << endl;
}

void add(Node *head,int value) {
	Node *temp = head;
	while (temp->pNext != NULL) {
		temp = temp->pNext;
	}
	temp->pNext = (Node *)malloc(sizeof(Node));
	temp->pNext->data = value;
	temp->pNext->pNext = NULL;
}


int main() {
	cout << "Jacob Waller 673978936\nPawel Klimek 671153576";


	char input;
	int number;
	Node *pHead = NULL;
	Node *pTemp;

	cout << "=====Question 1=====" << endl;

	cout << "Enter an integer to add to linked list: ";
	cin >> number;
	//Write code to add a node with data = number to linked list
	pHead = (Node *)malloc(sizeof(Node));
	pHead->data = number;
	pHead->pNext = NULL;
	//add(pHead, number);
	
	


	//display list
	displayList(pHead);

	cout << "Enter an integer to add to linked list: ";
	cin >> number;
	//Write code to add another node with data = number to linked list
	pTemp = pHead;
	pTemp = pTemp->pNext;
	pTemp = (Node *)malloc(sizeof(Node));
	pTemp->data = number;
	pTemp->pNext = NULL;
	pHead->pNext = pTemp;
	
	//display list
	displayList(pHead);

	cout << "=====Question 2=====" << endl;

	//Write code to delete the node in the front of the list
	pHead = pHead->pNext;


	//display list
	displayList(pHead);

	cout << "=====Question 3=====" << endl;

	//Write code that run in an infinite loop, asking user to input 'a' for add new node to the front,
	//or 'd' to delete node in the front
	//When user inputs 'a', ask user to input an integer
	//After adding or deleting, display the list
	char c;
	int value;
	while (true) {
		cout << "Input a to add, d to delete front";
		cin >> c;
		if (c == 'a') {
			cout << "Input value";
			cin >> value;
			add(pHead, value);
		}
		else {
			pHead = pHead->pNext;
		}
		displayList(pHead);
	}

	return 0;
}