package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLObject;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLObject;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractBinaryBooleanExpression extends IntermediateSQLObject implements BooleanExpressionMarker {

   protected String type;

   protected IntermediateSQLObject rhs;

   protected IntermediateSQLObject lhs;

   protected AbstractBinaryBooleanExpression() {
   }


   protected AbstractBinaryBooleanExpression(String type) {
      this.type = type;
   }

   protected AbstractBinaryBooleanExpression(String type, IntermediateSQLObject rhs, IntermediateSQLObject lhs) {
      this.type = type;
      this.rhs = rhs;
      this.lhs = lhs;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public SQLFactory getRhs() {
      return rhs;
   }

   public SQLFactory getLhs() {
      return lhs;
   }

}
