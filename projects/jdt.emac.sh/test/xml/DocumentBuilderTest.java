/*
 * Created on Dec 30, 2010 Copyright (c) eBay, Inc. 2010 All rights reserved.
 */

package xml;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author bishen
 */
public class DocumentBuilderTest
{

//    @Test
    public void testParseRemoteXmlFile()
            throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        URL configLoc = new URL("http://sjc-vts-274.corp.ebay.com:9090/mapping/de-DE/config.xml");
        Document doc = docBuilder.parse(configLoc.openStream());
        Assert.assertNotNull(doc);

        String content = doc.getElementsByTagName("StartContent").item(0).getFirstChild().getNodeValue();
        Assert.assertNotNull(content);
    }

    @Test
    public void testDomRead()
            throws SAXException, IOException, ParserConfigurationException
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(getClass().getResourceAsStream("config.xml"));
        // get root element
        Element root = doc.getDocumentElement();
        // read
        NodeList nodes = root.getElementsByTagName("module");
        Assert.assertTrue(nodes.getLength() > 0);
    }

    @Test
    public void testDomWrite()
            throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError,
            TransformerException, URISyntaxException
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(getClass().getResourceAsStream("config.xml"));
        // get root element
        Element root = doc.getDocumentElement();
        // read & update
        NodeList modules = root.getElementsByTagName("module");
        for (int i = 0; i < modules.getLength(); i++)
        {
            Element elm = (Element) modules.item(i);
            String name = elm.getAttribute("name");
            if ( name.startsWith("application/") )
            {
                if ( name.startsWith("application/RaptorEBA/") )
                {
                    continue;
                }

                root.removeChild(elm);
            }
        }
        // persist
        // Prepare the DOM document for writing
        Source source = new DOMSource(doc);

        // Prepare the output file
        File newFile = new File("config_modified.xml");
        Result result = new StreamResult(newFile);

        // Write the DOM document to the file
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);

        File oldFile = new File(getClass().getResource("config.xml").toURI());
        Assert.assertTrue(newFile.length() != oldFile.length());
    }

}
