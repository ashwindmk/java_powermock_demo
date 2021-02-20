package com.ashwin.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class, UserUtil.class})
public class UserUtilTest {
    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Mock
    String url;

    // Mocking private method
    @Test
    public void loginTest() {
        String username = "ashwin";
        String password = "pass123";

        UserService service = new UserService(url);
        UserService userService = PowerMockito.spy(service);
        try {
            PowerMockito.doReturn(true).when(userService,"validateUser", username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserUtil userUtil = new UserUtil(userService);

        assertEquals(true, userUtil.login(username, password));
    }

    // Mocking static method
    @Test
    public void getAgeTest() {
        UserService userService = Mockito.mock(UserService.class);

        UserUtil userUtil = new UserUtil(userService);

        int currentYear = 2020;
        PowerMockito.mockStatic(UserUtil.class);
        PowerMockito.when(UserUtil.currentYear()).thenReturn(currentYear);

        int birthyear = 1989;
        assertEquals(currentYear - birthyear, userUtil.getAge(birthyear));

        birthyear = 1994;
        assertEquals(currentYear - birthyear, userUtil.getAge(birthyear));
    }

    // Mocking private static method
    @Test
    public void testFullName() {
        String first = "John";
        String last = "Doe";
        PowerMockito.spy(UserService.class);
        try {
            PowerMockito.when(UserService.class, "copy", first).thenReturn(first);
            PowerMockito.when(UserService.class, "copy", last).thenReturn(last);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String expected = first + " " + last;
        String actual = UserService.getFullName(first, last);
        assertEquals(expected, actual);
    }

    // Mocking final method
    @Test
    public void getRandomUsername() {
        UserService userService = PowerMockito.mock(UserService.class);
        UserUtil userUtil = new UserUtil(userService);

        final String pre = "red";
        final String name = "skull";

        PowerMockito.when(userService.getRandomUsername()).thenReturn(name);

        int count = 0;

        count++;
        assertEquals(pre + "_" + name, userUtil.getRandomUsername(pre));

        count++;
        assertNotEquals("_" + name, userUtil.getRandomUsername(pre));

        Mockito.verify(userService, Mockito.times(count)).getRandomUsername();
    }
}
