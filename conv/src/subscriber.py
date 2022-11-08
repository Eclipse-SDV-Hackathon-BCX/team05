import sys
import time
import random

import config


import ecal.core.core as ecal_core
from ecal.core.subscriber import ProtoSubscriber
import telemetrie_pb2

from google.protobuf.json_format import MessageToJson
import json
from paho.mqtt import client as mqtt_client
mqttclient = 0 # our global client


def connect_mqtt():
    def on_connect(client, userdata, flags, rc):
        if rc == 0:
            print("Connected to MQTT Broker!")
        else:
            print("Failed to connect, return code %d\n", rc)
    # Set Connecting Client ID
    client = mqtt_client.Client(f'ecal-mqtt-{random.randint(0, 1000)}')
    client.username_pw_set(config.username, config.password)
    client.on_connect = on_connect
    client.connect(config.broker, config.port)
    return client

def run():
   # connect to MQTT
  mqttclient = connect_mqtt()
  mqttclient.loop_start()

  # Callback for receiving messages, pulish
  def callback(topic_name, msg, time):
    mqttclient.publish(config.topic, json.dumps(MessageToJson(msg))) 
    
  # Initialize eCAL
  ecal_core.initialize(sys.argv, "eCal-Subscriber")
  sub = ProtoSubscriber(config.ecalltopic, telemetrie_pb2.Status)
  sub.set_callback(callback)
  
  # Just don't exit
  while ecal_core.ok():
    time.sleep(0.5)
  
  # finalize eCAL API
  ecal_core.finalize()

if __name__ == "__main__":
  run()