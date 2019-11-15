package EX0;

import org.junit.Assert;
import org.junit.Test;

public class MainClass {
    private int class_number = 20;
    private String class_string = "Hello, world";

    public int getLocalNumber(){
        return 14;
    }

    public int getClassNumber() {
        return class_number;
    }

    public String getClassString() {
        return class_string;
    }

    public static class MainClassTest extends MainClass{
        @Test
        public void testGetLocalNumber() {
            int expected_value = 14;
            int actual_value = getLocalNumber();
            Assert.assertEquals("getLocalNumber returns unexpected value",expected_value, actual_value);
        }

        @Test
        public void testGetClassNumber() {
            int actual_class_number = getClassNumber();
            Assert.assertTrue("Expected that " + actual_class_number + " is more then 45", getClassNumber() > 45);
        }

        @Test
        public void testGetClassString() {
            String actual_class_string = getClassString();
            Assert.assertTrue(
                    "Expected that " + "'" + actual_class_string+ "'" + " contains 'hello' or 'Hello'",
                    actual_class_string.contains("Hello") | actual_class_string.contains("hello") );
        }
    }
}
