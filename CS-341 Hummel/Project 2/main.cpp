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
  
}



int main() {
  cout << "**Starting**" << endl;
  vector<Session> allSessions;
  ifstream inputFile("files.txt");
  string currLine;
  while(getline(inputFile,currLine)) {
    allSessions.push_back(getSessionFromFile(currLine));
  }


}
