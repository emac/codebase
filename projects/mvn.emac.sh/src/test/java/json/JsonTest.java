/*
 * Created on Jul 8, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package json;

import static org.junit.Assert.assertTrue;
import json.ClassB;
import json.Root;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * @author bishen
 */
public class JsonTest extends TestCase
{

    private Root root;

    @Before
    public void setUp()
    {
        this.root = new Root();
        ClassB b = new ClassB();
        this.root.setChild(b);
    }

    @Test
    public void testToJava()
    {
        String jsonTxt = JSONObject.fromObject(this.root).toString();

        JSONObject jsonObj = (JSONObject) JSONSerializer.toJSON(jsonTxt);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setRootClass(Root.class);

        Root newRoot = (Root) JSONSerializer.toJava(jsonObj, jsonConfig);

        assertTrue(newRoot.getChild() instanceof ClassB);
    }

}
