package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLObject;
import cx.ath.jbzdak.sqlbuilder.SQLObject;

/**
 * Created by: Jacek Bzdak
 */
public class BetweenCondition extends IntermediateSQLObject implements BooleanExpressionMarker{

   Range range;

   ColumnExpression expression;

   public BetweenCondition() {
   }

   public BetweenCondition(Range range, ColumnExpression expression) {
      this.range = range;
      this.expression = expression;
   }

   public void setExpression(ColumnExpression expression) {
      this.expression = expression;
   }

   public void setRange(Range range){
      this.range = range;
   }

   public Range getRange() {
      return range;
   }

   public ColumnExpression getExpression() {
      return expression;
   }
}
