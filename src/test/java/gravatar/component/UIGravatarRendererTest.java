package gravatar.component;


import me.jrkr.jsf.gravatar.component.UIGravatar;
import me.jrkr.jsf.gravatar.component.UIGravatarRenderer;
import org.apache.shale.test.base.AbstractJsfTestCase;
import org.apache.shale.test.mock.MockRenderKitFactory;
import org.apache.shale.test.mock.MockResponseWriter;

import java.io.StringWriter;

public class UIGravatarRendererTest extends AbstractJsfTestCase
{
    private static final String PLAIN_TEST_RESULT = "<img src=\"http://www.gravatar.com/avatar/e733d508cbe602617cb84a1087b612d1.jpg\"/>";
    private MockResponseWriter writer ;
    private UIGravatar gravatar;

    public UIGravatarRendererTest(String name)
    {
        super(name);
    }

    public void setUp() throws Exception
    {
        super.setUp();

        gravatar = new UIGravatar();
        gravatar.setEmail("me@jrkr.me");

        writer = new MockResponseWriter(new StringWriter(), "text/html", null);
        facesContext.setResponseWriter(writer);
        facesContext.getRenderKit().addRenderer(gravatar.getFamily(), gravatar.getRendererType(), new UIGravatarRenderer());
    }

    public void tearDown() throws Exception
    {
        super.tearDown();
        gravatar = null;
        writer = null;
    }

    public void testEncodeEnd() throws Exception
    {
        gravatar.encodeEnd(facesContext);
        facesContext.renderResponse();

        String output = writer.getWriter().toString();
        System.out.print(output);
        assertEquals(PLAIN_TEST_RESULT, output);
        assertNotSame("Bye John Smith!", output);
    }

}
