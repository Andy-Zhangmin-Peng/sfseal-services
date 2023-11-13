
/*
 * Copyright 2020 WanYun Corporation. All Rights Reserved.
 */
package com.wanyun.sfseal.user.security.password;

import com.wanyun.sfseal.commonutils.common.util.CommonUtil;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Base64;

/**
 * Password Encoder class
 *
 * @author WanYun
 */
public class WanYunPasswordEncoder implements PasswordEncoder {
    public static final String PREFIX = "{";
    public static final String SUFFIX = "}";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";
    private boolean encodeHashAsBase64;
    private String salt;
    private WanYunDigester digester;

    public WanYunPasswordEncoder() {
        super();
    }

    /**
     * The digest algorithm to use Supports the named
     * <a href="http://java.sun.com/j2se/1.4.2/docs/guide/security/CryptoSpec.html#AppA">
     * Message Digest Algorithms</a> in the Java environment.
     *
     * @param algorithm
     * @param salt
     */
    public WanYunPasswordEncoder(String algorithm, String salt) {
        this.salt = salt;
        this.digester = new WanYunDigester(algorithm, 1);
    }

    /**
     * Use SHA-1 by default
     *
     * @param salt
     */
    public WanYunPasswordEncoder(String salt) {
        this.salt = salt;
        this.digester = new WanYunDigester(SHA_1, 1);
    }

    public void setEncodeHashAsBase64(boolean encodeHashAsBase64) {
        this.encodeHashAsBase64 = encodeHashAsBase64;
    }

    /**
     * Encodes the rawPass using a MessageDigest. If a salt is specified it will be merged
     * with the password before encoding.
     *
     * @param rawPassword The plain text password
     * @return Hex string of password digest (or base64 encoded string if
     * encodeHashAsBase64 is enabled. {salt} is added as the prefix.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        String salt = PREFIX + this.salt + SUFFIX;
        return digest(salt, rawPassword);
    }

    /**
     * To encode a raw password with returning the pure password only, without salt being
     * added on top as the prefix
     *
     * @param rawPassword
     * @return
     */
    public String encodePurePassword(CharSequence rawPassword) {
        String salt = PREFIX + this.salt + SUFFIX;
        return digestPurePassword(salt, rawPassword);
    }

    private String digest(String salt, CharSequence rawPassword) {
        String saltedPassword = rawPassword + salt;

        byte[] digest = this.digester.digest(Utf8.encode(saltedPassword));
        String encoded = encode(digest);
        return salt + encoded;
    }

    private String digestPurePassword(String salt, CharSequence rawPassword) {
        String saltedPassword = rawPassword + salt;

        byte[] digest = this.digester.digest(Utf8.encode(saltedPassword));
        return encode(digest);
    }

    private String encode(byte[] digest) {
        if (this.encodeHashAsBase64) {
            return Utf8.decode(Base64.getEncoder().encode(digest));
        } else {
            return new String(Hex.encode(digest));
        }
    }

    /**
     * Takes a previously encoded password and compares it with a rawpassword after mixing
     * in the salt and encoding that value
     *
     * @param rawPassword     plain text password
     * @param encodedPassword previously encoded password
     * @return true or false
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encodeSecret = "";
        if (!CommonUtil.isStringEmpty(String.valueOf(rawPassword), true)) {
            //create password encoder
            encodeSecret = this.encodePurePassword(String.valueOf(rawPassword));
        } else {
            encodeSecret = this.encode(String.valueOf(rawPassword));
        }
        return WanYunPasswordEncoderUtils.equals(encodedPassword, encodeSecret);
    }

    /**
     * Sets the number of iterations for which the calculated hash value should be
     * "stretched". If this is greater than one, the initial digest is calculated, the
     * digest function will be called repeatedly on the result for the additional number
     * of iterations.
     *
     * @param iterations the number of iterations which will be executed on the hashed
     *                   password/salt value. Defaults to 1.
     */
    public void setIterations(int iterations) {
        this.digester.setIterations(iterations);
    }

    @SuppressWarnings("unused")
    private String extractSalt(String prefixEncodedPassword) {
        int start = prefixEncodedPassword.indexOf(PREFIX);
        if (start != 0) {
            return "";
        }
        int end = prefixEncodedPassword.indexOf(SUFFIX, start);
        if (end < 0) {
            return "";
        }
        return prefixEncodedPassword.substring(start, end + 1);
    }
}
