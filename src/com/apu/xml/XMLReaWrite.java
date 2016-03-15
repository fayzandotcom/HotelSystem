package com.apu.xml;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReaWrite {
	
	public static Document getXMLDoc(String filePath) throws ParserConfigurationException, SAXException, IOException{
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
	        Document doc = documentBuilder.parse(filePath);
	        return doc;
		}catch(FileNotFoundException ex){
			System.out.println("File " + filePath + " not found, have to create!");
			return null;
		}
	}
	
	public static void wtiteXMLFile(Document doc, String filePath) throws TransformerException {
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(filePath);

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
	}
	
	public static int generateId(Document doc, String element) {
		
		int id=0;
		
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName(element);
 
		for (int i=0; i<nList.getLength()-1; i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				id = Integer.parseInt(eElement.getAttribute("id"));
			}
		}
		
		return id+1;
	}

}
