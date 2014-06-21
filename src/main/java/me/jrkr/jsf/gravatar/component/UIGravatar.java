package me.jrkr.jsf.gravatar.component;

import com.google.common.base.Joiner;
import com.google.common.hash.Hashing;
import me.jrkr.jsf.gravatar.common.GravatarImageType;
import me.jrkr.jsf.gravatar.common.GravatarRating;
import org.apache.commons.lang.StringUtils;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@FacesComponent(value = UIGravatar.GRAVATAR_COMPONENT)
public class UIGravatar extends HtmlGraphicImage {

    public static final String GRAVATAR_COMPONENT = "me.jrkr.uigravatar";
    public static final String DEFAULT_RENDERER_TYPE = "me.jrkr.uigravatar.renderer";
    public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    public static final String CHAR_TYPE = "UTF-8";
    public static final int GRAVATAR_MAX_SIZE = 512;
    public static final int GRAVATAR_MIN_SIZE = 1;
    public static final int GRAVATAR_DEFAULT_SIZE = 80;

    private String email;
    private String size;
    private String rating;
    private String defaultImg;

    public UIGravatar() {
        setRendererType(DEFAULT_RENDERER_TYPE);
    }

    public String getGravUrl() {
        String emailHash = Hashing.md5().hashString(email.toLowerCase().trim(), Charset.forName(CHAR_TYPE)).toString();
        String params = formatUrlParameters();
        return (new StringBuilder()).append(GRAVATAR_URL).append(emailHash).append(".jpg").append(params).toString();
    }

    private String formatUrlParameters() {
        List params = new ArrayList();

        if (checkFixSizeCheck())
            params.add((new StringBuilder()).append("s=").append(size).toString());
        if (GravatarRating.fromCode(rating) != null)
            params.add((new StringBuilder()).append("r=").append(rating).toString());
        if (StringUtils.isNotBlank(defaultImg))
            params.add((new StringBuilder()).append("d=").append(defaultImg).toString());

        if (params.isEmpty())
            return "";
        else
            return (new StringBuilder()).append("?").append(Joiner.on("&").join(params.iterator())).toString();
    }

    private boolean checkFixSizeCheck() {
        boolean sizeOk = false;

        if (size == null)
            return sizeOk;

        Integer sizeInt;
        try {
            sizeInt = Integer.parseInt(size);
        } catch (NumberFormatException nfe) {
            return sizeOk;
        }

        if (sizeInt.intValue() < GRAVATAR_MIN_SIZE || sizeInt.intValue() > GRAVATAR_MAX_SIZE)
            return false;

        return true;
    }

    public String getEmail() {
        return email != null ? email : returnVeValue(getValueExpression("email"));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating != null ? rating : returnVeValue(getValueExpression("rating"));
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDefaultImg() {
        return defaultImg != null ? defaultImg : returnVeValue(getValueExpression("defaultImg"));
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    private String returnVeValue(ValueExpression ve) {
        return ve != null ? (String) ve.getValue(getELContext()) : null;
    }

    private ELContext getELContext() {
        return FacesContext.getCurrentInstance().getELContext();
    }
}
