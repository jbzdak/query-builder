package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.*;

/**
 * Created by: Jacek Bzdak
 */
public class JoinOn extends AbstractJoin{

   private AbstractBinaryBooleanExpression onCondition;

   public JoinOn() {
   }

   public JoinOn(AbstractBinaryBooleanExpression onCondition) {
      this.onCondition = onCondition;
   }

   public AbstractBinaryBooleanExpression getOnCondition() {
      return onCondition;
   }

   public void setOnCondition(AbstractBinaryBooleanExpression onCondition) {
      this.onCondition = onCondition;
   }
}
