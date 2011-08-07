package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;

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
         return d.getIdentifierQuote() + identifier + d.getIdentifierQuote();
      }
   };

   /**
    *
    * @param d
    * @param identifier
    * @return NULL when identifier is null, quoted string otherwise (other code depend on this assumption)
    */
   public abstract String quoteIdentifier(Dialect d, String identifier);

   public String quoteIdentifier(ExpressionContext d, String identifier){
      return quoteIdentifier(d.getDialect(), identifier);
   }

   public String quoteIdentifier(RenderingContext d, String identifier){
      return quoteIdentifier(d.getDialect(), identifier);
   }
}
