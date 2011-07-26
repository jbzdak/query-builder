package cx.ath.jbzdak.sqlbuilder.dialect.peer;

/**
 * Created by: Jacek Bzdak
 */
public class ConditionPeer extends AbstractPeer<cx.ath.jbzdak.sqlbuilder.Condition>{

   public ConditionPeer() {
   }

   public StringBuilder toSQL() {
      StringBuilder stringBuilder = new StringBuilder("( ");
      stringBuilder.append(parent.getLhs());
      stringBuilder.append(" ");
      stringBuilder.append(parent.getConditionType());
      stringBuilder.append(" ");
      stringBuilder.append(parent.getLhs());
      stringBuilder.append(" )");
      return stringBuilder;
   }
}
