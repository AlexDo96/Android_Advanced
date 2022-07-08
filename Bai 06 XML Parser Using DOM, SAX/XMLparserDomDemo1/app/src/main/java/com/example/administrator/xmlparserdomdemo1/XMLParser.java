package com.example.administrator.xmlparserdomdemo1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Administrator on 4/22/2017.
 */
public class XMLParser {

    String xml="";
    public String getXmlFromUrl(String chuoiurl)
    {
        try {
            URL url=new URL(chuoiurl);
            HttpURLConnection httpurl=(HttpURLConnection)url.openConnection();
            BufferedReader in=new BufferedReader(new InputStreamReader(httpurl.getInputStream()));
            String line="";
            StringBuffer buffer=new StringBuffer("");
            while ((line=in.readLine())!=null)
            {
                buffer.append(line);
            }
            xml=buffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    public Document getDomElement(String xml)
    {
        Document document=null;
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db=dbf.newDocumentBuilder();
            InputSource is=new InputSource();
            is.setCharacterStream(new StringReader(xml));
            document=db.parse(is);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }

    public String getValue(Element element,String str)
    {
        NodeList nl=element.getElementsByTagName(str).item(0).getChildNodes();
        Node nvalue=nl.item(0);
        return nvalue.getNodeValue();
    }
}
