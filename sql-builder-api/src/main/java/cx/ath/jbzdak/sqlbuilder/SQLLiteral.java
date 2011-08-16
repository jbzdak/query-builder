package cx.ath.jbzdak.sqlbuilder;

import java.util.Collections;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public abstract class SQLLiteral<T> extends SQLObject implements SQLFactory {

   protected String literalType;

   protected T literalValue;


   public SQLLiteral() {
   }

   public SQLLiteral(String literalType, T literalValue) {
      this.literalType = literalType;
      this.literalValue = literalValue;
   }

   public String getLiteralType() {
      return literalType;
   }

   public T getLiteralValue() {
      return literalValue;
   }

   public void setLiteralType(String literalType) {
      this.literalType = literalType;
   }

   public void setLiteralValue(T literalValue) {
      this.literalValue = literalValue;
   }

   public Set<String> collectParameterNames() {
      return Collections.emptySet();
   }
}
