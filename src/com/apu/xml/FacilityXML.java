package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.Facility;
import com.apu.util.Config;

public class FacilityXML {
	
	
	private String file;
	
	public FacilityXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "facility.xml";
	}
	
	public boolean add(Facility objFacility){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element facility = doc.createElement("facility");
			rootElement.appendChild(facility);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "facility")));
			facility.setAttributeNode(attr);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(objFacility.getName()));
			facility.appendChild(name);
	 
			Element desc = doc.createElement("desc");
			desc.appendChild(doc.createTextNode(String.valueOf(objFacility.getDesc())));
			facility.appendChild(desc);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Facility> getAll() {
		try {
			
			ArrayList<Facility> objFacilityList = new ArrayList<Facility>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("facility");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Facility objFacility = new Facility();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objFacility.setId(eElement.getAttribute("id"));
					objFacility.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					objFacility.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					
					objFacilityList.add(objFacility);
				}
				
			}
			
			return objFacilityList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Facility get(int id) {
		try {
			
			Facility objFacility = new Facility();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("facility");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objFacility.setId(eElement.getAttribute("id"));
						objFacility.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
						objFacility.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					}
				}
				
			}
			
			return objFacility;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Facility objFacility) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("facility");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objFacility.getId()))){
						eElement.getElementsByTagName("name").item(0).setTextContent(objFacility.getName());
						eElement.getElementsByTagName("desc").item(0).setTextContent((objFacility.getDesc()));
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
			
			NodeList nList = doc.getElementsByTagName("facility");
		 
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
