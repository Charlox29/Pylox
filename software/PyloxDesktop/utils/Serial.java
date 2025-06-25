package utils;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

import java.util.function.Consumer;

/**
 * @author Charles A
 *
 * @version 06/22/2025
 */
public class Serial {
    private static SerialPort aPort;
    private static InputStream aIn;
    private static OutputStream aOut;

    public static String getPortName() {
        if  (aPort == null) {
            return "NOT CONNECTED";
        }
        return aPort.getSystemPortName();
    }

    public static boolean isOpened() {
        return aPort != null && aPort.isOpen();
    }

    public static boolean open(String portName){
        return open(portName, 9600);
    }

    public static boolean open(String portName, int baudRate) {
        aPort = SerialPort.getCommPort(portName);
        aPort.setComPortParameters(baudRate, 8, 1, SerialPort.NO_PARITY);
        aPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        boolean vIsOpened = aPort.openPort();

        if (vIsOpened) {
            aIn = aPort.getInputStream();
            aOut = aPort.getOutputStream();
        }
        return vIsOpened;
    }

    public static void listen(Consumer<String> pOnMessage) {
        new Thread(() -> {
            try (var vScanner = new java.util.Scanner(aIn, java.nio.charset.StandardCharsets.UTF_8)) {
                while (aPort.isOpen()) {
                    if (vScanner.hasNextLine()) {
                        pOnMessage.accept(vScanner.nextLine());
                    }
                }
            }
        }, "SerialListener").start();
    }

    public static void send(String pMessage) throws Exception {
        aOut.write(pMessage.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        aOut.flush();
    }

    /**
     * Vérifie si des données sont disponibles à la lecture.
     * @return true si des données sont disponibles, false sinon.
     */
    public static boolean available() {
        try {
            return aIn != null && aIn.available() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lit une ligne depuis le port série.
     * @return la ligne lue, ou null si rien n'est disponible.
     */
    public static String read() {
        try {
            if (aIn != null) {
                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = aIn.read()) != -1) {
                    if (c == '\n') break;
                    if (c != '\r') sb.append((char) c);
                }
                if (!sb.isEmpty()) {
                    return sb.toString();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Vide tous les messages en attente dans le flux d'entrée du port série.
     */
    public static void clearInput() {
        try {
            if (aIn != null) {
                while (aIn.available() > 0) {
                    aIn.read();
                }
            }
        } catch (Exception ignored) {
        }
    }

    public static void close() {
        try {
            if (aIn != null) aIn.close();
        } catch (Exception ignored) {}
        try {
            if (aOut != null) aOut.close();
        } catch (Exception ignored) {}
        if (aPort != null) aPort.closePort();
        aIn = null;
        aOut = null;
        aPort = null;
    }

    public static String[] getPorts() {
        SerialPort[] vPorts = SerialPort.getCommPorts();
        String[] vNames = new String[vPorts.length];
        
        for (int i = 0; i < vPorts.length; i++) vNames[i] = vPorts[i].getSystemPortName();

        return vNames;
    }
}