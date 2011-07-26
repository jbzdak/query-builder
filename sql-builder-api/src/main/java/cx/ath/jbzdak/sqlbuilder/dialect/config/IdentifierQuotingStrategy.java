package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;

/**
 * Created by: Jacek Bzdak
 */
public enum IdentifierQuotingStrategy {
   NEVER{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         return identifier;
      }
   },
   WHEN_NEEDED{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         if(identifier == null){
            return null;
         }
         if(d.identifierNeedsQuoting(identifier)){
            return ALWAYS.quoteIdentifier(d, identifier);
         }else{
            return NEVER.quoteIdentifier(d, identifier);
         }
      }
   },
   DEFAULT{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         throw  new AbstractMethodError();
      }
   },
   ALWAYS{
      @Override
      public String quoteIdentifier(Dialect d, String identifier) {
         if(identifier == null){
            return null;
         }
         return d.getStringQuote() + identifier + d.getStringQuote();
      }
   };

   /**
    *
    * @param d
    * @param identifier
    * @return NULL when identifier is null, quoted string otherwise (other code depend on this assumption)
    */
   public abstract String quoteIdentifier(Dialect d, String identifier);
}
