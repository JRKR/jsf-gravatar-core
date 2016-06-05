package me.jrkr.jsf.gravatar.component;

import me.jrkr.jsf.gravatar.common.enums.GravatarImageType;
import me.jrkr.jsf.gravatar.common.enums.GravatarRating;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@FacesComponent(value = UIGravatar.COMPONENT_TYPE)
public class UIGravatar extends UIComponentBase {
    public static final String COMPONENT_FAMILY = "me.jrkr.uigravatar";
    public static final String COMPONENT_TYPE = "me.jrkr.uigravatar.UIGravatar";
    public static final String VAL_EMAIL = "email";
    public static final String VAL_RATING = "rating";
    public static final String VAL_DEFAULT_IMAGE = "defaultImg";
    public static final String VAL_DEFAULT_SIZE = "size";
    public static final String VAL_DEFAULT_TOOLTOP = "tooltip";
    public static final String VAL_DEFAULT_STYLECLASS = "styleClass";
    public static final String VAL_DEFAULT_DEFERRED = "deferred";
    public static final int GRAVATAR_DEFAULT_SIZE = 80;
    public static final String GRAVATAR_URL = "//www.gravatar.com/avatar/";
    public static final String UTF8 = "UTF-8";
    public static final String MD5 = "MD5";

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }


    public String getURL(UIGravatar gravatar) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(GRAVATAR_URL);
        sb.append(getHash(gravatar.getEmail()));
        sb.append("?d=");
        sb.append(encodeURL(gravatar.getDefaultImg()));

        int size = gravatar.getSize();
        if (size != GRAVATAR_DEFAULT_SIZE) {
            sb.append("&s=");
            sb.append(size);
        }
        String rating = gravatar.getRating();

        if (rating != null && !"".equalsIgnoreCase(rating.trim())) {
            sb.append("&r=");
            sb.append(rating);
        }

        return sb.toString();
    }

    private String getHash(String email) {
        try {
            return hex(MessageDigest.getInstance(MD5).digest(email.getBytes(UTF8)));
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException(e);
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    private String encodeURL(String defaultImage) throws UnsupportedEncodingException {
        return URLEncoder.encode(defaultImage, UTF8);
    }

    private String hex(byte[] array) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte element : array) {
            hexBuilder.append(Integer.toHexString((element & 0xFF) | 0x100).substring(1, 3));
        }
        return hexBuilder.toString();
    }

    public String getEmail() {
        return (String) getStateHelper().eval(VAL_EMAIL);
    }

    public void setEmail(String email) {
        getStateHelper().put(VAL_EMAIL, email);
    }

    public String getRating() {
        GravatarRating result = (GravatarRating) getStateHelper().eval(VAL_RATING);
        return result == null ? null : result.getCode();
    }

    public void setRating(String rating) {
        getStateHelper().put(VAL_RATING, GravatarRating.fromCode(rating));
    }

    public String getDefaultImg() {
        return ((GravatarImageType) getStateHelper().eval(VAL_DEFAULT_IMAGE, GravatarImageType.getDefaultImage())).getCode();
    }

    public void setDefaultImg(String defaultImg) {
        getStateHelper().put(VAL_DEFAULT_IMAGE, GravatarImageType.fromCode(defaultImg));
    }

    public int getSize() {
        return (Integer) getStateHelper().eval(VAL_DEFAULT_SIZE, GRAVATAR_DEFAULT_SIZE);
    }

    public void setSize(int value) {
        getStateHelper().put(VAL_DEFAULT_SIZE, value);
    }

    public String getStyleClass() {
        return (String) getStateHelper().eval(VAL_DEFAULT_STYLECLASS);
    }

    public void setStyleClass(String styleClass) {
        getStateHelper().put(VAL_DEFAULT_STYLECLASS, styleClass);
    }

    public boolean isDeferred() {
        return (Boolean) getStateHelper().eval(VAL_DEFAULT_DEFERRED, false);
    }

    public void setDeferred(boolean deferred) {
        getStateHelper().put(VAL_DEFAULT_DEFERRED, deferred);
    }
}
