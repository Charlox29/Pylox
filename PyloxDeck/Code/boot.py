import digitalio
import board
import storage


button = digitalio.DigitalInOut(board.GP13)
button.direction = digitalio.Direction.INPUT
button.pull = digitalio.Pull.UP


if button.value:
    storage.disable_usb_drive()