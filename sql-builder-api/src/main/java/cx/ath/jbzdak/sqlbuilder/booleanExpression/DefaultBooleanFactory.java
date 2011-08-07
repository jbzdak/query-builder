package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

import java.util.Arrays;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultBooleanFactory implements BooleanFactory {


   public BooleanExpressionMarker and(BooleanExpressionMarker... childExpressions){
      return new NAryBooleanExpression(NAryBooleanExpressionType.AND, Arrays.asList(childExpressions));
   }

   public BooleanExpressionMarker or(BooleanExpressionMarker... childExpressios){
      return new NAryBooleanExpression(NAryBooleanExpressionType.OR, Arrays.asList(childExpressios));
   }

   public BooleanExpressionMarker not(BooleanExpressionMarker child){
      return new Not(child);
   }

   public BooleanExpressionMarker isNull(ColumnExpression columnExpression){
      return new UnaryBooleanExpresson(UnaryBooleanExpressionType.IS_NULL, columnExpression);
   }

   public BooleanExpressionMarker isNotNull(ColumnExpression columnExpression){
      return new UnaryBooleanExpresson(UnaryBooleanExpressionType.IS_NON_NULL, columnExpression);
   }

   public BooleanExpressionMarker like(ColumnExpression columnExpression, StringLiteral stringLiteral){
      return new Condition(ConditionType.LIKE, columnExpression, stringLiteral);
   }

   public BooleanExpressionMarker condition(String conditionType, ColumnExpression columnExpression, SQLLiteral<?> stringLiteral){
      return new Condition(conditionType, columnExpression, stringLiteral);
   }






}
