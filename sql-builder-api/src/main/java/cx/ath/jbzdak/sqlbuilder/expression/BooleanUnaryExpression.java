package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanUnaryExpression extends UnaryExpresson implements BooleanExpressionMarker{

   public BooleanUnaryExpression() {
   }

   public BooleanUnaryExpression(String expressionType) {
      super(expressionType);
   }

   public BooleanUnaryExpression(String expressionType, IntermediateSQLFactory expression) {
      super(expressionType, expression);
   }
}
