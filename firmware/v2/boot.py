import digitalio
import board
import storage
import usb_cdc
import time

button = digitalio.DigitalInOut(board.GP13)
button.direction = digitalio.Direction.INPUT


usb_cdc.enable(console=False, data=True)

time.sleep(1)

if button.value:
    storage.disable_usb_drive()
    storage.remount("/", readonly=False)