def importKeyboardLayout(langage:str, keyboard):
    langage = langage.upper()

    if langage == "BR":
        import keyboard_layout_win_br as keyboard_layout

    elif langage == "CZ":
        import keyboard_layout_win_cz as keyboard_layout

    elif langage == "CZ1":
        import keyboard_layout_win_cz1 as keyboard_layout

    elif langage == "DA":
        import keyboard_layout_win_da as keyboard_layout

    elif langage == "DE":
        import keyboard_layout_win_de as keyboard_layout

    elif langage == "ES":
        import keyboard_layout_win_es as keyboard_layout

    elif langage == "FR":
        import keyboard_layout_win_fr as keyboard_layout

    elif langage == "HU":
        import keyboard_layout_win_hu as keyboard_layout

    elif langage == "IT":
        import keyboard_layout_win_it as keyboard_layout
    
    elif langage == "PO":
        import keyboard_layout_win_po as keyboard_layout
    
    elif langage == "SW":
        import keyboard_layout_win_sw as keyboard_layout

    elif langage == "TR":
        import keyboard_layout_win_tr as keyboard_layout

    else:
        import keyboard_layout_win_br as keyboard_layout

    return keyboard_layout.KeyboardLayout(keyboard)


def importKeycode(langage:str):
    langage = langage.upper()

    if langage == "BR":
        import keycode_win_br as keycode

    elif langage == "CZ":
        import keycode_win_cz as keycode

    elif langage == "CZ1":
        import keycode_win_cz1 as keycode

    elif langage == "DA":
        import keycode_win_da as keycode

    elif langage == "DE":
        import keycode_win_de as keycode

    elif langage == "ES":
        import keycode_win_es as keycode

    elif langage == "FR":
        import keycode_win_fr as keycode

    elif langage == "HU":
        import keycode_win_hu as keycode

    elif langage == "IT":
        import keycode_win_it as keycode
    
    elif langage == "PO":
        import keycode_win_po as keycode
    
    elif langage == "SW":
        import keycode_win_sw as keycode

    elif langage == "TR":
        import keycode_win_tr as keycode

    else:
        import keycode_win_br as keycode

    return keycode.Keycode



class hidConsumerControlCodes:
    def __init__(self, ConsumerControlCode):
        self._ConsumerControlCode = ConsumerControlCode
    
    def codeToHidConsumerControlCode(self, code):
        code = code.upper()

        consumerControlCodes = {
            "PREVIOUS":self._ConsumerControlCode.SCAN_PREVIOUS_TRACK,
            "NEXT":self._ConsumerControlCode.SCAN_NEXT_TRACK,
            "STOP":self._ConsumerControlCode.STOP,
            "PLAY / PAUSE":self._ConsumerControlCode.PLAY_PAUSE,
            "MUTE":self._ConsumerControlCode.MUTE,
            "VOLUME INCREMENT":self._ConsumerControlCode.VOLUME_INCREMENT,
            "VOLUME DECREMENT":self._ConsumerControlCode.VOLUME_DECREMENT,
            "BRIGHTNESS INCREMENT":self._ConsumerControlCode.BRIGHTNESS_INCREMENT,
            "BRIGHTNESS DECREMENT":self._ConsumerControlCode.BRIGHTNESS_DECREMENT
        }

        if code in consumerControlCodes:
            return consumerControlCodes[code]
        else:
            return None


class hidKeycodes:
    def __init__(self, Keycode):
        self._Keycode = Keycode
    
    def keyToHidKeycode(self, key:str):
        key = key.upper()

        keycode = {
            "BACK SPACE":self._Keycode.BACKSPACE,
            "TAB":self._Keycode.TAB,
            
            "RETURN":self._Keycode.RETURN,

            "RIGHT SHIFT":self._Keycode.RIGHT_SHIFT,
            "LEFT SHIFT":self._Keycode.LEFT_SHIFT,
            "RIGHT CTRL":self._Keycode.RIGHT_CONTROL,
            "LEFT CTRL":self._Keycode.LEFT_CONTROL,
            "RIGHT ALT":self._Keycode.RIGHT_ALT,
            "LEFT ALT":self._Keycode.LEFT_ALT,
            "PAUSE / BREAK":self._Keycode.PAUSE,
            "CAPS LOCK":self._Keycode.CAPS_LOCK,

            "ESCAPE":self._Keycode.ESCAPE,
            
            "SPACE":self._Keycode.SPACE,
            "PAGE UP":self._Keycode.PAGE_UP,
            "PAGE DOWN":self._Keycode.PAGE_DOWN,
            "END":self._Keycode.END,
            "HOME":self._Keycode.HOME,
            "LEFT":self._Keycode.LEFT_ARROW,
            "UP":self._Keycode.UP_ARROW,
            "RIGHT":self._Keycode.RIGHT_ARROW,
            "DOWN":self._Keycode.DOWN_ARROW,

            "PRINT SCREEN":self._Keycode.PRINT_SCREEN,
            "INSERT":self._Keycode.INSERT,
            "DELETE":self._Keycode.DELETE,

            "0":self._Keycode.ZERO,
            "1":self._Keycode.ONE,
            "2":self._Keycode.TWO,
            "3":self._Keycode.THREE,
            "4":self._Keycode.FOUR,
            "5":self._Keycode.FIVE,
            "6":self._Keycode.SIX,
            "7":self._Keycode.SEVEN,
            "8":self._Keycode.EIGHT,
            "9":self._Keycode.NINE,

            "A":self._Keycode.A,
            "B":self._Keycode.B,
            "C":self._Keycode.C,
            "D":self._Keycode.D,
            "E":self._Keycode.E,
            "F":self._Keycode.F,
            "G":self._Keycode.G,
            "H":self._Keycode.H,
            "I":self._Keycode.I,
            "J":self._Keycode.J,
            "K":self._Keycode.K,
            "L":self._Keycode.L,
            "M":self._Keycode.M,
            "N":self._Keycode.N,
            "O":self._Keycode.O,
            "P":self._Keycode.P,
            "Q":self._Keycode.Q,
            "R":self._Keycode.R,
            "S":self._Keycode.S,
            "T":self._Keycode.T,
            "U":self._Keycode.U,
            "V":self._Keycode.V,
            "W":self._Keycode.W,
            "X":self._Keycode.X,
            "Y":self._Keycode.Y,
            "Z":self._Keycode.Z,
            "OS":self._Keycode.GUI,

            "KEYPAD 0":self._Keycode.KEYPAD_ZERO,
            "KEYPAD 1":self._Keycode.KEYPAD_ONE,
            "KEYPAD 2":self._Keycode.KEYPAD_TWO,
            "KEYPAD 3":self._Keycode.KEYPAD_THREE,
            "KEYPAD 4":self._Keycode.KEYPAD_FOUR,
            "KEYPAD 5":self._Keycode.KEYPAD_FIVE,
            "KEYPAD 6":self._Keycode.KEYPAD_SIX,
            "KEYPAD 7":self._Keycode.KEYPAD_SEVEN,
            "KEYPAD 8":self._Keycode.KEYPAD_EIGHT,
            "KEYPAD 9":self._Keycode.KEYPAD_NINE,
            "KEYPAD *":self._Keycode.KEYPAD_ASTERISK,
            "KEYPAD +":self._Keycode.KEYPAD_PLUS,
            "KEYPAD -":self._Keycode.KEYPAD_MINUS,
            "KEYPAD .":self._Keycode.KEYPAD_PERIOD,
            "KEYPAD /":self._Keycode.KEYPAD_FORWARD_SLASH,

            "F1":self._Keycode.F1,
            "F2":self._Keycode.F2,
            "F3":self._Keycode.F3,
            "F4":self._Keycode.F4,
            "F5":self._Keycode.F5,
            "F6":self._Keycode.F6,
            "F7":self._Keycode.F7,
            "F8":self._Keycode.F8,
            "F9":self._Keycode.F9,
            "F10":self._Keycode.F10,
            "F11":self._Keycode.F11,
            "F12":self._Keycode.F12,

            "NUM LOCK":self._Keycode.KEYPAD_NUMLOCK,
            "SCROLL LOCK":self._Keycode.SCROLL_LOCK
        }


        if key in keycode:
            return keycode[key]
        else:
            return None
        




"""

hidConsumerControlCodes = {
    "PREVIOUS":ConsumerControlCode.SCAN_PREVIOUS_TRACK,
    "NEXT":ConsumerControlCode.SCAN_NEXT_TRACK,
    "STOP":ConsumerControlCode.STOP,
    "PLAY / PAUSE":ConsumerControlCode.PLAY_PAUSE,
    "MUTE":ConsumerControlCode.MUTE,
    "VOLUME INCREMENT":ConsumerControlCode.VOLUME_INCREMENT,
    "VOLUME DECREMENT":ConsumerControlCode.VOLUME_DECREMENT,
    "BRIGHTNESS INCREMENT":ConsumerControlCode.BRIGHTNESS_INCREMENT,
    "BRIGHTNESS DECREMENT":ConsumerControlCode.BRIGHTNESS_DECREMENT
}


def codeToHidConsumerControlCode(code:str):
    if code in hidConsumerControlCodes:
        return hidConsumerControlCodes[code]
    else:
        return None



hidKeycodes = {
    "BACK SPACE":keycode.BACKSPACE,
    "TAB":keycode.TAB,
    
    "RETURN":keycode.RETURN,

    "RIGHT SHIFT":keycode.RIGHT_SHIFT,
    "LEFT SHIFT":keycode.LEFT_SHIFT,
    "RIGHT CTRL":keycode.RIGHT_CONTROL,
    "LEFT CTRL":keycode.LEFT_CONTROL,
    "RIGHT ALT":keycode.RIGHT_ALT,
    "LEFT ALT":keycode.LEFT_ALT,
    "PAUSE / BREAK":keycode.PAUSE,
    "CAPS LOCK":keycode.CAPS_LOCK,

    "ESCAPE":keycode.ESCAPE,
    
    "SPACE":keycode.SPACE,
    "PAGE UP":keycode.PAGE_UP,
    "PAGE DOWN":keycode.PAGE_DOWN,
    "END":keycode.END,
    "HOME":keycode.HOME,
    "LEFT":keycode.LEFT_ARROW,
    "UP":keycode.UP_ARROW,
    "RIGHT":keycode.RIGHT_ARROW,
    "DOWN":keycode.DOWN_ARROW,

    "PRINT SCREEN":keycode.PRINT_SCREEN,
    "INSERT":keycode.INSERT,
    "DELETE":keycode.DELETE,

    "0":keycode.ZERO,
    "1":keycode.ONE,
    "2":keycode.TWO,
    "3":keycode.THREE,
    "4":keycode.FOUR,
    "5":keycode.FIVE,
    "6":keycode.SIX,
    "7":keycode.SEVEN,
    "8":keycode.EIGHT,
    "9":keycode.NINE,

    "A":keycode.A,
    "B":keycode.B,
    "C":keycode.C,
    "D":keycode.D,
    "E":keycode.E,
    "F":keycode.F,
    "G":keycode.G,
    "H":keycode.H,
    "I":keycode.I,
    "J":keycode.J,
    "K":keycode.K,
    "L":keycode.L,
    "M":keycode.M,
    "N":keycode.N,
    "O":keycode.O,
    "P":keycode.P,
    "Q":keycode.Q,
    "R":keycode.R,
    "S":keycode.S,
    "T":keycode.T,
    "U":keycode.U,
    "V":keycode.V,
    "W":keycode.W,
    "X":keycode.X,
    "Y":keycode.Y,
    "Z":keycode.Z,
    "OS":keycode.GUI,

    "KEYPAD 0":keycode.KEYPAD_ZERO,
    "KEYPAD 1":keycode.KEYPAD_ONE,
    "KEYPAD 2":keycode.KEYPAD_TWO,
    "KEYPAD 3":keycode.KEYPAD_THREE,
    "KEYPAD 4":keycode.KEYPAD_FOUR,
    "KEYPAD 5":keycode.KEYPAD_FIVE,
    "KEYPAD 6":keycode.KEYPAD_SIX,
    "KEYPAD 7":keycode.KEYPAD_SEVEN,
    "KEYPAD 8":keycode.KEYPAD_EIGHT,
    "KEYPAD 9":keycode.KEYPAD_NINE,
    "KEYPAD *":keycode.KEYPAD_ASTERISK,
    "KEYPAD +":keycode.KEYPAD_PLUS,
    "KEYPAD -":keycode.KEYPAD_MINUS,
    "KEYPAD .":keycode.KEYPAD_PERIOD,
    "KEYPAD /":keycode.KEYPAD_FORWARD_SLASH,

    "F1":keycode.F1,
    "F2":keycode.F2,
    "F3":keycode.F3,
    "F4":keycode.F4,
    "F5":keycode.F5,
    "F6":keycode.F6,
    "F7":keycode.F7,
    "F8":keycode.F8,
    "F9":keycode.F9,
    "F10":keycode.F10,
    "F11":keycode.F11,
    "F12":keycode.F12,

    "NUM LOCK":keycode.KEYPAD_NUMLOCK,
    "SCROLL LOCK":keycode.SCROLL_LOCK
}


def keyToHidKeycode(key:str):
    if key.upper() in hidKeycodes:
        return hidKeycodes[key.upper()]
    else:
        return None

"""

