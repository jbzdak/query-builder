package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryBooleanExpression extends UnaryExpression implements BooleanExpressionMarker{

   public UnaryBooleanExpression() {
   }

   public UnaryBooleanExpression(String expressionType) {
      super(expressionType);
   }

   public UnaryBooleanExpression(String expressionType, IntermediateSQLFactory expression) {
      super(expressionType, expression);
   }
}
