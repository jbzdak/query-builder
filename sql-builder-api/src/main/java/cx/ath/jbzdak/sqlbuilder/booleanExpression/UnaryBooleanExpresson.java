package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.*;

import java.security.InvalidParameterException;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpresson extends IntermediateSQLObject implements BooleanExpressionMarker {

   protected IntermediateSQLFactory expression;

   protected String expressionType;

   protected UnaryBooleanExpresson() {
   }

   public UnaryBooleanExpresson(String expressionType) {
      this(expressionType, null);
   }

   public UnaryBooleanExpresson(String expressionType, IntermediateSQLFactory expression) {
      if(!UnaryBooleanExpressionType.values().contains(expressionType)){
         throw new InvalidParameterException("Unknown expression type '" + expression + "'");
      }
      this.expression = expression;
      this.expressionType = expressionType;
   }

   public IntermediateSQLFactory getExpression() {
      return expression;
   }

   public void setExpression(ColumnExpression expression) {
      this.expression = expression;
   }

   public void setExpression(SQLLiteral expression) {
      this.expression = expression;
   }

   public void setExpression(BooleanExpressionMarker expression) {
      this.expression = expression;
   }

   public void setExpression(Select expression) {
      this.expression = expression;
   }

   public String getExpressionType() {
      return expressionType;
   }

   public void setExpressionType(String expressionType) {
      this.expressionType = expressionType;
   }
}
