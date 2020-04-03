package utils;

import java.security.SecureRandom;

public class RandomGenerators {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String getRandomString(int length){
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // 0-62 (exclusive), random returns 0-61
            int randomCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char randomChar = DATA_FOR_RANDOM_STRING.charAt(randomCharAt);

            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static long getRandomNumber(int length) {
        return random.nextInt(length) + 100000000;
    }

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    public static String capitalizeFirstLetter(String original) {

        if (original == null || original.length() == 0) {
            System.out.println(original);
            return original;
        }

        return  original.substring(0,1).toUpperCase() + original.substring(1);
    }

    public static String getRandomData(String format, int length) {
        String output = null;

        if (format.equals("capitalizeFirstLetter")) {
            output = capitalizeFirstLetter(getRandomString(length));
        } else if (format.equals("email")) {
            output = getRandomString(length) + "@" + getRandomString(length) + ".com";
        } else if (format.equals("stringNumber")) {
            output = Long.toString(getRandomNumber(999999999));
        } else if (format.equals("random")) {
            output = getRandomString(10);
        }

        return output;
    }

}

