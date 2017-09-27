#pragma once

#include <string>
#include <vector>
#include <algorithm>
#include "student.h"

using namespace std;

class Session {
private:
  std::string sessionName;
  int numAnswers;
  int numCorrect;
  int numClickers;
  int numQuestions;
  int numWrong;
  std::vector<Student> v;
public:
  Session() {
    numCorrect = 0;
    numAnswers = 0;
    numClickers = 0;
    numQuestions = 0;
    numWrong = 0;
  }

  void setName(std::string name) {
    sessionName = name;
  }

  string getName() {
    return sessionName;
  }

  int getNumClickers() {
    return numClickers;
  }

  int getNumCorrect() {
    return numCorrect;
  }

  int getNumAnswers() {
    return numCorrect+numWrong;
  }

  int getNumQuestions() {
    return numQuestions;
  }

  void newQuestion() {
    numQuestions++;
  }

  std::vector<Student>::iterator getEndOfStudents() {
    return v.end();
  }

  std::vector<Student>::iterator getStudentById(string id) {
    std::vector<Student>::iterator it = find_if(v.begin(),v.end(),
    [id](Student s){
      return s.getId() == id;
    });
    return it;
  }

  void studentAnswered(std::string id, bool gotRight) {
    numAnswers++;
    std::vector<Student>::iterator it;

    it = find_if(v.begin(),v.end(),
    [&](Student s){
      return s.getId() == id;
    });

    if(it == v.end()) {//Student is not in vector
      Student currStudent(id);
      if(gotRight) {
        numCorrect++;
        currStudent.gotOneRight();
      } else {
        numWrong++;
        currStudent.gotOneWrong();
      }
      v.push_back(currStudent);
      numClickers++;
    } else {
      if(gotRight) {
        numCorrect++;
        it->gotOneRight();
      } else {
        numWrong++;
        it->gotOneWrong();
      }
    }
  }

};
