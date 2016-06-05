package me.jrkr.jsf.gravatar.common;

import java.util.Arrays;
import java.util.Iterator;

public enum GravatarImageType {

    GRAVATAR_ICON(""),
    IDENTICON("identicon"),
    MONSTERID("monsterid"),
    WAVATAR("wavatar"),
    HTTP_404("404");

    private final String code;

    GravatarImageType(String code) {
        this.code = code;
    }

    public static GravatarImageType fromCode(String code) {
        for (Iterator iterator = Arrays.asList(values()).iterator(); iterator.hasNext(); ) {
            GravatarImageType obj = (GravatarImageType) iterator.next();
            if (obj.getCode().equalsIgnoreCase(code))
                return obj;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
