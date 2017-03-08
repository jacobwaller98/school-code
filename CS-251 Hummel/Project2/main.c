/*Jacob Waller
  CS251
  1/14/17
*/

// ignore stdlib warnings if working in Visual Studio:
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Movie_ {
  char title[256];
  int id;
  int yearReleased;
  double rating;
  int numRatings;
} Movie;

typedef struct Review_ {
  int movieId;
  int userId;
  int rating;
  char reviewDate[16];
} Review;

void parseMovieData(char *line, int lineLen, int *id, char title[], int *year) {
  line[strcspn(line, "\r\n")] = '\0';
  const char s[2] = ",";
  char* substr;
  substr = malloc(sizeof(char)*(strlen(line)+1));
  substr = strtok(line,s);
  int count = 0;
  while(substr!=NULL) {
     if(count == 0) {
       *id = atoi(substr);
     } else if(count == 1) {
       strcpy(title,substr);
     } else {
       *year = atoi(substr);
     }
     count++;
     substr = strtok(NULL,s);
  }
}

void readInMovieArr(struct Movie_ *arr, FILE* mv) {
  int count = 0;
  char line[512];
  int id;
  char title[256];
  int year;
  while(fgets(line, 512, mv) != NULL) {
    parseMovieData(line,512,&id,title,&year);
    arr[count].id = id;
    strcpy(arr[count].title,title);
    arr[count++].yearReleased = year;
  }
}

void parseReviewData(char line[],int lineLen,int *userId,int *movieId,int *rating,char reviewDate[]) {
  line[strcspn(line, "\r\n")] = '\0';
  const char s[2] = ",";
  char* substr;
  substr = malloc(sizeof(char)*(strlen(line)+1));
  substr = strtok(line,s);
  int count = 0;
  while(substr!=NULL) {
     if(count == 0) {
       *movieId = atoi(substr);
     } else if(count == 1) {
       *userId = atoi(substr);
     } else if(count == 2) {
       *rating = atoi(substr);
     } else {
       strcpy(reviewDate,substr);
     }
     count++;
     substr = strtok(NULL,s);
  }
}

void readInReviewsArr(struct Review_ *arr, FILE* mv) {
  int count = 0;
  char line[512];
  int userId=0;
  int movieId = 0;
  int rating = 0;
  char reviewDate[16];
  while(fgets(line, 512, mv) != NULL) {
    parseReviewData(line,512,&userId,&movieId,&rating,reviewDate);
    arr[count].userId = userId;
    arr[count].movieId = movieId;
    arr[count].rating = rating;
    strcpy(arr[count++].reviewDate,reviewDate);
  }

}

void sortByRating(struct Movie_ *arr, int numMovies) {
  int i,j;
  for(i=0;i<numMovies;i++) {
    for(j=0;j<numMovies-1;j++) {
      if(arr[j].rating<arr[j+1].rating) {
        struct Movie_ T = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = T;
      } else if(arr[j].rating==arr[j+1].rating) {
        if(strcmp(arr[j].title,arr[j+1].title)>0) {
          struct Movie_ T = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = T;
        }
      }
    }
  }
}

void sortByNumRatings(struct Movie_ *arr, int numMovies) {
  int i,j;
  for(i=0;i<numMovies;i++) {
    for(j=0;j<numMovies-1;j++) {
      if(arr[j].numRatings<arr[j+1].numRatings) {
        struct Movie_ T = arr[j];
        arr[j] = arr[j+1];
        arr[j+1] = T;
      } else if(arr[j].numRatings==arr[j+1].numRatings) {
        if(strcmp(arr[j].title,arr[j+1].title)>0) {
          struct Movie_ T = arr[j];
          arr[j] = arr[j+1];
          arr[j+1] = T;
        }
      }
    }
  }
}

int main(int argc, char *argv[]) {

  char movieFileName[256];
  char reviewFileName[256];
  scanf("%s",movieFileName);
  scanf("%s",reviewFileName);

  FILE* moviesFile;
  FILE* reviewsFile;
  moviesFile = fopen(movieFileName,"r");
  reviewsFile = fopen(reviewFileName,"r");

  int numMovies,numReviews;
  fscanf(moviesFile," %d ", &numMovies);
  fscanf(reviewsFile," %d ", &numReviews);

  struct Movie_ *movieArr = malloc(sizeof(struct Movie_)*numMovies);
  struct Review_ *reviewArr = malloc(sizeof(struct Review_)*numReviews);

  //printf("%s\n%s\n",movieFileName,reviewFileName);
  printf("Movies: %d\nReviews %d\n",numMovies,numReviews);

  readInMovieArr(movieArr,moviesFile);
  readInReviewsArr(reviewArr,reviewsFile);

  int x = 0;
  int i = 0;
  int id = 0;
  double sum = 0;
  int numRatings = 0;
  for(x=0;x<numMovies;x++) {
    id = movieArr[x].id;
    sum = 0;
    numRatings = 0;
    for(i=0;i<numReviews;i++) {
      if(reviewArr[i].movieId == id) {
        sum+=reviewArr[i].rating;
        numRatings++;
      }
    }
    if(numRatings!=0) {
      movieArr[x].rating = (sum/(double)numRatings);
    } else {
      //printf("NO RATINGS\n");
      movieArr[x].rating = 0;
    }
    movieArr[x].numRatings = numRatings;
    //printf("DEBUGGING: sum: %f numRatings: %d movieArr[x].title: %s\n",sum,numRatings,movieArr[x].title);
    //printf("DEBUGGING 2: movieArr[x].rating: %f movieArr[x].numRatings: %d \n",movieArr[x].rating,movieArr[x].numRatings );
  }

  sortByRating(movieArr,numMovies);
  printf("**Top-10 by Rating**\n");
  for(i=0;i<10;i++) {
    printf("%d: %f,'%s'\n",i+1,movieArr[i].rating,movieArr[i].title);
  }

  sortByNumRatings(movieArr,numMovies);
  printf("**Top-10 by Num Reviews**\n");
  for(i=0;i<10;i++) {
    printf("%d: %d,'%s'\n",i+1,movieArr[i].numRatings,movieArr[i].title);
  }
  return 0;
}

/*

*/
