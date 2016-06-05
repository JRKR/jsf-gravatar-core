package me.jrkr.jsf.gravatar.common.enums;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @url https://en.gravatar.com/site/implement/images/
 * @default is mm as per docs on the gravatar forums
 * 404: do not load any image if none is associated with the email hash, instead return an HTTP 404 (File Not Found) response
 * mm: (mystery-man) a simple, cartoon-style silhouetted outline of a person (does not vary by email hash)
 * identicon: a geometric pattern based on an email hash
 * monsterid: a generated 'monster' with different colors, faces, etc
 * wavatar: generated faces with differing features and backgrounds
 * retro: awesome generated, 8-bit arcade-style pixelated faces
 * blank: a transparent PNG image (border added to HTML below for demonstration purposes)
 */
public enum GravatarImageType {
    GRAVATAR_ICON(""),
    IDENTICON("identicon"),
    MONSTERID("monsterid"),
    WAVATAR("wavatar"),
    RETRO("retro"),
    MM("mm"),
    HTTP_404("404");

    private final String code;

    GravatarImageType(String code) {
        this.code = code;
    }

    /**
     * Default Image Type is 'MM' even for invalid codes.
     *
     * @param code
     */
    public static GravatarImageType fromCode(String code) {
        for (Iterator iterator = Arrays.asList(values()).iterator(); iterator.hasNext(); ) {
            GravatarImageType obj = (GravatarImageType) iterator.next();
            if (obj.getCode().equalsIgnoreCase(code)) {
                return obj;
            }
        }
        return getDefaultImage();
    }

    public String getCode() {
        return code;
    }

    public static GravatarImageType getDefaultImage() {
        return GravatarImageType.MM;
    }
}
