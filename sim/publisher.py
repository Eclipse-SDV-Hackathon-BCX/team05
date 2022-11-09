import re
import sys
import time

import ecal.core.core as ecal_core
import geopy.distance
from ecal.core.publisher import ProtoPublisher
from pykml import parser

import telemetrie_pb2

coordinates = []
coordinates_size = 0

STOP_longitude = 13.004903
STOP_latitude = 52.667512
STOP = (STOP_latitude, STOP_longitude)

#[[lat,long,0] for i in range(50)]

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
            coordinates= coordinates + [[STOP_longitude,STOP_latitude,0] for i in range(50)]
            stopIndex = True

if __name__ == "__main__":
    # initialize eCAL API. The name of our Process will be "Python Hello World Publisher"
    ecal_core.initialize(sys.argv, "Python Hello World Publisher")

    # Create a String Publisher that publishes on the topic "hello_world_python_topic"
    pub = ProtoPublisher("hello_world_python_topic", telemetrie_pb2.Status)

    # Create a counter, so something changes in our message
    counter = 0

    # Infinite loop (using ecal_core.ok() will enable us to gracefully shutdown
    # the process from another application)
    while ecal_core.ok():
        status = telemetrie_pb2.Status()
        status.uuid = "391a05ef-471a-442d-bca1-c24882012ce2"
        status.acceleration = 0
        status.longitude = 13.009171
        status.latitude = 52.685639
        print("Sending Standing Truck 1: {}".format(status))
        pub.send(status)

        generated_status = coordinates[counter%coordinates_size]
        status = telemetrie_pb2.Status()
        status.uuid = "a593ff4c-562c-4f95-a5be-a6de91e13776"
        status.acceleration = int(generated_status[2])
        status.longitude = float(generated_status[0])
        status.latitude = float(generated_status[1])
        print("Sending Driving Truck 2: {}".format(status))
        pub.send(status)

        # Sleep 500 ms
        time.sleep(0.5)

        counter = counter + 1

    # finalize eCAL API
    ecal_core.finalize()
