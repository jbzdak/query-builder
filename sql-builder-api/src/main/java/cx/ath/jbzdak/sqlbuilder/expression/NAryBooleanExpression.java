package cx.ath.jbzdak.sqlbuilder.expression;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpression extends AbstractNaryExpression<BooleanExpressionMarker> implements BooleanExpressionMarker{

   public NAryBooleanExpression(String type) {
      super(type);
   }

   public NAryBooleanExpression(String type, BooleanExpressionMarker... markers) {
      super(type, markers);
   }

   public NAryBooleanExpression(String type, Collection<BooleanExpressionMarker> expressions) {
      super(type, expressions);
   }
}
