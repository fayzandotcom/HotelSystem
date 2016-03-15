package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.Policy;
import com.apu.util.Config;

public class PolicyXML {
	
	
	private String file;
	
	public PolicyXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "policy.xml";
	}
	
	public boolean add(Policy objPolicy){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element policy = doc.createElement("policy");
			rootElement.appendChild(policy);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "policy")));
			policy.setAttributeNode(attr);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(objPolicy.getName()));
			policy.appendChild(name);
	 
			Element desc = doc.createElement("desc");
			desc.appendChild(doc.createTextNode(objPolicy.getDesc()));
			policy.appendChild(desc);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Policy> getAll() {
		try {
			
			ArrayList<Policy> objPolicyList = new ArrayList<Policy>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("policy");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Policy objPolicy = new Policy();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objPolicy.setId(eElement.getAttribute("id"));
					objPolicy.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					objPolicy.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					
					objPolicyList.add(objPolicy);
				}
				
			}
			
			return objPolicyList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Policy get(int id) {
		try {
			
			Policy objPolicy = new Policy();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("policy");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objPolicy.setId(eElement.getAttribute("id"));
						objPolicy.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
						objPolicy.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					}
				}
				
			}
			
			return objPolicy;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Policy objPolicy) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("policy");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objPolicy.getId()))){
						eElement.getElementsByTagName("name").item(0).setTextContent(objPolicy.getName());
						eElement.getElementsByTagName("desc").item(0).setTextContent((objPolicy.getDesc()));
						XMLReaWrite.wtiteXMLFile(doc, file);
						return true;
					}
				}
			}
			
			return false;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(int id) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("policy");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						nNode.getParentNode().removeChild(nNode);
						XMLReaWrite.wtiteXMLFile(doc, file);
						return true;
					}
				}
				
			}
			
			return false;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


}
