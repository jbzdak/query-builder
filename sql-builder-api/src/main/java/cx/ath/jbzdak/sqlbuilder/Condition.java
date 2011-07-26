package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class Condition extends SQLObject implements SQLFactory{

   private String conditionType;

   private SQLFactory lhs;

   private SQLFactory rhs;

   public Condition(String conditionType, SQLFactory lhs, SQLFactory rhs) {
      this.conditionType = conditionType;
      this.lhs = lhs;
      this.rhs = rhs;
   }

   public String getConditionType() {
      return conditionType;
   }

   public SQLFactory getLhs() {
      return lhs;
   }

   public SQLFactory getRhs() {
      return rhs;
   }
}


