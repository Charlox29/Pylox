package utils;

import java.io.*;

public class Data {
    /**
     * Save an Object in a file.
     */
    public static void save(Object pO, String pPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(pPath))) {
            oos.writeObject(pO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Import an Object from a file.
     */
    public static Object load(String pPath) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(pPath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
