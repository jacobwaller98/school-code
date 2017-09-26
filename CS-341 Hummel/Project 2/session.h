#pragma once

#include <string>

class Session {
private:
  std::string sessionName;
  int numQuestions;
  int numCorrect;
  int numClickers;
public:
  Session(std::string name)
  : sessionName(name) {
    numAnswered = 0;
    numCorrect = 0;
    numClickers = 0;
  }

  void addClicker() {
    numClickers++;
  }

  int getCorrect() {
    return numCorrect;
  }

  void gotCorrect() {
    numCorrect++;
  }

  int getNumAsked() {
    return numQuestions;
  }

  void askedQuestion() {
    numQuestions++;
  }

}
