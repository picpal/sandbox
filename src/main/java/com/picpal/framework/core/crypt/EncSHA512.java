package com.picpal.framework.core.crypt;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class EncSHA512 implements PasswordEncoder {
    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }

        return this.getSHA512password(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        }
        if (encodedPassword == null || encodedPassword.length() == 0) {
            return false;
        }

        String encodedRawPassword = this.getSHA512password(rawPassword);
        return encodedRawPassword.equals(encodedPassword);
    }


    private String getSHA512password(CharSequence rawPassword) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(rawPassword.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] msgb = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msgb.length; i++) {
            String tmp = Integer.toHexString(msgb[i] & 0xFF);
            while (tmp.length() < 2) {
                tmp = "0" + tmp;
            }
            sb.append(tmp.substring(tmp.length() - 2));
        }

        return sb.toString();
    }

}
