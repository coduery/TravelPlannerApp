package com.travelplanner.view;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Class for displaying the current date on JSP pages mapped through view.tld file.
 * @author Gavin Rouleau
 */
public class CurrentDate extends SimpleTagSupport {

    /**
     * Method overridden from SimpleTagSupport to construct current date tag.
     */
    @Override
    public void doTag() throws JspException, IOException {
        
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
        getJspContext().getOut().print(dateFormat.format(calendar.getTime()));
    }
}
