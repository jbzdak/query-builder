package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.SQLFactory;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpression extends AbstractBooleanExpression{

   public BooleanExpression() {
   }

   BooleanExpression(String type, SQLFactory rhs, SQLFactory lhs) {
      super(type, rhs, lhs);
   }

   public void setRhs(BooleanExpression rhs){
      this.rhs = rhs;
   }

   public void setRhs(Condition rhs){
      this.rhs = rhs;
   }

   public void setLhs(BooleanExpression lhs){
      this.lhs = lhs;
   }

   public void setLhs(Condition lhs){
      this.lhs = lhs;
   }

   void setRhs(SQLFactory rhs){
      this.rhs = rhs;
   }

   void setLhs(SQLFactory lhs){
      this.lhs = lhs;
   }



}
