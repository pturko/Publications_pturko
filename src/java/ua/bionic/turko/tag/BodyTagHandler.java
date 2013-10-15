/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.turko.tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Artem
 */
public class BodyTagHandler extends BodyTagSupport{
    private int number;

    public void setNumber(String number) {
        this.number = new Integer(number);
    }
    
    public int doStartTag() throws JspTagException{
        try {
            pageContext.getOut().write("<TABLE BORDER=\"2\" WIDTH=\"100%\">");
            pageContext.getOut().write("<TR><TD>");
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    
    public int doAfterBody() throws JspTagException{
       if(number-- > 1){
            try {
                pageContext.getOut().write("</TD></TR><TR><TD>");                 
            } catch (IOException ex) {
                 throw new JspTagException(ex.getMessage());
            }
            return EVAL_BODY_INCLUDE;
       }else{
           return SKIP_BODY;
       }    
    }
    
    public int doEndTag() throws JspTagException {
        try{
            pageContext.getOut().write("</TD></TR>");
            pageContext.getOut().write("</TABLE>");
        }catch(IOException e){
             throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
