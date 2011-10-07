package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.IdenitfierPart;
import cx.ath.jbzdak.sqlbuilder.dialect.QuotingManager;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractQuotingManager implements QuotingManager{

   public String quoteIdentifier(CharSequence ident, IdenitfierPart idenitfierPart) {
      return quoteIdentifier(ident, IdentifierQuotingStrategy.DEFAULT, idenitfierPart);
   }

   public String quoteIdentifier(CharSequence ident) {
      return quoteIdentifier(ident, IdentifierQuotingStrategy.DEFAULT, IdenitfierPart.UNKNOWM);
   }

   public boolean identifierNeedsQuoting(CharSequence ident) {
      return identifierNeedsQuoting(ident, IdenitfierPart.UNKNOWM);
   }
}
