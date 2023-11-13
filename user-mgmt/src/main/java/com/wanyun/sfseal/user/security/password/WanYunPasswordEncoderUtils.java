/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security.password;

import org.springframework.security.crypto.codec.Utf8;

/**
 * Utility for constant time comparison to prevent against timing attacks.
 *
 * @author WanYun
 */
public class WanYunPasswordEncoderUtils {
    private WanYunPasswordEncoderUtils() {
    }

    /**
     * Constant time comparison to prevent against timing attacks.
     *
     * @param expected
     * @param actual
     * @return
     */
    static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        int expectedLength = expectedBytes == null ? -1 : expectedBytes.length;
        int actualLength = actualBytes == null ? -1 : actualBytes.length;

        int result = expectedLength == actualLength ? 0 : 1;
        for (int i = 0; i < actualLength; i++) {
            byte expectedByte = expectedLength <= 0 ? 0 : expectedBytes[i % expectedLength];
            byte actualByte = actualBytes[i % actualLength];
            result |= expectedByte ^ actualByte;
        }
        return result == 0;
    }

    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return new byte[0];
        }
        // need to check if Utf8.encode() runs in constant time (probably not). This may leak length of string.
        return Utf8.encode(s);
    }
}
