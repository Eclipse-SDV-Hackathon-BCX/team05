# Convert and store messages from eCal

Working mode:

## Usage

Install dependencies (see Installation below)

Configure your setting in environment vars or a copy of the .env.dist as .env

Run `python3 ./subscriber.py`

The output that gets sent/stored is in json format:

``` 
{
    "acceleration": 1,
    "longitude": 1,
    "latitude": 1
}"
``` 



## Installation
install needed dependencies
`sudo apt install python3-ecal5`

active env
`python3 -m venv venv/`
`source venv/bin/activate`
`pip3 install -r src/requirements.txt` 



Raststätten Liste

Wolfslake-Ost
[13.008814, 52.686675]

Wolfslake-West
[13.004903, 52.667512]

Autohof Oberkrämer
[13.108898, 52.70811]