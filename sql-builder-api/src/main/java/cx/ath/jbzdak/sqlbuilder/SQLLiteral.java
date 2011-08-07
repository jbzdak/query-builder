package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class SQLLiteral<T> extends SQLObject implements SQLFactory {

   private String literalType;

   private T literalValue;


   public SQLLiteral() {
   }

   public static SQLLiteral<Integer> create(Integer value){
      return new SQLLiteral<Integer>(SQLLiteralType.INTEGER, value);

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
