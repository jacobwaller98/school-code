/*avl.h*/

//
// AVL Tree ADT header file.
//
// Jacob Waller
// 673978936
// U. of Illinois, Chicago
// CS251, Spring 2017
//

// make sure this header file is #include'ed exactly once:
#pragma once

typedef int  AVLKey;

typedef struct STATION {
  int  StationID;
  char name[256];
  double lat, lon;
  char date[32];
  int numTrips, capacity;
} STATION;

typedef struct TRIP {
  int  TripID;
  char startTime[64],stopTime[64];
  int bike, duration, fromStat, toStat;
  char fromStatName[64],toStatName[64];
} TRIP;

typedef struct BIKE {
  int  BikeID;
  int  numTrips;
} BIKE;

enum UNIONTYPE {
  STATIONTYPE,
  TRIPTYPE,
  BIKETYPE
};

typedef struct AVLValue {
  enum UNIONTYPE Type;  // Station, Trip, or Bike:
  union {
    STATION  Station;
    TRIP     Trip;
    BIKE     Bike;
  };
} AVLValue;

typedef struct AVLNode {
  AVLKey    key;
  AVLValue value;
  struct AVLNode  *Left;
  struct AVLNode  *Right;
  int       height;
} AVLNode;

typedef struct AVL {
  AVLNode *root;
  int      count;
} AVL;

AVL *AVLCreate();
void AVLFree(AVL *tree);
int AVLCompareKeys(AVLKey key1, AVLKey key2);
AVLNode *AVLSearch(AVL *tree, AVLKey key);
int AVLInsert(AVL *tree, AVLKey key, AVLValue value);
int AVLCount(AVL *tree);
int AVLHeight(AVL *tree);
