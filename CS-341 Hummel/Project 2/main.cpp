/*
  Jacob Waler
  673978936
  9/25/2017
*/
#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <functional>

#include "session.h"

using namespace std;

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

Session getSessionFromFile(string filename) {
  ifstream inputFile(filename);
  Session currSession;
  string currAnswer = "";
  string line;
  while(getline(inputFile,line)) {
    while(isspace(line.front())) {
      line.erase(0,1);
    }
    if(line.substr(1,1) == "s") { //Looking at session
      currSession.setName(getArgument(line,"ssnn"));
    } else if(line.substr(1,1) == "v") { //Looking at student
      string studentsAnswer = getArgument(line,"ans");
      if(studentsAnswer.size() != 0)
        currSession.studentAnswered(getArgument(line, "id"), currAnswer.find(studentsAnswer) != string::npos);
    } else if(line.substr(1,1) == "p") { //Looking at a poll
      currAnswer = getArgument(line,"cans");
      currSession.newQuestion();
    } else if(line.substr(1,1) == "/") {
      //Looking at the end of a block
      //Don't do anything...
    }
  }
  return currSession;
}



int main() {
  cout << "**Starting**" << endl;
  vector<Session> allSessions;
  ifstream inputFile("files.txt");
  string currLine;
  int numSessions = 0;
  int numQuestions = 0;
  while(getline(inputFile,currLine)) {
    cout << ">>Parsing '" << currLine << "'..." << endl;
    numSessions++;
    allSessions.push_back(getSessionFromFile(currLine));
    numQuestions += allSessions[allSessions.size()-1].getNumQuestions();
  }

  cout << endl << "**Class Analysis**" << endl;
  cout << ">>Total sessions: " << numSessions << endl;
  cout << ">>Total questions: " << numQuestions << endl;


  cout << ">>Answered:" << endl;
  for(auto& s : allSessions) {
    cout << "  \"" << s.getName() << "\": " << 100.0*(s.getNumAnswers() /((double)s.getNumQuestions() * s.getNumClickers())) << "% (";
    cout << s.getNumQuestions() << " questions, " << s.getNumClickers();
    cout << " clickers, " << s.getNumAnswers() << " answers)" << endl;
  }

  cout << ">>Correctly:" << endl;
  for(auto& s : allSessions) {
    cout << "  \"" << s.getName() << "\": " << 100.0*(s.getNumCorrect() /((double)s.getNumQuestions() * s.getNumClickers())) << "% (";
    cout << s.getNumQuestions() << " questions, " << s.getNumClickers();
    cout << " clickers, " << s.getNumCorrect() << " correct answers)" << endl;
  }

  cout << endl << "**Student Analysis**" << endl;

  cout << ">> Enter a clicker id (# to quit): ";
  cin >> currLine;
  while(currLine != "#") {
    currLine.insert(0,"#");
    std::vector<Student> tempV;
    for(auto& s : allSessions) {
      std::vector<Student>::iterator it = s.getStudentById(currLine);
      if(it != s.getEndOfStudents()) {
        Student tmp = *it;
        tempV.push_back(tmp);
        cout << "  \"" << s.getName() << "\": " << tmp.getAnswered() << " out of " << s.getNumQuestions();
        cout << " answered, " << 100.0*(tmp.getCorrect() / (double) s.getNumQuestions()) << "% correctly"<< endl;
      }
    }
    if(tempV.size() == 0) {
      cout << "**not found..." << endl;
    }

    cout << endl << ">> Enter a clicker id (# to quit): ";
    cin >> currLine;
  }


  cout << endl << endl <<"**END**";
}
