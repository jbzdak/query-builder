package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class JoinOn extends AbstractJoin{

   private BooleanExpression onCondition;

   public JoinOn(String joinType, Table table, BooleanExpression onCondition) {
      super(joinType, table);
      this.onCondition = onCondition;
   }

   public BooleanExpression getOnCondition() {
      return onCondition;
   }
}
