/*student.h*/
#pragma once

#include <string>
using namespace std;

class Student
{
private:
	int totalAnswered;
	int totalCorrect;
	string id;

public:
	Student(std::string clickerId)
  : id(clickerId)
  {
    totalAnswered = 0;
    totalCorrect = 0;
  }

  string getId() {
    return id;
  }

  void gotOneRight() {
    totalCorrect++;
    totalAnswered++;
  }

  void gotOneWrong() {
    totalAnswered++;
  }

  int getAnswered() {
    return totalAnswered;
  }

  int getCorrect() {
    return totalCorrect;
  }
};
