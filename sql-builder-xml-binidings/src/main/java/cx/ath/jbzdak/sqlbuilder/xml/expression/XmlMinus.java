package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.expression.BinaryExpressionType;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.xml.XmlParsingContext;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "minus")
public class XmlMinus extends XmlBinaryExpression{


   @Override
   public ExpressionMarker create() {
      if(rhs == null){
         return createSingleParam();
      }
      return createBinary();
   }

   public ExpressionMarker createSingleParam(){
      return XmlParsingContext.getDialect().getExpressionFactory().minus(toFactory(lhs));
   }


   public ExpressionMarker createBinary(){
      return XmlParsingContext.getDialect().getExpressionFactory().expression(BinaryExpressionType.MINUS,
              toFactory(lhs), toFactory(rhs));
   }
}



