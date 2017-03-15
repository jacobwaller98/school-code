Jacob Waller
The University of Illinois at Chicago
3/14/17

First input is a csv file of divvy stations in Chicago in format:
"id,name,latitude,longitude,dpcapacity,online_date"

Second input is a csv file of trips with Divvy bikes in the format:
"trip_id,starttime,stoptime,bikeid,tripduration,from_station_id,from_station_name,to_station_id,to_station_name,usertype,gender,birthyear"

Next inputs are commands
  stats - Shows statistics about three AVL trees. Number of nodes and height of trees
  station <id> - Shows the Name, location, capacity, and trip count of the station with ID <id>
  trip <id> - Shows the bikeId, originating station ID, destination station ID, and duration of trip with ID <id>
  bike <id> - Shows the number of trips that were taken by the bike with ID <id>
  find <lat> <lon> <dist> - Shows all stations within <dist> miles of <lat> <lon>
  route <id> <dist> - Displays number of routes within <dist> of trip with ID <id>
  exit - Exits program
