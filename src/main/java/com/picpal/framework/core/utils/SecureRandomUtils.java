package com.picpal.framework.core.utils;

import java.security.SecureRandom;

public class SecureRandomUtils {
    private static final SecureRandom secureRandom = new SecureRandom();

    // 지정된 길이의 난수를 생성하고, 부족한 경우 0으로 채우기 + prefix
    public static String getRandomIntWithPrefix(String prefix, int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        // 최대 자릿수에 해당하는 범위의 난수 생성 (예: length = 5 -> 00000 ~ 99999)
        int maxValue = (int) Math.pow(10, length) - 1;
        int randomInt = secureRandom.nextInt(maxValue + 1);

        // 자리수 맞추기: String.format을 이용하여 빈 자리를 0으로 채움
        String formattedNumber = String.format("%0" + length + "d", randomInt);

        // prefix와 함께 반환
        return prefix + formattedNumber;
    }

    // 임의의 바이트 배열 생성 + prefix
    public static String getRandomBytesWithPrefix(int length, String prefix) {
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        StringBuilder result = new StringBuilder(prefix);
        for (byte b : bytes) {
            result.append(String.format("%02x", b)); // 바이트를 16진수로 변환하여 문자열에 추가
        }
        return result.toString();
    }

    // 랜덤한 문자열 생성 (알파벳 대소문자 및 숫자 조합) + prefix
    public static String getRandomStringWithPrefix(int length, String prefix) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(prefix); // 초기값에 prefix 추가
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return randomString.toString();
    }

}
