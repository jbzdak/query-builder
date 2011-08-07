package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.ColumnExpression;
import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

/**
 * Created by: Jacek Bzdak
 */
public interface BooleanFactory {

   public static final DefaultBooleanFactory BOOLEAN_FACTORY = new DefaultBooleanFactory();

   BooleanExpressionMarker and(BooleanExpressionMarker... childExpressions);

   BooleanExpressionMarker or(BooleanExpressionMarker... childExpressios);

   BooleanExpressionMarker not(BooleanExpressionMarker child);

   BooleanExpressionMarker isNull(ColumnExpression columnExpression);

   BooleanExpressionMarker isNotNull(ColumnExpression columnExpression);

   BooleanExpressionMarker like(ColumnExpression columnExpression, StringLiteral stringLiteral);

   BooleanExpressionMarker condition(String conditionType, ColumnExpression columnExpression, SQLLiteral<?> stringLiteral);
}
