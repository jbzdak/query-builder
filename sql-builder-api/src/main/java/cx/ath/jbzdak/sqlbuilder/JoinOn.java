package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.*;

import java.util.Set;

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

   public Set<String> collectParameterNames() {
      return expressionContext.collectParameterNames(collectParameterNamesParant(), onCondition);
   }
}
