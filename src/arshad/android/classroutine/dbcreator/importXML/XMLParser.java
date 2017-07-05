//    Copyright (C) 2017 MD. Ibrahim Khan
//
//    Project Name: 
//    Author: MD. Ibrahim Khan
//    Author's Email: ib.arshad777@gmail.com
//
//    Redistribution and use in source and binary forms, with or without modification,
//    are permitted provided that the following conditions are met:
//
//    1. Redistributions of source code must retain the above copyright notice, this
//       list of conditions and the following disclaimer.
//
//    2. Redistributions in binary form must reproduce the above copyright notice, this
//       list of conditions and the following disclaimer in the documentation and/or
//       other materials provided with the distribution.
//
//    3. Neither the name of the copyright holder nor the names of the contributors may
//       be used to endorse or promote products derived from this software without
//       specific prior written permission.
//
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
//    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
//    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//    IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
//    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING
//    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
//    DATA, OR PROFITS; OR BUSINESS INTERRUPTIONS) HOWEVER CAUSED AND ON ANY THEORY OF
//    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//    OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
//    OF THE POSSIBILITY OF SUCH DAMAGE.

package arshad.android.classroutine.dbcreator.importXML;

import arshad.android.classroutine.dbcreator.model.ClassInfo;
import arshad.android.classroutine.dbcreator.model.Days;
import arshad.android.classroutine.dbcreator.model.Deadlines;
import arshad.android.classroutine.dbcreator.model.ExtraInfo;
import arshad.android.classroutine.dbcreator.model.Inputs;
import arshad.android.classroutine.dbcreator.model.TeacherInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Arshad
 */
public class XMLParser {
    private final DocumentBuilderFactory factory;
    private final DocumentBuilder builder;
    private Document doc;
    private XPath xPath;
    private final String filename;
    
    public XMLParser(String xmlfile) throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        filename = xmlfile;
    }
    
    public ObjectDatabase getObjectDatabase() throws FileNotFoundException, SAXException, IOException, XPathExpressionException {
        File file = new File(filename);
        if(!file.exists()) {
            throw new FileNotFoundException("Could not find the xml input file");
        }
        doc = builder.parse(file);
        doc.getDocumentElement().normalize();
        
        xPath =  XPathFactory.newInstance().newXPath();
        
        Days days = new Days();
        days.setClassList(Days.DAY_FRIDAY, getClassInfoList(Days.DAY_FRIDAY));
        days.setClassList(Days.DAY_MONDAY, getClassInfoList(Days.DAY_MONDAY));
        days.setClassList(Days.DAY_SATURDAY, getClassInfoList(Days.DAY_SATURDAY));
        days.setClassList(Days.DAY_SUNDAY, getClassInfoList(Days.DAY_SUNDAY));
        days.setClassList(Days.DAY_THURSDAY, getClassInfoList(Days.DAY_THURSDAY));
        days.setClassList(Days.DAY_TUESDAY, getClassInfoList(Days.DAY_TUESDAY));
        days.setClassList(Days.DAY_WEDNESDAY, getClassInfoList(Days.DAY_WEDNESDAY));        
        
        return new ObjectDatabase(days, getDeadlines(), getExtraInfo(), getCurrentVersion());
    }
    
    private ArrayList<ClassInfo> getClassInfoList(String day) throws XPathExpressionException {
        ArrayList<ClassInfo> classList = new ArrayList<>();
        String expression = "/classroutine/routine/day[@name='" + day + "']/class";	        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;
            String index = eElement.getElementsByTagName("index").item(0).getTextContent();
            String coursecode = eElement.getElementsByTagName("coursecode").item(0).getTextContent();
            String coursename = eElement.getElementsByTagName("coursename").item(0).getTextContent();
            String room = eElement.getElementsByTagName("room").item(0).getTextContent();
            String time = eElement.getElementsByTagName("time").item(0).getTextContent();
            String courseteacher = eElement.getElementsByTagName("courseteacher").item(0).getTextContent();
            classList.add(new ClassInfo(index, coursecode, coursename, room, time, courseteacher));
        }
        classList.trimToSize();
        return classList;
    }
    
    private Deadlines getDeadlines() throws XPathExpressionException {
        String expression = "/classroutine/deadlines/inputs";	        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        Deadlines deadlines = new Deadlines();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;
            String index = eElement.getElementsByTagName("index").item(0).getTextContent();
            String topic = eElement.getElementsByTagName("topic").item(0).getTextContent();
            String info1 = eElement.getElementsByTagName("info1").item(0).getTextContent();
            String info2 = eElement.getElementsByTagName("info2").item(0).getTextContent();
            String info3 = eElement.getElementsByTagName("info3").item(0).getTextContent();
            String info4 = eElement.getElementsByTagName("info4").item(0).getTextContent();
            deadlines.addInputs(new Inputs(index, topic, info1, info2, info3, info4));
        }
        deadlines.trim();
        
        return deadlines;
    }
    
    private ExtraInfo getExtraInfo() throws XPathExpressionException {
        String expression = "/classroutine/extrainfo/teacher";	        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        ExtraInfo extraInfo = new ExtraInfo();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Element eElement = (Element) nNode;
            String name = eElement.getElementsByTagName("name").item(0).getTextContent();
            String department = eElement.getElementsByTagName("department").item(0).getTextContent();
            String initial = eElement.getElementsByTagName("initial").item(0).getTextContent();
            String mobile = eElement.getElementsByTagName("mobile").item(0).getTextContent();
            String email = eElement.getElementsByTagName("email").item(0).getTextContent();
            extraInfo.addTeacherInfo(new TeacherInfo(name, department, initial, mobile, email));
        }
        extraInfo.trim();
        
        return extraInfo;
    }
    
    private String getCurrentVersion() throws XPathExpressionException {
        String expression = "/classroutine/version";	        
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
        Element eElement = (Element) nodeList.item(0);
        return eElement.getElementsByTagName("currentversion").item(0).getTextContent();
    }
}
