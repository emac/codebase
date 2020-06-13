/*
 * Created on Jul 8, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package json;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author bishen
 */
public class JsonTest extends TestCase
{

    private Root root;

    @BeforeEach
    public void setUp()
    {
        root = new Root();
        ClassB b = new ClassB();
        root.setChild(b);
    }

    @Test
    public void testToJava()
    {
        String jsonTxt = JSONObject.fromObject(root).toString();

        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(Root.class);

        Root newRoot = (Root) JSONSerializer.toJava(jsonObj, jsonConfig);

        assertTrue(newRoot.getChild() instanceof ClassA);
    }

}
