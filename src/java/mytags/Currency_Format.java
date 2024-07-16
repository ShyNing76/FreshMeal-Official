/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytags;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import utils.Format;

/**
 *
 * @author ADMIN
 */
public class Currency_Format extends SimpleTagSupport {
    
    private Integer price;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            out.print(Format.formatCurrency(price));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
