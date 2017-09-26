#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>

#include "student.h"

using namespace std;


//
// FileExists:
//
// Returns true if the file exists, false if not.
//
bool FileExists(string filename) {
	ifstream file(filename);

	return file.good();
}

string tail(string& fullStr, int len) {
  return fullStr.substr(fullStr.size()-len);
}

string getArgument(string line, string tag) {
  string thingToFind = " " + tag + "=\"";
  size_t pos = line.find(thingToFind);
  if(pos == string::npos) {
    return "";
  }
  pos+=thingToFind.size();
  size_t end = line.find("\"",pos);
  int len = end-pos;
  return line.substr(pos,len);
}


int main() {
	string filename = "files.txt";

	cout << "**Starting**" << endl;
	

	if (!FileExists(filename))
	{
		cout << "Error: '" << filename << "' not found, exiting..." << endl;
		return -1;
	}

	//
	// Open file and analyze the clicker data:
	//
	cout << "**Analysis**" << endl;
  cout << "Date: " << filename.substr(3,2) << "/" << filename.substr(5,2) << "/" << filename.substr(1,2) << endl;
  cout << "Time: " << filename.substr(7,2) << ":" << filename.substr(9,2) << endl;



	vector<Student> allStudents;
  ifstream inputFile(filename);
  string line;

  for(int x=0;x<40;x++) {
    getline(inputFile,line);
  }

  string currCorrect = "X";
  string sessionName;
  int numQuestions = 0;
  while(getline(inputFile,line)) {
    while(isspace(line.front())) {
      line.erase(0,1);
    }

    if(line.substr(1,1) == "s") { //Looking at session

    } else if(line.substr(1,1) == "v") { //Looking at student
      //Id starts at 8 and is 8 Long

    } else if(line.substr(1,1) == "p") { //Looking at a poll

    } else if(line.substr(1,1) == "/") {
      //Looking at the end of a block
      //Don't do anything...
    }
  }

	cout << "**END**" << endl;
	return 0;
}
