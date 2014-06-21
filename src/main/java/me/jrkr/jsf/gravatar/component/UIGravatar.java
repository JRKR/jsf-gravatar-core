package me.jrkr.jsf.gravatar.component;

import com.google.common.base.Joiner;
import com.google.common.hash.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import me.jrkr.jsf.gravatar.common.GravatarImageType;
import me.jrkr.jsf.gravatar.common.GravatarRating;

@FacesComponent(value="me.jrkr.uigravatar")
public class UIGravatar extends HtmlGraphicImage
{

    public UIGravatar()
    {
        setRendererType("me.jrkr.uigravatar.renderer");
    }

    public String getGravUrl()
    {
        String emailHash = Hashing.md5().hashString(email.toLowerCase().trim(), Charset.forName("UTF-8")).toString();
        String params = formatUrlParameters();
        return (new StringBuilder()).append("http://www.gravatar.com/avatar/").append(emailHash).append(".jpg").append(params).toString();
    }

    private String formatUrlParameters()
    {
        List params = new ArrayList();
        if(size != null)
            params.add((new StringBuilder()).append("s=").append(size).toString());
        if(GravatarRating.fromCode(rating) != null)
            params.add((new StringBuilder()).append("r=").append(rating).toString());
        if(GravatarImageType.fromCode(defaultImgType) != null)
            params.add((new StringBuilder()).append("d=").append(defaultImgType).toString());
        if(params.isEmpty())
            return "";
        else
            return (new StringBuilder()).append("?").append(Joiner.on("&").join(params.iterator())).toString();
    }

    public String getEmail()
    {
        return email != null ? email : returnVeValue(getValueExpression("email"));
    }

    public String getRating()
    {
        return rating != null ? rating : returnVeValue(getValueExpression("rating"));
    }

    public String getDefaultImgType()
    {
        return defaultImgType != null ? defaultImgType : returnVeValue(getValueExpression("defaultImgType"));
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public void setDefaultImgType(String defaultImgType)
    {
        this.defaultImgType = defaultImgType;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    private String returnVeValue(ValueExpression ve)
    {
        return ve != null ? (String)ve.getValue(getELContext()) : null;
    }

    private ELContext getELContext()
    {
        return FacesContext.getCurrentInstance().getELContext();
    }

    public static final String DEFAULT_RENDERER_TYPE = "me.jrkr.uigravatar.renderer";
    public static final String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
    private String email;
    private String size;
    private String rating;
    private String defaultImgType;
}
