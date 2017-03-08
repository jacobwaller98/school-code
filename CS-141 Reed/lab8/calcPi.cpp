#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

double evaluteAt(double x) {
  return 1/(pow(x,3)-11*pow(x,2) - pow(2.71,x));
}

int main() {
  int partitions;
  double a,b;
  std::cout << "Number of partitions: ";
  cin >> partitions;
  std::cout << "Start: ";
  cin >> a;
  std::cout << "End: ";
  cin >> b;
  double result = 0;
  if(partitions % 2 != 0 || partitions == 0) {
    return -1;
  }
  int counter = 0;
  for(double i = a; i < b; i+=(1/((double)partitions))) {
    //cout << 1/((double)partitions) << '\n';
    if(counter==0 || counter == partitions) {
      result+=evaluteAt(i);
    } else {
      if(counter%2 == 0) {
        result+=2.0*evaluteAt(i);
      } else {
        result+=4.0*evaluteAt(i);
      }
    }
    counter++;
  }
  result*=(1/((double)partitions))/3;
  printf("%.15lf\n",result );


}
/*

calcPi.cpp36:1(36, 752)
CRLFUTF-8C++
C:\Users\Jacob Waller\Desktop\School Stuff\CS-141\lab8\calcPi.cpp: In function 'int main()':
C:\Users\Jacob Waller\Desktop\School Stuff\CS-141\lab8\calcPi.cpp:19:35: error: expected ';' before ')' token
cout << 1/((double)partitions)) << '\n';
^
*/
