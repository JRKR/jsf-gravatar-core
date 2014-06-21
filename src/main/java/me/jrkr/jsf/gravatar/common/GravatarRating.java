// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 19/06/2014 12:30:11 AM
// Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   GravatarRating.java

package me.jrkr.jsf.gravatar.common;

import java.util.Arrays;
import java.util.Iterator;

public enum GravatarRating {

    GENERAL_AUDIENCES("g"),
    PARENTAL_GUIDANCE_SUGGESTED("pg"),
    RESTRICTED("r"),
    XPLICIT("x");

    private final String code;

    private GravatarRating(String code) {
        this.code = code;
    }

    public static GravatarRating fromCode(String code) {
        for (Iterator iterator = Arrays.asList(values()).iterator(); iterator.hasNext(); ) {
            GravatarRating obj = (GravatarRating) iterator.next();
            if (obj.getCode().equalsIgnoreCase(code))
                return obj;
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}