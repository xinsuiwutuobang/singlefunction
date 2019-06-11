package com.yangfei.singlefunction.utils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;
/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
public class XMLWriter{

    public static void main(String[] args) {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element theBook=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder db=factory.newDocumentBuilder();
            File files = new File(
                    "C:\\Users\\yangfei\\Documents\\WeChat Files\\YF8377\\FileStorage\\File\\2019-06\\labels");
            File[] files1 = files.listFiles();
            for (File file : files1) {
                Document xmldoc=db.parse(file);

                root=xmldoc.getDocumentElement();
                //theBook = (Element) xmldoc.getElementsByTagName("annotation");
                root.removeAttribute("verified");
                output(xmldoc);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                //DOMSource source = new DOMSource(doc);
                Source source = new DOMSource(xmldoc);
                //StreamResult result = new StreamResult();
                Result result = new StreamResult(file);
                transformer.transform(source, result);//
            }
            //File file = new File(
                    //"C:\\Users\\yangfei\\Documents\\WeChat Files\\YF8377\\FileStorage\\File\\2019-06\\0097.xml");

            //saveXml("Test1_Edited.xml", xmldoc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void output(Node node) {//将node的XML字符串输出到控制台
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("encoding", "utf-8");
            transformer.setOutputProperty("indent", "yes");
            DOMSource source=new DOMSource();
            source.setNode(node);
            StreamResult result=new StreamResult();
            result.setOutputStream(System.out);

            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static Node selectSingleNode(String express, Object source) {//查找节点，并返回第一个符合条件节点
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static NodeList selectNodes(String express, Object source) {//查找节点，返回符合条件的节点集。
        NodeList result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void saveXml(String fileName, Document doc) {//将Document输出到文件
        TransformerFactory transFactory=TransformerFactory.newInstance();
        try {
            Transformer transformer = transFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            DOMSource source=new DOMSource();
            source.setNode(doc);
            StreamResult result=new StreamResult();
            result.setOutputStream(new FileOutputStream(fileName));

            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
