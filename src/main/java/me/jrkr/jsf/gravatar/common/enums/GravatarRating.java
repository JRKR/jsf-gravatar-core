// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 19/06/2014 12:30:11 AM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   GravatarRating.java

package me.jrkr.jsf.gravatar.common.enums;

import java.util.Arrays;
import java.util.Iterator;

/**
 * g: suitable for display on all websites with any audience type.
 * pg: may contain rude gestures, provocatively dressed individuals, the lesser swear words, or mild violence.
 * r: may contain such things as harsh profanity, intense violence, nudity, or hard drug use.
 * x: may contain hardcore sexual imagery or extremely disturbing violence.
 */
public enum GravatarRating {
    GENERAL_AUDIENCES("g"),
    PARENTAL_GUIDANCE_SUGGESTED("pg"),
    RESTRICTED("r"),
    XPLICIT("x");

    private final String code;

    GravatarRating(String code) {
        this.code = code;
    }

    /**
     * Default value returned for any gravatar value is general viewing.
     *
     * @param code
     */
    public static GravatarRating fromCode(String code) {
        for (Iterator iterator = Arrays.asList(values()).iterator(); iterator.hasNext(); ) {
            GravatarRating obj = (GravatarRating) iterator.next();
            if (obj.getCode().equalsIgnoreCase(code))
                return obj;
        }
        return getDefaultRating();
    }

    public String getCode() {
        return code;
    }

    public static GravatarRating getDefaultRating() {
        return GENERAL_AUDIENCES;
    }
}