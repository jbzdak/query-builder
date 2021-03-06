package cx.ath.jbzdak.sqlbuilder.expression;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;

/**
 * Created by: Jacek Bzdak
 */
public class BinaryBooleanExpression extends AbstractBinaryExpression implements BooleanExpressionMarker{

   public BinaryBooleanExpression() {
   }

   public BinaryBooleanExpression(String type) {
      super(type);
   }

   public BinaryBooleanExpression(String type, IntermediateSQLFactory lhs, IntermediateSQLFactory rhs) {
      super(type, rhs, lhs);
   }

}
