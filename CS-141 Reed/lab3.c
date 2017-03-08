#include <stdio.h>
/*
  Jacob Waller UIN: 673978936
  Pawel Klimek UIN: 671153576
*/

void printName() {
  printf("Jacob Waller UIN: 673978936");
  printf("Pawel Klimek UIN: 671153576");
}

int getMax(int num1, int num2) {
  if(num1>num2) {
    return num1;
  }
  return num2;
}

int getMin(int num1, int num2) {
  if(num1<num2) {
    return num1;
  }
  return num2;
}

int getSum(int num1, int num2) {
  return num1 + num2;
}

void displayInOrder(int num1, int num2, int num3) {
  int nums[3] = {num1, num2, num3};
  int x,y, temp;
  for(x = 0; x < 3; x++) {
    for(y = 0; y < 3 ; y++) {
      if(nums[y] < nums[y+1]) {
        temp = nums[y];
        nums[y] = nums[y+1];
        nums[y+1] = temp;
      }
    }
  }
  printf("%d, %d, %d\n",nums[2], nums[1], nums[0] );
}

int main() {
  int num1, num2, num3;
  printf("Task 1: get the sum of two numbers\n");
  printf("Please enter two integers: ");
  scanf("%d %d", &num1, &num2);
  printf("The sum of the two numbers is: %d\n", getSum(num1,num2));
  printf("\n");

  printf("Task 2: get the minimum of two numbers\n");
  printf("Please enter two integers: ");
  scanf("%d %d", &num1, &num2);
  printf("The minimum of the two numbers is: %d\n", getMin(num1,num2));
  printf("\n");

  printf("Task 3: display three numbers in ascending order\n");
  printf("Please enter three integers: ");
  scanf("%d %d %d", &num1, &num2, &num3);
  printf("The ascending order is: ");
  displayInOrder(num1,num2,num3);
  printf("\n");

  printf("Task 3: display three numbers in ascending order\n");
  printf("Please enter three integers: ");
  scanf("%d %d %d", &num1, &num2, &num3);
  printf("The ascending order is: ");
  displayInOrder(num1,num2,num3);
  return 0;
}
