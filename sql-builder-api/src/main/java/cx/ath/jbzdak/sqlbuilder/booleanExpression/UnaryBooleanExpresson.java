package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.*;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpresson extends IntermediateSQLObject implements BooleanExpressionMarker {

   IntermediateSQLFactory expression;

   String expressionType;

   public UnaryBooleanExpresson() {
   }

   public UnaryBooleanExpresson(String expressionType) {
      this.expressionType = expressionType;
   }

   public IntermediateSQLFactory getExpression() {
      return expression;
   }

   public void setExpression(ColumnExpression expression) {
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
