package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;

/**
 * Created by: Jacek Bzdak
 */
public class Not extends UnaryBooleanExpresson{


   public Not() {
      expressionType = "NOT";
   }

   public Not(BooleanExpressionMarker parent) {
      this();
      expression = parent;
   }

   public Not(ColumnExpression parent) {
      this();
      expression = parent;
   }
}
