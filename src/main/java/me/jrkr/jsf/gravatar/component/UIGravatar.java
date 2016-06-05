package me.jrkr.jsf.gravatar.component;

import me.jrkr.jsf.gravatar.common.GravatarImageType;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

@FacesComponent(value = UIGravatar.COMPONENT_TYPE)
public class UIGravatar extends UIComponentBase {

    public static final String COMPONENT_FAMILY = "me.jrkr.uigravatar";
    public static final String COMPONENT_TYPE = "me.jrkr.uigravatar.UIGravatar";
    public static final int GRAVATAR_DEFAULT_SIZE = 80;

    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public String getEmail() {
        return (String) getStateHelper().eval("email");
    }

    public void setEmail(String email) {
        getStateHelper().put("email", email);
    }

    public String getRating() {
        return (String) getStateHelper().eval("rating");
    }

    public void setRating(String rating) {
        getStateHelper().put("rating", rating);
    }

    public GravatarImageType getDefaultImg() {
        return (GravatarImageType) getStateHelper().eval("defaultImg", GravatarImageType.HTTP_404);
    }

    public void setDefaultImg(GravatarImageType defaultImg) {
        getStateHelper().put("defaultImg", defaultImg);
    }

    public int getSize() {
        return (Integer) getStateHelper().eval("size", GRAVATAR_DEFAULT_SIZE);
    }

    public void setSize(int value) {
        getStateHelper().put("size", value);
    }

    public String getTooltip() {
        return (String) getStateHelper().eval("tooltip");
    }

    public void setTooltip(String tooltip) {
        getStateHelper().put("tooltip", tooltip);
    }

    public String getStyleClass() {
        return (String) getStateHelper().eval("styleClass");
    }

    public void setStyleClass(String styleClass) {
        getStateHelper().put("styleClass", styleClass);
    }

    public boolean isDeferred() {
        return (Boolean) getStateHelper().eval("deferred", false);
    }

    public void setDeferred(boolean deferred) {
        getStateHelper().put("deferred", deferred);
    }
}
