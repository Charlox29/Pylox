#coding: utf-8

#https://circuitpython.readthedocs.io/projects/hid/en/latest/api.html

#https://circuitpython.readthedocs.io/projects/hid/en/latest/examples.html#keyboard-shortcuts
#https://circuitpython.readthedocs.io/projects/hid/en/latest/_modules/adafruit_hid/keycode.html



# LIBRARIES
import digitalio
import board
import time
import usb_hid
import busio
import json
import storage
from adafruit_hid.keyboard import Keyboard
from adafruit_hid.mouse import Mouse
from adafruit_hid.consumer_control import ConsumerControl
from adafruit_hid.consumer_control_code import ConsumerControlCode

import library



# PHYSICAL COMPONENTS SETUP
columns = [board.GP17,board.GP18,board.GP19,board.GP20,board.GP21]
raws = [board.GP22,board.GP26,board.GP27]

encoder = {"CLK":board.GP11,"DT":board.GP12,"SW":board.GP13}
clkLast = None
pressTime = 0

led = board.GP25


for column in range(len(columns)):
    columns[column] = digitalio.DigitalInOut(columns[column])
    columns[column].direction = digitalio.Direction.INPUT
    columns[column].pull = digitalio.Pull.DOWN

for raw in range(len(raws)):
    raws[raw] = digitalio.DigitalInOut(raws[raw])
    raws[raw].direction = digitalio.Direction.OUTPUT


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
size = len(raws)*len(columns)

states = ["OFF"]*size



# USUALS FUNCTIONS
def millis():
    return time.monotonic() * 1000


def blink():
    led.value = 1
    time.sleep(0.15)
    led.value = 0
    time.sleep(0.15)


def setupTasks(tasks:list=[]):
    if "LOOP" in tasks:
        tasks.remove("LOOP")
        tasks.insert(0,"LOOP")
    
    for task in tasks[int("LOOP" in tasks):len(tasks)-1]:
        if len(task) != 2:
            task = ["",""]
        
        if task[0] not in ["TEXT", "SHORTCUT","DELAY"]:
            task[0] = "TEXT"
        
        if task[0] == "TEXT" and type(task[1]) != str:
            task[1] = ""
        
        if task[0] == "SHORTCUT" and type(task[1]) != list:
            task[1] = []
        
        if task[0] == "DELAY" and type(task[1]) != int:
            task[1] = 0
    
    return tasks


def shortcut(listOfFunctions:list=[]):
    for function in listOfFunctions:
        if hidKeycodes.keyToHidKeycode(function) is not None:
            keyboard.press(hidKeycodes.keyToHidKeycode(function))
        
        if hidConsumerControlCodes.codeToHidConsumerControlCode(function) is not None:
            consumerControl.press(hidConsumerControlCodes.codeToHidConsumerControlCode(function))
    
    keyboard.release_all()
    consumerControl.release()


def doTasks(tasks:list=[]):
    for task in tasks:
        if task[0] == "TEXT":
            capsLockWas = False
            
            if keyboard.led_on(Keyboard.LED_CAPS_LOCK):
                keyboard.send(hidKeycodes.keyToHidKeycode("CAPS LOCK"))
                capsLockWas = True
            
            try:
                layout.write(str(task[1]))
            except:
                pass
            
            if capsLockWas == True:
                keyboard.send(hidKeycodes.keyToHidKeycode("CAPS LOCK"))
        
        elif task[0] == "SHORTCUT":
            shortcut(task[1])
        
        elif task[0] == "DELAY":
            time.sleep(task[1]/1000)



# DATAS SETUP & LOAD
datas = {}

try:
    with open("datas.json", "r") as json_file:
        datas = json.load(json_file)
except:
    pass

for data in datas:
    if data not in ["MACROS","LANGAGE","ENCODER"]:
        datas.pop(data, None)


if "MACROS" not in datas or type(datas["MACROS"]) != list:
    datas["MACROS"] = []

if len(datas["MACROS"]) > size:
    datas["MACROS"] = datas["MACROS"][0:size-1]

elif len(datas["MACROS"]) < size:
    datas["MACROS"].extend([[]]*(size-len(datas["MACROS"])))

for macro in datas["MACROS"]:
    if type(macro) != list:
        macro = []
    
    macro = setupTasks(macro)


if "LANGAGE" not in datas or type(datas["LANGAGE"]) != str:
    datas["LANGAGE"] = "BR"


if "ENCODER" not in datas or type(datas["ENCODER"]) != dict:
    datas["ENCODER"] = {}

for data in datas["ENCODER"]:
    if data not in ["CLOCKWISE","ANTI CLOCKWISE","PRESS","LONG PRESS"]:
        datas["ENCODER"].pop(data, None)

if "CLOCKWISE" not in datas["ENCODER"] or type(datas["ENCODER"]["CLOCKWISE"]) != list:
    datas["ENCODER"]["CLOCKWISE"] = []

if "ANTI CLOCKWISE" not in datas["ENCODER"] or type(datas["ENCODER"]["ANTI CLOCKWISE"]) != list:
    datas["ENCODER"]["ANTI CLOCKWISE"] = []

if "PRESS" not in datas["ENCODER"] or type(datas["ENCODER"]["PRESS"]) != list:
    datas["ENCODER"]["PRESS"] = []

if "LONG PRESS" not in datas["ENCODER"] or type(datas["ENCODER"]["LONG PRESS"]) != list:
    datas["ENCODER"]["LONG PRESS"] = []

for encoderTask in datas["ENCODER"]:
    datas["ENCODER"][encoderTask] = setupTasks(datas["ENCODER"][encoderTask])


try:
    with open("datas.json", "w") as json_file:
        json.dump(datas, json_file)
except:
    pass



# VIRTUAL COMPONENTS SETUP
mouse = Mouse(usb_hid.devices)
keyboard = Keyboard(usb_hid.devices)
consumerControl = ConsumerControl(usb_hid.devices)


layout = library.importKeyboardLayout(langage=datas["LANGAGE"], keyboard=keyboard)
keycode = library.importKeycode(langage=datas["LANGAGE"])


hidKeycodes = library.hidKeycodes(keycode)
hidConsumerControlCodes = library.hidConsumerControlCodes(ConsumerControlCode)



print(datas)

# LOOP
while True:
    # BUTTONS
    for raw in range(len(raws)):
        raws[raw].value = 1
        
        for column in range(len(columns)):
            button = raw*len(columns)+column
            
            if columns[column].value:
                if states[button] == "OFF":
                    states[button] = "ON"
                    
                    print("BUTTON " + str(button+1) + " > " + str(datas["MACROS"][button]))
                    
                    doTasks(datas["MACROS"][button])
                    
                    if "LOOP" in datas["MACROS"][button]:
                        while columns[column].value:
                            doTasks(datas["MACROS"][button])
                        while not columns[column].value:
                            doTasks(datas["MACROS"][button])
            
            else:
                states[raw*len(columns)+column] = "OFF"
        
        raws[raw].value = 0            
    
    
    # ENCODER ROTATION
    clkState = encoder["CLK"].value
    
    if clkLast != clkState and clkLast != None:
        if encoder["DT"].value != clkState:
            print("ENCODER CLOCKWISE > " + str(datas["ENCODER"]["CLOCKWISE"]))
            
            doTasks(datas["ENCODER"]["CLOCKWISE"])
        
        else:
            print("ENCODER ANTI CLOCKWISE > " + str(datas["ENCODER"]["ANTI CLOCKWISE"]))
            
            doTasks(datas["ENCODER"]["ANTI CLOCKWISE"])
    
    clkLast = clkState
    
    
    # ENCODER BUTTON
    if not encoder["SW"].value:
        if pressTime == 0:
            pressTime = millis()
    
    elif pressTime != 0:
        if (millis() - pressTime) < 1000:
            print("ENCODER PRESS > " + str(datas["ENCODER"]["PRESS"]))
            
            doTasks(datas["ENCODER"]["PRESS"])
            
            if "LOOP" in datas["ENCODER"]["PRESS"]:
                while encoder["SW"].value:
                    doTasks(datas["ENCODER"]["PRESS"])
        
        else:
            print("ENCODER LONG PRESS > " + str(datas["ENCODER"]["LONG PRESS"]))
            
            doTasks(datas["ENCODER"]["LONG PRESS"])
            
            if "LOOP" in datas["ENCODER"]["LONG PRESS"]:
                while encoder["SW"].value:
                    doTasks(datas["ENCODER"]["LONG PRESS"])
        
        pressTime = 0