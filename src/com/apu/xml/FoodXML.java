package com.apu.xml;

import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apu.obj.Food;
import com.apu.util.Config;

public class FoodXML {
	
	
	private String file;
	
	public FoodXML() throws IOException{
		Config config = new Config();
		this.file = config.getXMLDataLocation() + "food.xml";
	}
	
	public boolean add(Food objFood){
		try {
			Document doc = XMLReaWrite.getXMLDoc(file);

	        // Root Element
	        Element rootElement = doc.getDocumentElement();
	 
			Element food = doc.createElement("food");
			rootElement.appendChild(food);
	 
			Attr attr = doc.createAttribute("id");
			attr.setValue(String.valueOf(XMLReaWrite.generateId(doc, "food")));
			food.setAttributeNode(attr);
			
			Element name = doc.createElement("name");
			name.appendChild(doc.createTextNode(objFood.getName()));
			food.appendChild(name);
	 
			Element desc = doc.createElement("desc");
			desc.appendChild(doc.createTextNode(String.valueOf(objFood.getDesc())));
			food.appendChild(desc);
			  		
			XMLReaWrite.wtiteXMLFile(doc, file);
	 
			return true;
			
		  } catch (Exception pce) {
			pce.printStackTrace();
		  }
		
		return false;
	}
	
	public ArrayList<Food> getAll() {
		try {
			
			ArrayList<Food> objFoodList = new ArrayList<Food>();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("food");
		 
			for (int i=0; i<nList.getLength(); i++) {
		 
				Food objFood = new Food();
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					objFood.setId(eElement.getAttribute("id"));
					objFood.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					objFood.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					
					objFoodList.add(objFood);
				}
				
			}
			
			return objFoodList;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Food get(int id) {
		try {
			
			Food objFood = new Food();
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("food");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(id))){
						objFood.setId(eElement.getAttribute("id"));
						objFood.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
						objFood.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
					}
				}
				
			}
			
			return objFood;
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean edit(Food objFood) {
		try {
			
			Document doc = XMLReaWrite.getXMLDoc(file);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("food");
		 
			for (int i=0; i<nList.getLength(); i++) {
				
				Node nNode = nList.item(i);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
		 
					if(eElement.getAttribute("id").equals(String.valueOf(objFood.getId()))){
						eElement.getElementsByTagName("name").item(0).setTextContent(objFood.getName());
						eElement.getElementsByTagName("desc").item(0).setTextContent((objFood.getDesc()));
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
			
			NodeList nList = doc.getElementsByTagName("food");
		 
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
