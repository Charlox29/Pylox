import digitalio
import board
import storage
import usb_cdc

button = digitalio.DigitalInOut(board.GP13)
button.direction = digitalio.Direction.INPUT
button.pull = digitalio.Pull.UP

usb_cdc.enable(console=False, data=True)

if button.value:
    storage.disable_usb_drive()
    storage.remount("/", readonly=False)