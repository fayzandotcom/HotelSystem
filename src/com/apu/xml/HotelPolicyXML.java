package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.HotelPolicy;
import com.apu.util.Config;

public class HotelPolicyXML {
	
	
	private String file;
	
	public HotelPolicyXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "hotelPolicy.xml";
	}
	
	public boolean add(HotelPolicy objHotelPolicy){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element hotelPolicy = doc.createElement("hotelPolicy");
			rootElement.appendChild(hotelPolicy);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "hotelPolicy")));
			hotelPolicy.setAttributeNode(attr);
			
			Element hotelId = doc.createElement("hotelId");
			hotelId.appendChild(doc.createTextNode(objHotelPolicy.getHotelId()));
			hotelPolicy.appendChild(hotelId);
	 
			Element policyId = doc.createElement("policyId");
			policyId.appendChild(doc.createTextNode(objHotelPolicy.getPolicyId()));
			hotelPolicy.appendChild(policyId);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<HotelPolicy> getAll() {
		try {
			
			ArrayList<HotelPolicy> objHotelPolicyList = new ArrayList<HotelPolicy>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelPolicy");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelPolicy objHotelPolicy = new HotelPolicy();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objHotelPolicy.setId(eElement.getAttribute("id"));
					objHotelPolicy.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
					objHotelPolicy.setPolicyId(eElement.getElementsByTagName("policyId").item(0).getTextContent());
					
					objHotelPolicyList.add(objHotelPolicy);
				}
				
			}
			
			return objHotelPolicyList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelPolicy> getByHotelId(String hotelId) {
		try {
			
			ArrayList<HotelPolicy> objHotelPolicyList = new ArrayList<HotelPolicy>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelPolicy");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				HotelPolicy objHotelPolicy = new HotelPolicy();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getElementsByTagName("hotelId").item(0).getTextContent().equalsIgnoreCase(hotelId)) {
						objHotelPolicy.setId(eElement.getAttribute("id"));
						objHotelPolicy.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelPolicy.setPolicyId(eElement.getElementsByTagName("policyId").item(0).getTextContent());
						
						objHotelPolicyList.add(objHotelPolicy);
					}
				}
				
			}
			
			return objHotelPolicyList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelPolicy get(int id) {
		try {
			
			HotelPolicy objHotelPolicy = new HotelPolicy();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelPolicy");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objHotelPolicy.setId(eElement.getAttribute("id"));
						objHotelPolicy.setHotelId(eElement.getElementsByTagName("hotelId").item(0).getTextContent());
						objHotelPolicy.setPolicyId(eElement.getElementsByTagName("policyId").item(0).getTextContent());
					}
				}
				
			}
			
			return objHotelPolicy;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(HotelPolicy objHotelPolicy) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("hotelPolicy");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objHotelPolicy.getId()))){
						eElement.getElementsByTagName("hotelId").item(0).setTextContent(objHotelPolicy.getHotelId());
						eElement.getElementsByTagName("policyId").item(0).setTextContent((objHotelPolicy.getPolicyId()));
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
			
			NodeList nList = doc.getElementsByTagName("hotelPolicy");
		 
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
