package cx.ath.jbzdak.sqlbuilder;

/**
 * Author: Jacek Bzdak <jbzdak@gmail.com>
 */

public class OrderByExpression extends PeerIntermediateSQLObject{

   IntermediateSQLFactory expression;

   OrderByDirection direction;

   public OrderByExpression(IntermediateSQLObject parent) {
      super(parent);
   }

   public OrderByExpression(ExpressionContext context) {
      super(context);
   }

   public OrderByExpression(IntermediateSQLFactory expression, OrderByDirection direction) {
      this.expression = expression;
      this.direction = direction;
   }

   public OrderByExpression(ExpressionContext context, IntermediateSQLFactory expression, OrderByDirection direction) {
      super(context);
      this.expression = expression;
      this.direction = direction;
   }

   public OrderByExpression(IntermediateSQLObject parent, IntermediateSQLFactory expression, OrderByDirection direction) {
      super(parent);
      this.expression = expression;
      this.direction = direction;
   }

   public IntermediateSQLFactory getExpression() {
      return expression;
   }

   public void setExpression(IntermediateSQLFactory expression) {
      this.expression = expression;
   }

   public OrderByDirection getDirection() {
      return direction;
   }

   public void setDirection(OrderByDirection direction) {
      this.direction = direction;
   }
}
