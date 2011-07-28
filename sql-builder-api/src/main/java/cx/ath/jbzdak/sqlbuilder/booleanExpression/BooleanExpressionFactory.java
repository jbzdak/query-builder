package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.Dialect;

/**
 * Created by: Jacek Bzdak
 */
public class BooleanExpressionFactory {

   public static BooleanExpressionA and(Dialect d){
      return new BooleanExpressionA(d, BooleanExpressionType.AND);
   }

   public static BooleanExpressionA and(){
      return new BooleanExpressionA(BooleanExpressionType.AND);
   }







}
