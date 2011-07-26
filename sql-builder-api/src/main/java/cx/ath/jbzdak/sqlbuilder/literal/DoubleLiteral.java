package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class DoubleLiteral extends SQLLiteral<Double>{

   public DoubleLiteral(Double literalValue) {
      super(SQLLiteralType.DOUBLE, literalValue);
   }



}
