package cx.ath.jbzdak.sqlbuilder.xml.expression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.expression.BinaryExpressionType;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionArgument;
import cx.ath.jbzdak.sqlbuilder.expression.ExpressionMarker;
import cx.ath.jbzdak.sqlbuilder.expression.UnaryExpression;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.literal.ParameterLiteral;
import cx.ath.jbzdak.sqlbuilder.xml.XmlColumnExpression;
import cx.ath.jbzdak.sqlbuilder.xml.XmlParsingContext;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.TypedParameter;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlParameter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
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
      return XmlParsingContext.getDialect().getBooleanFactory().minus(toFactory(lhs));
   }


   public ExpressionMarker createBinary(){
      return XmlParsingContext.getDialect().getBooleanFactory().expression(BinaryExpressionType.MINUS,
              toFactory(lhs), toFactory(rhs));
   }
}



