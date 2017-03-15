/*main.cpp*/

//
// Divvy Bike Ride Route Analysis, using AVL trees.
//
// Jacob Waller
// 673978936
// U. of Illinois, Chicago
// CS251, Spring 2017
// Project #04
//

// ignore stdlib warnings if working in Visual Studio:
#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <math.h>

#include "avl.h"



//
// distBetween2Points:
//
// Returns the distance in miles between 2 points (lat1, long1) and (lat2, long2).
//
double distBetween2Points(double lat2, double long2, double lat1, double long1) {
  //
  // Reference: http://www8.nau.edu/cvm/latlon_formula.html
  //
  double PI = 3.14159265;
  double earth_rad = 3963.1;  // statue miles:

  double lat1_rad = lat1 * PI / 180.0;
  double long1_rad = long1 * PI / 180.0;
  double lat2_rad = lat2 * PI / 180.0;
  double long2_rad = long2 * PI / 180.0;

  double dist = earth_rad * acos((cos(lat1_rad)*cos(long1_rad)*cos(lat2_rad)*cos(long2_rad))+
    (cos(lat1_rad)*sin(long1_rad)*cos(lat2_rad)*sin(long2_rad))+(sin(lat1_rad)*sin(lat2_rad)));

  return dist;
}


//
// getFileName:
//
// Inputs a filename from the keyboard, make sure the file can be
// opened, and returns the filename if so.  If the file cannot be
// opened, an error message is output and the program is exited.
//
char *getFileName()
{
  char filename[512];
  int  fnsize = sizeof(filename) / sizeof(filename[0]);

  // input filename from the keyboard:
  fgets(filename, fnsize, stdin);
  filename[strcspn(filename, "\r\n")] = '\0';  // strip EOL char(s):

  // make sure filename exists and can be opened:
  FILE *infile = fopen(filename, "r");
  if (infile == NULL)
  {
    printf("**Error: unable to open '%s'\n\n", filename);
    exit(-1);
  }

  fclose(infile);

  // duplicate and return filename:
  char *s = (char *)malloc((strlen(filename) + 1) * sizeof(char));
  strcpy(s, filename);

  return s;
}


//
// skipRestOfInput:
//
// Inputs and discards the remainder of the current line for the
// given input stream, including the EOL character(s).
//
void skipRestOfInput(FILE *stream)
{
  char restOfLine[256];
  int rolLength = sizeof(restOfLine) / sizeof(restOfLine[0]);

  fgets(restOfLine, rolLength, stream);
}

void setInputStation(char input[], int* id, char name[], double* lat, double* lon, int* cap, char date[]) {
    char *tok;
    char *ptr = input;
    int counter = 0;
    while ((tok = strtok(ptr, ",")) != NULL) {
        switch (counter++) {
          case 0:
            *id = atoi(tok);
            break;
          case 1:
            strcpy(name,tok);
            break;
          case 2:
            *lat = atof(tok);
            break;
          case 3:
            *lon = atof(tok);
            break;
          case 4:
            *cap = atoi(tok);
            break;
          case 5:
            strcpy(date,tok);
            break;
          default:
            //Cry :'(
            break;
        }
        ptr = NULL;
    }
}

void setInputTrips(char input[], int* id, char startTime[64], char stopTime[64],int* bike, int* duration, int* fromStat, char fromStatName[], int* toStat, char toStatName[]) {
  char *tok;
  char *ptr = input;
  int counter = 0;
  while ((tok = strtok(ptr, ",")) != NULL) {
      switch (counter++) {
        case 0:
          *id = atoi(tok);
          break;
        case 1:
          strcpy(startTime,tok);
          break;
        case 2:
          strcpy(stopTime,tok);
          break;
        case 3:
          *bike = atoi(tok);
          break;
        case 4:
          *duration = atoi(tok);
          break;
        case 5:
          *fromStat = atoi(tok);
          break;
        case 6:
          strcpy(fromStatName,tok);
          break;
        case 7:
          *toStat = atoi(tok);
          break;
        case 8:
          strcpy(toStatName,tok);
          break;
        default:
          //Cry :'(
          break;
      }
      ptr = NULL;
  }
}


void getListWithin(AVLNode* root, AVLNode** arr, int* index, double lat, double lon, double dist) {
  if(root==NULL) {
    return;
  }
  getListWithin(root->Left,arr,index,lat,lon,dist);
  if(distBetween2Points(lat,lon,root->value.Station.lat,root->value.Station.lon) <= dist) {
    arr[(*index)++] = root;
  }
  getListWithin(root->Right,arr,index,lat,lon,dist);
}

void printWithinDist(AVL* tree,double lat, double lon, double dist) {
  int size = 50000;
  int index = 0;
  int i,j;
  AVLNode** arr = malloc(sizeof(AVLNode*)*size);

  getListWithin(tree->root, arr, &index, lat, lon, dist);

  AVLNode* temp;
  for(j=0;j<index;j++) {

    for(i=0;i<index-1;i++) {
      double one = distBetween2Points(lat,lon,arr[i]->value.Station.lat,arr[i]->value.Station.lon);
      double two = distBetween2Points(lat,lon,arr[i+1]->value.Station.lat,arr[i+1]->value.Station.lon);

      if(one > two) {
        temp = arr[i];
        arr[i] = arr[i+1];
        arr[i+1] = temp;
      } else if(one == two) {

        if(arr[i]->value.Station.StationID > arr[i+1]->value.Station.StationID) {
          temp = arr[i];
          arr[i] = arr[i+1];
          arr[i+1] = temp;
        }

      }
    }
  }


  for(i=0;i<index;i++) {
    printf("Station %d: distance %lf miles\n",arr[i]->value.Station.StationID,distBetween2Points(lat,lon,arr[i]->value.Station.lat,arr[i]->value.Station.lon));
  }
  free(arr);
}

int contains(AVLNode* root, int id1, int id2) {
  if(root == NULL) {
    return 0;
  } else if(root->value.Trip.fromStat==id1 && root->value.Trip.toStat==id2) {
    return 1+contains(root->Right,id1,id2)+contains(root->Left,id1,id2);
  } else {
    return contains(root->Right,id1,id2)+contains(root->Left,id1,id2);
  }
}

int countTripsWithStations(AVL* tree, AVLNode** arr1, AVLNode** arr2, int size1, int size2) {
  AVLNode* root = tree->root; //Trips root

  int* idArr1 = malloc(sizeof(int)*size1);
  int* idArr2 = malloc(sizeof(int)*size2);
  int i,j;

  for(i=0;i<size1;i++) {
    idArr1[i] = arr1[i]->value.Station.StationID;
  }
  for(i=0;i<size2;i++) {
    idArr2[i] = arr2[i]->value.Station.StationID;
  }

  int count = 0;
  for(i=0;i<size1;i++) {
    for(j=0;j<size2;j++) {
        count+=contains(root,idArr1[i],idArr2[j]);
    }
  }
  free(idArr1);
  free(idArr2);
  return count;
}


///////////////////////////////////////////////////////////////////////
//
// main:
//
int main()
{
  printf("** Welcome to Divvy Route Analysis **\n");

  //
  // get filenames from the user/stdin:
  //
  char  cmd[64];
  char *StationsFileName = getFileName();
  char *TripsFileName = getFileName();
  FILE* stationsFile = fopen(StationsFileName,"r");
  FILE* tripsFile = fopen(TripsFileName,"r");
  free(StationsFileName);
  free(TripsFileName);
  //
  // As an example, create some trees and insert some
  // dummy (key, value) pairs:
  //
  AVL *stations = AVLCreate();
  AVL *trips = AVLCreate();
  AVL *bikes = AVLCreate();

  //
  // Insert a new station:
  //
  AVLValue stationValue;
  stationValue.Station.numTrips = 0;
  char input[512];
  int tempId;
  double lat, lon;
  int tempCap;
  char name[256];
  char date[32];
  fgets(input,511,stationsFile); //Double because of header

  fgets(input,511,stationsFile);
  while(!feof(stationsFile)) {
    setInputStation(input,&tempId,name,&lat,&lon,&tempCap,date);
    stationValue.Type = STATIONTYPE;  // union holds a station:
    stationValue.Station.StationID = tempId;
    strcpy(stationValue.Station.name, name);
    stationValue.Station.lon = lon;
    stationValue.Station.lat = lat;
    strcpy(stationValue.Station.date,date);
    stationValue.Station.capacity = tempCap;

    AVLInsert(stations, stationValue.Station.StationID, stationValue);

    fgets(input,511,stationsFile);
  }

  char startTime[64],stopTime[64],fromStatName[64],toStatName[64];
  int bike,duration,fromStat,toStat;
  AVLValue tripValue;
  tripValue.Type = TRIPTYPE;

  AVLValue bikeValue;
  bikeValue.Type = BIKETYPE;
  bikeValue.Bike.numTrips = 1;

  fgets(input,511,tripsFile);

  fgets(input,511,tripsFile);
  while(!feof(tripsFile)) {
    setInputTrips(input,&tempId,startTime,stopTime,&bike,&duration,&fromStat,fromStatName,&toStat,toStatName);

    tripValue.Trip.TripID = tempId;
    strcpy(tripValue.Trip.startTime, startTime);
    strcpy(tripValue.Trip.stopTime, stopTime);
    tripValue.Trip.bike = bike;
    tripValue.Trip.duration = duration;
    tripValue.Trip.fromStat = fromStat;
    tripValue.Trip.toStat = toStat;
    strcpy(tripValue.Trip.fromStatName,fromStatName);
    strcpy(tripValue.Trip.toStatName,toStatName);

    bikeValue.Bike.BikeID = bike;

    if(AVLSearch(bikes,bike) == NULL) {
      AVLInsert(bikes,bike,bikeValue);
    } else {
      (AVLSearch(bikes,bike)->value.Bike.numTrips)++;
    }

    (AVLSearch(stations,fromStat)->value.Station.numTrips)++;
    (AVLSearch(stations,toStat)->value.Station.numTrips)++;

    AVLInsert(trips, tripValue.Trip.TripID, tripValue);
    fgets(input,511,tripsFile);
  }

  fclose(stationsFile);
  fclose(tripsFile);


  //AVLInsert(bikes, bikeValue.Bike.BikeID, bikeValue);

  //
  // now interact with user:
  //
  AVLNode* temp;
  printf("** Ready **\n");

  scanf("%s", cmd);

  while (strcmp(cmd, "exit") != 0)
  {
    temp = NULL;
    if (strcmp(cmd, "stats") == 0)
    {
      //
      // Output some stats about our data structures:
      //
      printf("** Trees:\n");

      printf("   Stations: count = %d, height = %d\n",
        AVLCount(stations), AVLHeight(stations));
      printf("   Trips:    count = %d, height = %d\n",
        AVLCount(trips), AVLHeight(trips));
      printf("   Bikes:    count = %d, height = %d\n",
        AVLCount(bikes), AVLHeight(bikes));
    } else if(strcmp(cmd,"station") == 0) {
      scanf("%d", &tempId);
      temp = AVLSearch(stations,tempId);
      if(temp==NULL) {
        printf("**not found\n");
      } else {
        printf("**Station %d:\n",temp->value.Station.StationID);
        printf("  Name: '%s'\n",temp->value.Station.name);
        printf("  Location:   (%lf,%lf)\n",temp->value.Station.lat,temp->value.Station.lon);
        printf("  Capacity:   %d\n",temp->value.Station.capacity);
        printf("  Trip count: %d\n",temp->value.Station.numTrips);
      }


    } else if(strcmp(cmd,"trip") == 0) {
      scanf("%d", &tempId);
      temp = AVLSearch(trips,tempId);
      if(temp==NULL) {
        printf("**not found\n");
      } else {
        printf("**Trip %d:\n",temp->value.Trip.TripID);
        printf("  Bike: %d\n",temp->value.Trip.bike);
        printf("  From: %d\n",temp->value.Trip.fromStat);
        printf("  To:   %d\n",temp->value.Trip.toStat);
        printf("  Duration: %d min, %d secs\n",temp->value.Trip.duration/60,temp->value.Trip.duration%60);
      }

    } else if(strcmp(cmd,"bike") == 0) {
      scanf("%d", &tempId);
      temp = AVLSearch(bikes,tempId);
      if(temp==NULL) {
        printf("**not found\n");
      } else {
        printf("**Bike %d:\n",temp->value.Bike.BikeID);
        printf("  Trip count: %d\n",temp->value.Bike.numTrips);
      }
    } else if(strcmp(cmd, "find") == 0) {
      double tempLat, tempLon, dist;
      scanf("%lf %lf %lf",&tempLat,&tempLon,&dist);
      printWithinDist(stations,tempLat,tempLon,dist);
    } else if(strcmp(cmd, "route") == 0) {
      double dist;
      scanf("%d %lf",&tempId,&dist);
      AVLNode* target = AVLSearch(trips,tempId);
      if(target == NULL) {
        printf("**not found\n");
      } else {
        int size = 50000;
        int index1 = 0, index2 = 0;
        AVLNode** arr1 = malloc(sizeof(AVLNode*)*size);
        AVLNode** arr2 = malloc(sizeof(AVLNode*)*size);
        double initLat, initLon;
        double finalLat, finalLon;
        initLat = AVLSearch(stations,target->value.Trip.fromStat)->value.Station.lat;
        initLon = AVLSearch(stations,target->value.Trip.fromStat)->value.Station.lon;
        finalLat = AVLSearch(stations,target->value.Trip.toStat)->value.Station.lat;
        finalLon = AVLSearch(stations,target->value.Trip.toStat)->value.Station.lon;

        getListWithin(stations->root,arr1,&index1,initLat,initLon,dist);
        getListWithin(stations->root,arr2,&index2,finalLat,finalLon,dist);
        // arr1[index1++] = AVLSearch(stations,target->value.Trip.fromStat);
        // arr2[index2++] = AVLSearch(stations,target->value.Trip.toStat);

        int count = countTripsWithStations(trips,arr1,arr2,index1,index2);

        printf("** Route: from station #%d to station #%d\n",AVLSearch(stations,target->value.Trip.fromStat)->value.Station.StationID,AVLSearch(stations,target->value.Trip.toStat)->value.Station.StationID);
        printf("** Trip count: %d\n",count);
        printf("** Percentage: %lf%%\n",(count/(double)trips->count)*100.0);
        free(arr1);
        free(arr2);

      }
    }
    else
    {
      printf("**unknown cmd, try again...\n");
    }

    scanf("%s", cmd);
  }

  //
  // done, free memory and return:
  //
  printf("** Freeing memory **\n");


  AVLFree(stations);
  AVLFree(trips);
  AVLFree(bikes);

  printf("** Done **\n");

  return 0;
}

