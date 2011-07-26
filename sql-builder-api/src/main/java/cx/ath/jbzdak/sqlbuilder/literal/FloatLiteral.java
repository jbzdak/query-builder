package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class FloatLiteral extends SQLLiteral<Double>{

   public FloatLiteral() {
   }

   public FloatLiteral(Double literalValue) {
      super(SQLLiteralType.FLOATING_POINT, literalValue);
   }
}
