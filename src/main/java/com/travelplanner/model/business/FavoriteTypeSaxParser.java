package com.travelplanner.model.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Class for parsing favorite list types for use on Welcome page Favorite List Summary
 * and on the Create Favorite List page Favorite List Type drop down list.
 * @author Gavin Rouleau
 */
public class FavoriteTypeSaxParser extends DefaultHandler implements ServletContextListener {

    /** Field for storing an instance of StringBuffer for building strings. */
    StringBuffer stringBuffer = null;
    
    /** Field for storing the Favorite List Types retrieved from the parsed XML file. */
    List<String> favListTypes = null;
    
    /**
     * Method implemented in accordance with ServletContextListener and web.xml
     * configured listener to initiate parsing of XML file to read in Favorite List Types.
     * @param event - Incoming ServletContextEvent generated by web.xml listener.
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {

        ServletContext context = event.getServletContext();
        String xmlFilePath = context.getRealPath(context.getInitParameter("list_types_xml_path"));
        parseFavoriteListTypes(xmlFilePath);
        context.setAttribute("favListTypes", favListTypes);
    }

    /**
     * Method for getting SAX Parser Factory, SAX Parser, and parsing XML file.
     * @param filename Incoming filename to parse.
     */
    public void parseFavoriteListTypes(String filename) {
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
        stringBuffer = new StringBuffer();
        
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(filename), this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method overridden from DefaultHandler to identify starting XML tag elements.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        stringBuffer.setLength(0);
        
        switch (qName) {
            case "fav-list-types":
                favListTypes = new ArrayList<String>();
                break;
            case "fav-list-type":
                // Do nothing
                break;
            case "type-name":
                // Do nothing
                break;
        }
    }
    
    /**
     * Method overridden from DefaultHandler to identify ending XML tag elements.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
        switch (qName) {
            case "fav-list-types":
                // Do nothing
                break;
            case "fav-list-type":
                // Do nothing
                break;
            case "type-name":
                favListTypes.add(stringBuffer.toString().trim());
                break;
        }
    }
    
    /**
     * Method overridden from DefaultHandler to parse characters between tag elements.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        
        stringBuffer.append(ch, start, length);
    }
    
    /**
     * Method overridden from ServletContextListener with no implementation.
     * @param event - Incoming ServletContextEvent generated by web.xml listener.
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
        // Method empty intentionally
    }
}
