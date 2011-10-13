package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.NAryExpression;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlAnd;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlBinaryExpression;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlIsNull;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.XmlOr;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class XmlNargExpression {

   private List<Object> operands =  new ArrayList<Object>();

   @XmlElements({
           @XmlElement(name = "or", type = XmlOr.class),
           @XmlElement(name = "and", type = XmlAnd.class),
           @XmlElement(name = "cond", type = XmlBinaryExpression.class),
           @XmlElement(name = "is-null", type = XmlIsNull.class)
   })
   public List<Object> getOperands() {
      return operands;
   }

   public void setOperands(List<Object> operands) {
      this.operands = operands;
   }

   public ExpressionMarker create(String type) {
      NAryExpression expression = new NAryExpression(type);
      for (Object o : getOperands()) {
         Factory<ExpressionMarker> f = (Factory<ExpressionMarker>) o;
         expression.addExpression(f.create());
      }
      return expression;

   }
}
