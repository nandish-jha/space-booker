package com.space_booker.model;

import java.io.*;

public class SerializationModule implements Serializable {
    public static void SaveUsers(User[] userInfo, String filepath) {
        try{
            ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(filepath));
            serializer.writeObject(userInfo);
        }
        catch (IOException ignored) {}
    }

    public static User[] LoadUsers(String filepath) {
        ObjectInputStream in;
        try{
            in = new ObjectInputStream(new FileInputStream(filepath));
        } catch (IOException e) {
            return new User[0];
        }

        try{
            return (User[]) in.readObject();
        }
        catch (ClassNotFoundException | IOException ignored) {
            return new User[0];
        }
    }

    public static void SaveBookings(Booking[] userInfo, String filepath) {
        try{
            ObjectOutputStream serializer = new ObjectOutputStream(new FileOutputStream(filepath));
            serializer.writeObject(userInfo);
        }
        catch (IOException ignored) {}
    }

    public static Booking[] LoadBookings(String filepath) {
        ObjectInputStream in;
        try{
            in = new ObjectInputStream(new FileInputStream(filepath));
        } catch (IOException e) {
            return new Booking[0];
        }

        try{
            return (Booking[]) in.readObject();
        }
        catch (ClassNotFoundException | IOException ignored) {
            return new Booking[0];
        }
    }
}
