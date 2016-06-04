package me.jrkr.jsf.gravatar.component;

import me.jrkr.jsf.gravatar.common.GravatarImageType;
import me.jrkr.jsf.gravatar.component.UIGravatar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GravatarUtils {

    public static final int GRAVATAR_DEFAULT_SIZE = 80;
    public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    private GravatarUtils() { }


    public static String getURL(UIGravatar gravatar) {
        return getURL(gravatar.getEmail(), gravatar.getSize(), gravatar.getDefaultImg());
    }

    public static String getURL(String email, int size, String defaultImage) {
        String url = GRAVATAR_URL + getHash(email) + "?d=" + encodeURL(defaultImage);

        if (size != GRAVATAR_DEFAULT_SIZE) {
            url += "&s=" + size;
        }

        return url;
    }

    public static String getHash(String email) {
        try {
            return hex(MessageDigest.getInstance("MD5").digest(email.getBytes("UTF-8")));
        }
        catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private static String encodeURL(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
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
