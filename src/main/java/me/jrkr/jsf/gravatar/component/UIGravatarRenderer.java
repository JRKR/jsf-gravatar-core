package me.jrkr.jsf.gravatar.component;

import com.sun.faces.renderkit.*;
import com.sun.faces.renderkit.html_basic.ImageRenderer;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

import org.apache.commons.lang.StringUtils;

@FacesRenderer(rendererType="me.jrkr.uigravatar.renderer", componentFamily="javax.faces.Graphic")
public class UIGravatarRenderer extends ImageRenderer
{
    private static final Attribute ATTRIBUTES[] = AttributeManager.getAttributes(com.sun.faces.renderkit.AttributeManager.Key.GRAPHICIMAGE);

    public UIGravatarRenderer()
    {
    }

    public void encodeBegin(FacesContext context, UIComponent component)
            throws IOException
    {
        rendererParamsNotNull(context, component);
    }

    public void encodeEnd(FacesContext context, UIComponent component)
            throws IOException
    {
        rendererParamsNotNull(context, component);
        if(!shouldEncode(component))
            return;

        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);

        writer.startElement("img", component);
        writeIdAttributeIfNecessary(context, writer, component);
        setupGravatarDetails(component);
        writer.writeURIAttribute("src", (String)component.getAttributes().get("email"), "email");
        if(writer.getContentType().equals("application/xhtml+xml") && null == component.getAttributes().get("alt"))
            writer.writeAttribute("alt", "", "alt");
        RenderKitUtils.renderPassThruAttributes(context, writer, component, ATTRIBUTES);
        RenderKitUtils.renderXHTMLStyleBooleanAttributes(writer, component);
        String styleClass;
        if(null != (styleClass = (String)component.getAttributes().get("styleClass")))
            writer.writeAttribute("class", styleClass, "styleClass");
        writer.endElement("img");
        if(logger.isLoggable(Level.FINER))
            logger.log(Level.FINER, (new StringBuilder()).append("End encoding component ").append(component.getId()).toString());
    }

    private void setupGravatarDetails(UIComponent component)
    {
        Map attributes = component.getAttributes();
        UIGravatar gravObj = new UIGravatar();
        if(StringUtils.isNotBlank((String)attributes.get("email")))
            gravObj.setEmail((String)attributes.get("email"));
        if(StringUtils.isNotBlank((String)attributes.get("size")))
            gravObj.setSize((String)attributes.get("size"));
        if(StringUtils.isNotBlank((String)attributes.get("rating")))
            gravObj.setRating((String)attributes.get("rating"));
        if(StringUtils.isNotBlank((String)attributes.get("defaultImgType")))
            gravObj.setDefaultImgType((String)attributes.get("defaultImgType"));
        attributes.put("email", gravObj.getGravUrl());
    }

    }
