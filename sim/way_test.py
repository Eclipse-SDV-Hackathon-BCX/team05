import re
import sys
import time

import ecal.core.core as ecal_core
from ecal.core.publisher import ProtoPublisher
from pykml import parser
import geopy.distance

import telemetrie_pb2

coordinates = []
coordinates_size = 0

STOP_longitude = 13.009171
STOP_latitude = 52.685639
STOP = (STOP_latitude, STOP_longitude)

#[[lat,long,0] for i in range(50)]

cords_speed_arry = None

stopIndex = False
## create array
with open("LKW-Strecke.kml") as file:
    doc = parser.parse(file).getroot().Document
    coordinates_tmp = re.split('\s+', str(doc.Placemark.LineString.coordinates).replace("\n",""))[1:-1]
    coordinates_size = len(coordinates_tmp)
    for coordinate in coordinates_tmp:
        coordinate = coordinate.split(',')[:-1]
        coordinate.append(90)
        coordinates.append(coordinate)
        current = coordinate[1], coordinate[0]
        distance = int(geopy.distance.geodesic(current, STOP).m)
        if ( distance < 100 and not stopIndex):
            coordinates= coordinates + [[STOP_latitude,STOP_longitude,0] for i in range(50)]
            stopIndex = True
            print(coordinates)
        