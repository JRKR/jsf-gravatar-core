package me.jrkr.jsf.gravatar.component;

import me.jrkr.jsf.gravatar.common.GravatarImageType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GravatarUtils {
    private static final int GRAVATAR_DEFAULT_SIZE = 80;
    private static final String GRAVATAR_URL = "//www.gravatar.com/avatar/";
    private static final String UTF8 = "UTF-8";
    private static final String MD5 = "MD5";

    private GravatarUtils() {
    }

    public static String getURL(UIGravatar gravatar) {
        return getURL(gravatar.getEmail(), gravatar.getSize(), gravatar.getDefaultImg());
    }

    private static String getURL(String email, int size, GravatarImageType defaultImage) {
        StringBuilder sb = new StringBuilder(GRAVATAR_URL);
        sb.append(getHash(email));
        sb.append("?d=");
        sb.append(encodeURL(defaultImage));

        if (size != GRAVATAR_DEFAULT_SIZE) {
            sb.append("&s=");
            sb.append(size);
        }

        return sb.toString();
    }

    private static String getHash(String email) {
        try {
            return hex(MessageDigest.getInstance(MD5).digest(email.getBytes(UTF8)));
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static String encodeURL(GravatarImageType git) {
        try {
            return URLEncoder.encode(git.getCode(), UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static String hex(byte[] array) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte element : array) {
            hexBuilder.append(Integer.toHexString((element & 0xFF) | 0x100).substring(1, 3));
        }
        return hexBuilder.toString();
    }
}
