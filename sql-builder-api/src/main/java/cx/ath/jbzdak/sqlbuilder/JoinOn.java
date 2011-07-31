package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.*;

/**
 * Created by: Jacek Bzdak
 */
public class JoinOn extends AbstractJoin{

   private AbstractBinaryBooleanExpression onCondition;

   public JoinOn() {
   }

   public JoinOn(String joinType, Table table, BinaryBooleanExpression onCondition) {
      super(joinType, table);
      this.onCondition = onCondition;
   }

   public AbstractBinaryBooleanExpression getOnCondition() {
      return onCondition;
   }

   public void setOnCondition(AbstractBinaryBooleanExpression onCondition) {
      this.onCondition = onCondition;
   }

   public BooleanExpressionA createAndCondition(){
      return BooleanExpressionFactory.and(dialect, new BooleanExpressionFactoryCallback() {
         public void expressionBuild(BinaryBooleanExpression expression) {
            onCondition = expression;
         }
      });
   }

   public BooleanExpressionA createOrCondition(){
      return BooleanExpressionFactory.or(dialect, new BooleanExpressionFactoryCallback() {
         public void expressionBuild(BinaryBooleanExpression expression) {
            onCondition = expression;
         }
      });
   }
}
