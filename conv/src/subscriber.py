import sys
import time

import ecal.core.core as ecal_core
from ecal.core.subscriber import ProtoSubscriber

import telemetrie_pb2

# Callback for receiving messages
def callback(topic_name):
  print("Received: {}".topic_name)

if __name__ == "__main__":
  # Initialize eCAL
  ecal_core.initialize(sys.argv, "eCal-Subscriber")

  sub = ProtoSubscriber("BrakeInPBTopic", telemetrie_pb2.Status)

  sub.set_callback(callback)
  
  # Just don't exit
  while ecal_core.ok():
    time.sleep(0.5)
  
  # finalize eCAL API
  ecal_core.finalize()
