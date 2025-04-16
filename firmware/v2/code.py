#coding: utf-8

# LIBRARIES
import digitalio
import board
import time
import busio
import usb_cdc



# PHYSICAL COMPONENTS SETUP
columns = [board.GP17,board.GP18,board.GP19,board.GP20,board.GP21]
rows = [board.GP22,board.GP26,board.GP27]

encoder = {"CLK":board.GP11,"DT":board.GP12,"SW":board.GP13}
clkLast = None
pressTime = 0

led = board.GP25


for column in range(len(columns)):
    columns[column] = digitalio.DigitalInOut(columns[column])
    columns[column].direction = digitalio.Direction.INPUT
    columns[column].pull = digitalio.Pull.DOWN

for row in range(len(rows)):
    rows[row] = digitalio.DigitalInOut(rows[row])
    rows[row].direction = digitalio.Direction.OUTPUT


encoder["CLK"] = digitalio.DigitalInOut(encoder["CLK"])
encoder["CLK"].direction = digitalio.Direction.INPUT

encoder["DT"] = digitalio.DigitalInOut(encoder["DT"])
encoder["DT"].direction = digitalio.Direction.INPUT

encoder["SW"] = digitalio.DigitalInOut(encoder["SW"])
encoder["SW"].direction = digitalio.Direction.INPUT
encoder["SW"].pull = digitalio.Pull.UP


led = digitalio.DigitalInOut(led)
led.direction = digitalio.Direction.OUTPUT



# VARIABLES SETUP
size = len(rows)*len(columns)

states = ["OFF"]*size



# USUALS FUNCTIONS
def millis():
    return time.monotonic() * 1000


def blink():
    led.value = 1
    time.sleep(0.075)
    led.value = 0
    time.sleep(0.075)


def send_message(message):
    usb_cdc.data.write(message.encode('utf-8'))
    usb_cdc.data.write(b'\n')

def receive_message():
    if usb_cdc.data.in_waiting > 0:
        return usb_cdc.data.read(usb_cdc.data.in_waiting).decode('utf-8').strip()
    return None



# LOOP
while True:
    # BUTTONS
    for row in range(len(rows)):
        rows[row].value = 1
        
        for column in range(len(columns)):
            button = row*len(columns)+column
            
            if columns[column].value:
                if states[button] == "OFF":
                    states[button] = "ON"
                    
                    print("BUTTON " + str(button+1))
                    
                    send_message("BUTTON " + str(button+1))
            
            else:
                states[row*len(columns)+column] = "OFF"
        
        rows[row].value = 0            
    
    
    # ENCODER ROTATION
    clkState = encoder["CLK"].value
    
    if clkLast != clkState and clkLast != None:
        if clkState == 0:
            if encoder["DT"].value != clkState:
                print("ENCODER CLOCKWISE")
                
                send_message("ENCODER CLOCKWISE")
            
            else:
                print("ENCODER ANTI CLOCKWISE")
                
                send_message("ENCODER ANTI CLOCKWISE")
    
    clkLast = clkState
    
    
    # ENCODER BUTTON
    if not encoder["SW"].value:
        if pressTime == 0:
            pressTime = millis()
    
    elif pressTime != 0:
        if (millis() - pressTime) < 1000:
            print("ENCODER PRESS")
            
            send_message("ENCODER PRESS")
        
        else:
            print("ENCODER LONG PRESS")
            
            send_message("ENCODER LONG PRESS")
        
        pressTime = 0