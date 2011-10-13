package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.expression.BooleanExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.NAryExpressionType;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "times")
public class XmlTimes extends XmlNargExpression implements Factory<ExpressionMarker> {

   @Override
   public ExpressionMarker create() {
      return create(NAryExpressionType.TIMES);
   }
}
