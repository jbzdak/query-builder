package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class Condition extends AbstractBinaryBooleanExpression {


   public Condition() {
   }

   Condition(String type, ColumnExpression rhs, SQLLiteral lhs) {
       super(type, rhs, lhs);
   }

   Condition(String type, SQLLiteral rhs, ColumnExpression lhs) {
       super(type, rhs, lhs);
   }

   Condition(String type, ColumnExpression rhs, ColumnExpression lhs) {
       super(type, rhs, lhs);
   }

   public void setLhs(ColumnExpression lhs){
      this.lhs = lhs;
   }

   public void setLhs(SQLLiteral lhs){
      this.lhs = lhs;
   }

    public void setRhs(ColumnExpression rhs){
      this.rhs = lhs;
   }

   public void setRhs(SQLLiteral rhs){
      this.rhs = lhs;
   }
}
