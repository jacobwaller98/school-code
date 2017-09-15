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
	string filename = "L1709081300.xml";

	cout << "**Starting**" << endl;
	cout << "**Filename?**" << endl;
	cin >> filename;
	cout << filename << endl;

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
      string sessionName = getArgument(line,"ssnn");
      cout << "Name: \"" << sessionName << "\"" << endl;
    } else if(line.substr(1,1) == "v") { //Looking at student
      //Id starts at 8 and is 8 Long

      string currId = getArgument(line,"id");

      vector<Student>::iterator it = find_if(allStudents.begin(),allStudents.end(),
        [&](Student& s1) {
          return s1.getId() == currId;
        }
      );



      if(it==allStudents.end()) {
        //Student isn't in list
        Student currStudent(currId);
        //Determine if they're right...
        if(currCorrect.find(getArgument(line,"ans")) != string::npos && getArgument(line,"ans").size() != 0) {
          currStudent.gotOneRight();
        } else {
          if(getArgument(line,"ans").size() != 0)
            currStudent.gotOneWrong();
        }
        allStudents.push_back(currStudent);
      } else {
        //Student is in list
        //Determine if they're right...
        if(currCorrect.find(getArgument(line,"ans")) != string::npos && getArgument(line,"ans").size() != 0) {
          it->gotOneRight();
        } else {
          if(getArgument(line,"ans").size() != 0) {
            it->gotOneWrong();
					}
        }
      }
    } else if(line.substr(1,1) == "p") { //Looking at a poll
      currCorrect = getArgument(line,"cans");
      numQuestions++;
    } else if(line.substr(1,1) == "/") {
      //Looking at the end of a block
      //Don't do anything...
    }
  }
  //Done reading the file...
  //Start displaying info...
  cout << "# questions: " << numQuestions << endl;
  cout << "# clickers:  " << allStudents.size() << endl;

  cout << "# of students who answered:" << endl;
  cout << "  All questions: " << count_if(allStudents.begin(),allStudents.end(),
    [=](Student& s) {
      return s.getAnswered() == numQuestions;
    }) << endl;

  cout << "  At least half: " << count_if(allStudents.begin(),allStudents.end(),
    [=](Student& s) {
      return s.getAnswered() >= (numQuestions+1)/2;
    }) << endl;

  cout << "  At least one: " << count_if(allStudents.begin(), allStudents.end(),
    [](Student& s) {
      return s.getAnswered() >= 1;
    }) << endl;

  cout << "  None: " << count_if(allStudents.begin(), allStudents.end(),
    [](Student& s) {
      return s.getAnswered() == 0;
    }) << endl;

  cout << "# of students who answered correctly: " << endl;

  cout << "  All questions: " << count_if(allStudents.begin(),allStudents.end(),
    [=](Student& s) {
      return s.getCorrect() == numQuestions;
    }) << endl;

  cout << "  At least half: " << count_if(allStudents.begin(),allStudents.end(),
    [=](Student& s) {
      return s.getCorrect() >= (numQuestions+1)/2;
    }) << endl;

  cout << "  At least one : " << count_if(allStudents.begin(), allStudents.end(),
    [](Student& s) {
      return s.getCorrect() >= 1;
    }) << endl;

    cout << "  None : " << count_if(allStudents.begin(), allStudents.end(),
    [](Student& s) {
      return s.getCorrect() <= 0;
    }) << endl;


  cout << "Students who answered < half:" << endl;
  for(Student& s:allStudents) {
    if(s.getAnswered() < (numQuestions+1)/2) {
      cout << s.getId() << endl;
    }
  }

  cout << "Students with 0 correct:" << endl;

  for(Student& s:allStudents) {
    if(s.getCorrect() == 0) {
      cout << s.getId() << endl;
    }
  }


	cout << "**END**" << endl;
	return 0;
}
