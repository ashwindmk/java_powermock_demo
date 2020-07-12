package com.ashwin.java;

import java.util.Calendar;

public class UserUtil {
    private UserService userService;

    public UserUtil(UserService userService) {
        this.userService = userService;
    }

    public boolean login(String username, String password) {
        return this.userService.login(username, password) != -1;
    }

    public final String getSampleUsername() {
        return "green_avocado";
    }

    public String getSampleUsername(String pre) {
        return pre + "_avocado";
    }

    public String getRandomUsername() {
        return userService.getRandomUsername();
    }

    public String getRandomUsername(String pre) {
        return pre + "_" + userService.getRandomUsername();
    }

    public String getRandomPassword(int size) {
        return userService.getRandomPassword(size);
    }

    public static int currentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public int getAge(int birthyear) {
        return currentYear() - birthyear;
    }

    public boolean isValid(String str) {
        return userService.isValid(str);
    }

    public boolean updateName(String username, String password, String newName) {
        return userService.updateName(username, password, newName);
    }
}
