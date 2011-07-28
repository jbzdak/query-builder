package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.*;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionA {

   Dialect dialect;

   String type;

   public BooleanExpressionA(Dialect dialect, String type) {
      this.dialect = dialect;
      this.type = type;
   }

   public BooleanExpressionA(String type) {
      this.type = type;
   }

   public BooleanExpressionB lhs(BooleanExpression booleanExpression){
      return new BooleanExpressionB(dialect, type, booleanExpression);
   }

    public BooleanExpressionB lhs(Condition booleanExpression){
      return new BooleanExpressionB(dialect,type, booleanExpression);
   }

   public BooleanExpressionB lhs(String conditionType, ColumnExpression left, ColumnExpression right){
      return new BooleanExpressionB(dialect,type, new Condition(conditionType, right, left));
   }

    public BooleanExpressionB lhs(String conditionType, ColumnExpression left, SQLLiteral right){
      return new BooleanExpressionB(dialect, type, new Condition(conditionType, right, left));
   }
}
