package cx.ath.jbzdak.sqlbuilder.expression;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpression extends AbstractNaryExpression<BooleanExpressionArgument> implements BooleanExpressionMarker{

   public NAryBooleanExpression(String type) {
      super(type);
   }

   public NAryBooleanExpression(String type, BooleanExpressionArgument... markers) {
      super(type, markers);
   }

   public NAryBooleanExpression(String type, Collection<? extends BooleanExpressionArgument> expressions) {
      super(type, expressions);
   }
}
