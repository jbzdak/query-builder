package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionB {

   Dialect dialect;

   String type;

   SQLFactory lhs;

   public BooleanExpressionB(String type, SQLFactory lhs) {
      this.type = type;
      this.lhs = lhs;
   }

   public BooleanExpressionB(Dialect dialect, String type, SQLFactory lhs) {
      this.dialect = dialect;
      this.type = type;
      this.lhs = lhs;
   }

   private BooleanExpression create(){
      if(dialect!= null){
         return dialect.booleanExpression();
      } else {
         return new BooleanExpression();
      }
   }

   private BooleanExpression create(SQLFactory rhs){
      BooleanExpression expression = create();
      expression.setLhs(lhs);
      expression.setRhs(rhs);
      expression.setType(type);
      return expression;
   }

   public BooleanExpression rhs(BooleanExpression booleanExpression){
      return create(booleanExpression);
   }

    public BooleanExpression rhs(Condition booleanExpression){
      return create(booleanExpression);
    }

   public BooleanExpression rhs(String conditionType, ColumnExpression left, ColumnExpression right){
      return create(new Condition(conditionType, right, left));
   }

    public BooleanExpression rhs(String conditionType, ColumnExpression left, SQLLiteral right){
       return create( new Condition(conditionType, right, left));
   }
}
