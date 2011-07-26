package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class SQLLiteral<T> extends SQLObject implements SQLFactory{

   private String literalType;

   private T literalValue;


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
}
