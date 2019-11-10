package org.itstep;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RegisteredUsers {
    private static volatile RegisteredUsers instance;
    private List<User> userList;
    private static final Object lock = new Object();

    public static RegisteredUsers getInstance() {
        RegisteredUsers r = instance;
        if (r == null) {
            synchronized (lock) {
                r = instance;
                if (r == null) {
                    r = new RegisteredUsers();
                    instance = r;
                }
            }
        }
        return r;
    }

    private RegisteredUsers() {
        userList = Collections.synchronizedList(new LinkedList<>());
    }

    public void add(User user) {
        userList.add(user);
    }

    public List<User> list(File file) {
        if (file.exists() && file.length() > 0) {
            try (InputStream inputStream = new FileInputStream(file)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                try {
                    userList = (List<User>) objectInputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }
}
