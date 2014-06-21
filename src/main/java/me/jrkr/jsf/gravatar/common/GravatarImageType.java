
package me.jrkr.jsf.gravatar.common;

import java.util.*;

public enum GravatarImageType {

    GRAVATAR_ICON(""),

    IDENTICON("identicon"),

    MONSTERID("monsterid"),

    WAVATAR("wavatar"),

    HTTP_404("404");

    private final String code;

    private GravatarImageType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GravatarImageType fromCode(String code)
    {
        for(Iterator iterator = Arrays.asList(values()).iterator(); iterator.hasNext();)
        {
            GravatarImageType obj = (GravatarImageType)iterator.next();
            if(obj.getCode().equalsIgnoreCase(code))
                return obj;
        }

        return null;
    }
}
