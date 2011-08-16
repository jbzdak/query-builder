package cx.ath.jbzdak.sqlbuilder.expressionConfig;

import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public enum ExpressionConfigKey {
   PARAMETER_REGEXP_PATTERN(){
      @Override
      public Object getDefault(ExpressionConfig config) {
         return Pattern.compile(":[\\w\\W\\d\\-_]+");
      }
   }
   ;

   public abstract Object getDefault(ExpressionConfig config);

}