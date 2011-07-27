package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpression extends SQLObject{

   String booleanExpressionType;

   SQLFactory rhs;

   SQLFactory lhs;

   public BooleanExpression() {
   }



   public String getBooleanExpressionType() {
      return booleanExpressionType;
   }

   public void setBooleanExpressionType(String booleanExpressionType) {
      this.booleanExpressionType = booleanExpressionType;
   }

   public SQLFactory getRhs() {
      return rhs;
   }

   public SQLFactory getLhs() {
      return lhs;
   }

   public void setRhs(SQLFactory rhs) {
      this.rhs = rhs;
   }

   public void setLhs(SQLFactory lhs) {
      this.lhs = lhs;
   }

}
