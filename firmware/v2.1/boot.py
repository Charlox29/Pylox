import digitalio
import board
import storage
import usb_cdc
import time

output = digitalio.DigitalInOut(board.GP15)
output.direction = digitalio.Direction.OUTPUT
output.value = 1

input = digitalio.DigitalInOut(board.GP8)
input.direction = digitalio.Direction.INPUT
input.pull = digitalio.Pull.DOWN

usb_cdc.enable(console=False, data=True)

led = board.GP25
led = digitalio.DigitalInOut(led)
led.direction = digitalio.Direction.OUTPUT

def blink():
    led.value = 1
    time.sleep(0.075)
    led.value = 0
    time.sleep(0.075)

if not input.value:
    storage.disable_usb_drive()
    storage.remount("/", readonly=False)