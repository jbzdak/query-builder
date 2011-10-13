package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.xml.XmlColumnExpression;
import cx.ath.jbzdak.sqlbuilder.xml.XmlRaw;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.TypedParameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Created by: Jacek Bzdak
 */
public class XmlExpression {

   Object internal;

   @XmlElements({
           @XmlElement(name = "plus", type = XmlPlus.class),
           @XmlElement(name = "times", type = XmlTimes.class),
           @XmlElement(name = "minus", type = XmlMinus.class),
           @XmlElement(name = "divide", type = XmlDivide.class)
   })
   public Object getInternal() {
      return internal;
   }

   public void setInternal(Object internal) {
      this.internal = internal;
   }
}
