import os
from dotenv import load_dotenv


load_dotenv()

broker = os.getenv('broker')
port = int(os.getenv('port'))
topic = os.getenv('topic')
username = os.getenv('username')
password = os.getenv('password')
ecalltopic = os.getenv('ecalltopic')