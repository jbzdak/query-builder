package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class ParameterType {

   public static final String DEFAULTT_PARAMETER = "default";

   public static final String UNQUOTED_STRING_PARAMETER = "unquoted";

   public static final String INTEGER_PARAMETER = SQLLiteralType.INTEGER;

   public static final String FLOAT_PARAMETER = SQLLiteralType.FLOATING_POINT;

   public static final String NUMERIC_PARAMETER = SQLLiteralType.DOUBLE;

   public static final String QUOTED_STRING = SQLLiteralType.STRING;

   public static final String DATE = SQLLiteralType.STRING;

   public static final String DATETIME = SQLLiteralType.DATETIME;

   public static final String SET = SQLLiteralType.SET;





   private ParameterType() {
   }

}
