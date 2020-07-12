package com.ashwin.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UserService {
    private final String URL;

    public UserService(String url) {
        URL = url;
    }

    private void updateData(User user) {
        System.out.println("Updating user...");
        System.out.println("User is up-to-date!");
    }

    public boolean isValid(String str) {
        if (str == null) return false;
        if (str.isEmpty()) return false;
        if (str.trim().isEmpty()) return false;
        if (str.length() < 6) return false;
        return true;
    }

    private boolean validateUser(String username, String password) {
        // Validate user from remote server
        return false;
    }

    private User getUser(String username) {
        // Get user from remote server
        return null;
    }

    private User getUser(String username, String password) {
        if (validateUser(username, password)) {
            return getUser(username);
        }
        return null;
    }

    public long login(String username, String password) {
        if (isValid(username) && isValid(password) && validateUser(username, password)) {
            return username.hashCode();
        }
        return -1;
    }

    public final String getRandomUsername() {
        List<String> usernameList = new ArrayList<String>(Arrays.asList("brown_cookie", "blue_eagle", "black_ninja"));
        Random random = new Random();
        int i = random.nextInt(usernameList.size());
        return usernameList.get(i);
    }

    public String getRandomPassword(int size) {
        Random random = new Random();
        char[] chars = {'Z', 'z', '9'};

        String pwd = "";
        for (int i = 0; i < size; i++) {
            char end = chars[random.nextInt(chars.length)];
            int index = random.nextInt(27);
            char c = (char) (end - index);
            pwd += c;
        }
        return pwd;
    }

    public boolean updateName(String username, String password, String newName) {
        User user = getUser(username, password);
        if (user != null) {
            user.setName(newName);
            updateData(user);
            return true;
        }
        return false;
    }
}
