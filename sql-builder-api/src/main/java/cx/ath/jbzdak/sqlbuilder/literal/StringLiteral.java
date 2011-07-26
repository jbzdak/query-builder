package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class StringLiteral extends SQLLiteral<String>{

   public StringLiteral(String literalValue) {
      super(SQLLiteralType.STRING, literalValue);
   }


}
