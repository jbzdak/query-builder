package cx.ath.jbzdak.sqlbuilder;

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
}
