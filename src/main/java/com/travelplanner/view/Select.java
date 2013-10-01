package com.travelplanner.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Class to construct a selection drop down list.
 * @author Gavin Rouleau
 */
public class Select extends SimpleTagSupport {

    /** Field to store name of selection list. */
    private String name;

    /** Field to store size of selection list option view. */
    private String size;
    
    /** Field to store a List of options to include in selection drop down list.*/
    private List<String> select_options;
    
    /**
     * Method overridden from SimpleTagSupport to construct selection tag.
     */
    @Override
    public void doTag() throws JspException, IOException {
        
        JspWriter out = getJspContext().getOut();
        String tag = constructTag();
        out.print(tag);
    }
    
    /**
     * Helper method to construct selection tag.
     * @return Returns a String representing the selection tag.
     */
    private String constructTag() {
        
        StringBuilder tag = new StringBuilder();
        tag.append("<select name=\"");
        tag.append(name);
        tag.append("\" size=\"");
        tag.append(size);
        tag.append("\">\r\n");
        
        for (String favListType : select_options) {
            tag.append("                        <option value=\"");
            tag.append(favListType);
            tag.append("\"> ");
            tag.append(favListType);
            tag.append(" </option>\r\n");
        }
        
        tag.append("                    </select>");
        
        return tag.toString();
    }
    
    /**
     * Method for setting the name of the selection drop down list.
     * @param name Incoming parameter representing name of selection drop down list.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the size of selection list option view.
     * @param size Incoming parameter representing the size of the selection list option view.
     */
    public void setSize(String size) {
        this.size = size;
    }
    
    /**
     * Method for setting the options to include in the selection drop down list.
     * @param select_options Incoming parameter representing the options to include in the selection drop down list.
     */
    public void setSelect_options(List<String> select_options) {
        this.select_options = select_options;
    }
}
