package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLObject;
import cx.ath.jbzdak.sqlbuilder.Select;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpresson extends SQLObject implements BooleanExpressionMarker {

   SQLFactory expression;

   String expressionType;

   public UnaryBooleanExpresson() {
   }

   public UnaryBooleanExpresson(String expressionType) {
      this.expressionType = expressionType;
   }

   public SQLFactory getExpression() {
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
