package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.expression.BinaryExpressionType;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.xml.XmlParsingContext;

/**
 * Created by: Jacek Bzdak
 */
public class XmlDivide extends XmlBinaryExpression{

   @Override
   public ExpressionMarker create() {

      return XmlParsingContext.getDialect().getExpressionFactory().expression(BinaryExpressionType.DIVIDE, toFactory(lhs), toFactory(rhs));

   }
}
