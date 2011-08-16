package cx.ath.jbzdak.sqlbuilder;

import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class RawString extends SQLObject{


   public RawString(String rawString) {
      this.rawString = rawString;
   }

   String rawString;

   public String getRawString() {
      return rawString;
   }

   public Set<String> collectParameterNames() {
      return expressionContext.collectParameterNames(rawString);
   }
}
