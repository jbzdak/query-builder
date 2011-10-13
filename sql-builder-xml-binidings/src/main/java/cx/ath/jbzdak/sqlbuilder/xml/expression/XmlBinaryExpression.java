package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.expression.ExpressionArgument;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.literal.ParameterLiteral;
import cx.ath.jbzdak.sqlbuilder.xml.XmlColumnExpression;
import cx.ath.jbzdak.sqlbuilder.xml.XmlRaw;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.TypedParameter;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlParameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * Created by: Jacek Bzdak
 */
public abstract class XmlBinaryExpression implements Factory<ExpressionMarker> {
   Object lhs;

   Object rhs;

   @XmlElements({
           @XmlElement(name = "plus", type = XmlPlus.class),
           @XmlElement(name = "times", type = XmlTimes.class),
           @XmlElement(name = "parameter", type = TypedParameter.class),
           @XmlElement(name = "column", type = XmlColumnExpression.class),
           @XmlElement(name = "minus", type = XmlMinus.class),
           @XmlElement(name = "plus", type = XmlPlus.class),
           @XmlElement(name = "raw", type = XmlRaw.class)
   })
   public Object getLhs() {
      return lhs;
   }

   public void setLhs(Object lhs) {
      this.lhs = lhs;
   }


   @XmlElements({
           @XmlElement(name = "plus", type = XmlPlus.class),
           @XmlElement(name = "times", type = XmlTimes.class),
           @XmlElement(name = "parameter", type = TypedParameter.class),
           @XmlElement(name = "column", type = XmlColumnExpression.class),
           @XmlElement(name = "minus", type = XmlMinus.class),
           @XmlElement(name = "plus", type = XmlPlus.class),
           @XmlElement(name = "raw", type = XmlRaw.class)
   })
   public Object getRhs() {
      return rhs;
   }

   public void setRhs(Object rhs) {
      this.rhs = rhs;
   }


   protected ExpressionArgument toFactory(Object o){
      if (o instanceof XmlParameter) {
         XmlParameter xmlParameter = (XmlParameter) o;
         return new ParameterLiteral(xmlParameter.createParameter());
      }
      Factory<ExpressionArgument> factory = (Factory<ExpressionArgument>) o;
      return factory.create();
   }


}
