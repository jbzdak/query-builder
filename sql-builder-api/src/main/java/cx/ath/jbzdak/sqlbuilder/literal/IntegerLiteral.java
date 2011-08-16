package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class IntegerLiteral extends SQLLiteral<Integer> {

   public IntegerLiteral() {
   }

   public IntegerLiteral(Integer literalValue) {
      super(SQLLiteralType.INTEGER, literalValue);
   }

}
