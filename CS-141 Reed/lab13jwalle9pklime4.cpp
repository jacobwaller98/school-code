// lab13.cpp : Defines the entry point for the console application.
//

#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include <fstream>
#include <string>

using namespace std;


struct person {
	char name[16];
	char sex;
	int travelP;
};

struct node {
	node * next;
	person data;
};

node *head = NULL;

void prepend(person nextP) {

	if (head != NULL) {
		node * tempNode = head;
		head->data = nextP;
		head->next = tempNode;
	}
	else {
		head = new node;
		head->data = nextP;
		head->next = NULL;
	}
}

void displayList() {
	node* temp = head;
	while (temp != NULL) {
		cout << head->data.name << " " << head->data.sex << " " << head->data.travelP << endl;
		temp = temp->next;
	}
}



int main() {
	
	
	//char word[62];
		
	ifstream myfile("CandidateList.txt");
	if (myfile.is_open()){
		while (!myfile.eof()){
			char name[16];
			myfile >> name;
			cout << name;
			char sex;
			myfile >> sex;
			int travel;
			myfile >> travel;
			person tempP;
			strcpy(tempP.name,name);
			tempP.sex = sex;
			tempP.travelP = travel;
			
			prepend(tempP);
		}
		myfile.close();
	}
	else{
		cout << "Unable to open file";
	}

	displayList();

    return 0;
}

