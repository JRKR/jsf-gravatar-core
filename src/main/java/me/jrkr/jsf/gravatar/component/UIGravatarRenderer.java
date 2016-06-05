package me.jrkr.jsf.gravatar.component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import java.io.IOException;

@FacesRenderer(rendererType = UIGravatarRenderer.RENDERER_TYPE, componentFamily = UIGravatar.COMPONENT_FAMILY)
public class UIGravatarRenderer extends Renderer {

    public static final String RENDERER_TYPE = "me.jrkr.uigravatar.Renderer";

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        ResponseWriter writer = context.getResponseWriter();
        UIGravatar gravatar = (UIGravatar) component;
        String url = gravatar.getURL(gravatar);

        writer.startElement("img", gravatar);
        writer.writeAttribute("id", gravatar.getClientId(), "id");

        if (gravatar.isDeferred()) {
            writer.writeURIAttribute("data-defer-src", url, "email");
        } else {
            writer.writeURIAttribute("src", url, "email");
        }
        int size = gravatar.getSize();
        writer.writeAttribute("width", size, "size");
        writer.writeAttribute("height", size, "size");

        String styleClass = gravatar.getStyleClass();

        if (styleClass != null) {
            writer.writeAttribute("class", styleClass, "styleClass");
        }

        writer.endElement("img");
    }

}
