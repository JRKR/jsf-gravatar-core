package me.jrkr.jsf.gravatar.component;

import org.apache.shale.test.base.AbstractJsfTestCase;
import org.apache.shale.test.mock.MockResponseWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

public class UIGravatarRendererTest extends AbstractJsfTestCase {
    private static final String PLAIN_TEST_RESULT = "<img id=\"test\" src=\"//www.gravatar.com/avatar/e733d508cbe602617cb84a1087b612d1?d=mm&amp;s=30\" width=\"30\" height=\"30\"/>";
    private MockResponseWriter writer;
    private UIGravatar gravatar;

    public UIGravatarRendererTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();

        gravatar = new UIGravatar();
        gravatar.setId("test");
        gravatar.setEmail("me@jrkr.me");
        gravatar.setSize(30);
        gravatar.setRendererType(UIGravatarRenderer.RENDERER_TYPE);

        writer = new MockResponseWriter(new StringWriter(), "text/html", null);
        facesContext.setResponseWriter(writer);
        facesContext.getRenderKit().addRenderer(gravatar.getFamily(), gravatar.getRendererType(), new UIGravatarRenderer());
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        gravatar = null;
        writer = null;
    }

    @Test
    public void testEncodeEnd() throws Exception {
        gravatar.encodeEnd(facesContext);
        facesContext.renderResponse();

        String output = writer.getWriter().toString();
        System.out.print(output);
        assertEquals(PLAIN_TEST_RESULT, output);
        assertNotSame("Bye John Smith!", output);
    }

}