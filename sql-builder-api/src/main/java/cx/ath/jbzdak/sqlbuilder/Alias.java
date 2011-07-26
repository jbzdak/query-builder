package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class Alias extends SQLObject implements SQLFactory, Identifier{

   String alias;

   public String getAlias() {
      return alias;
   }

   public Alias(String alias) {
      this.alias = alias;
   }
}
