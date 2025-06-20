#coding: utf-8

# LIBRARIES
import digitalio
import board
import time
import usb_cdc



# PHYSICAL COMPONENTS SETUP
columns = [board.GP17, board.GP16, board.GP15, board.GP14, board.GP13]
rows = [board.GP7, board.GP8, board.GP9]

for column in range(len(columns)):
    columns[column] = digitalio.DigitalInOut(columns[column])
    columns[column].direction = digitalio.Direction.INPUT
    columns[column].pull = digitalio.Pull.UP

for row in range(len(rows)):
    rows[row] = digitalio.DigitalInOut(rows[row])
    rows[row].direction = digitalio.Direction.INPUT


class Encoder:
    def __init__(self, A, B, SW):
        self.A = digitalio.DigitalInOut(A)
        self.A.direction = digitalio.Direction.INPUT
        self.A.pull = digitalio.Pull.UP
        
        self.B = digitalio.DigitalInOut(B)
        self.B.direction = digitalio.Direction.INPUT
        self.B.pull = digitalio.Pull.UP
        
        self.SW = digitalio.DigitalInOut(SW)
        self.SW.direction = digitalio.Direction.INPUT
        self.SW.pull = digitalio.Pull.UP
        
        self.pressTime = 0
        self.last = None

encoders = [Encoder(board.GP27, board.GP26, board.GP28),
            Encoder(board.GP19, board.GP18, board.GP20),
            Encoder(board.GP5, board.GP4, board.GP3),
            Encoder(board.GP12, board.GP11, board.GP10)]


led = board.GP25
led = digitalio.DigitalInOut(led)
led.direction = digitalio.Direction.OUTPUT



# VARIABLES SETUP
size = len(rows)*len(columns)

last = None


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
        rows[row].direction = digitalio.Direction.OUTPUT
        rows[row].value = 0
        
        for column in range(len(columns)):
            button = row*len(columns)+column
            
            if not columns[column].value:
                if last == None:
                    last = button
                    
                    send_message(f"but.{button}.p")

            else:
                if last == button:
                    last = None
        
        rows[row].value = 1
        rows[row].direction = digitalio.Direction.INPUT
    
    
    # ENCODERS
    for i in range(len(encoders)):
        # ENCODER ROTATION
        current_state_A = encoders[i].A.value
        current_state_B = encoders[i].B.value

        if encoders[i].last is not None:
            last_state_A, last_state_B = encoders[i].last

            if last_state_A != current_state_A:
                if current_state_A != current_state_B:
                    send_message(f"enc.{i}.c")
                else:
                    send_message(f"enc.{i}.ac")

        encoders[i].last = (current_state_A, current_state_B)

        
        # ENCODER BUTTON
        if not encoders[i].SW.value:
            if encoders[i].pressTime == 0:
                encoders[i].pressTime = millis()

        elif encoders[i].pressTime != 0:
            if (millis() - encoders[i].pressTime) < 1000:
                send_message(f"enc.{i}.p")

            else:
                send_message(f"enc.{i}.lp")

            encoders[i].pressTime = 0