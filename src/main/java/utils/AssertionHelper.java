package utils;

import utilities.Log;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AssertionHelper {

    public static void handlingAssert(Map<String, String> expected, Map<String, String> actual) {
        try {
            assertEquals(expected, actual);
            System.out.println("TEST SUCCESSFULLY COMPLETED.");
        } catch (AssertionError e) {
            Log.error("Arrays has different values " + e.getMessage());
            fail("TEST FAILED: " + e.getMessage());
        }
    }

    public static void handlingAssert(String expected, String actual) {
        try {
            assertEquals(expected, actual);
            System.out.println("TEST SUCCESSFULLY COMPLETED.");
        } catch (AssertionError e) {
            Log.error("Arrays has different values " + e.getMessage());
            fail("TEST FAILED: " + e.getMessage());
        }
    }
}


