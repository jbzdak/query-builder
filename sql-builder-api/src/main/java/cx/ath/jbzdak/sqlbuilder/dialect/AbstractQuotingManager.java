package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.IdenitfierPart;
import cx.ath.jbzdak.sqlbuilder.dialect.QuotingManager;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractQuotingManager implements QuotingManager{

   protected final DialectConfig dialectConfig;

   protected AbstractQuotingManager(DialectConfig dialectConfig) {
      this.dialectConfig = dialectConfig;
   }

   public String quoteIdentifier(CharSequence ident, IdenitfierPart idenitfierPart) {
      return quoteIdentifier(ident, IdentifierQuotingStrategy.DEFAULT, idenitfierPart);
   }



   public String quoteIdentifier(CharSequence ident) {
      return quoteIdentifier(ident, IdentifierQuotingStrategy.DEFAULT, IdenitfierPart.UNKNOWM);
   }

   public boolean identifierNeedsQuoting(CharSequence ident) {
      return identifierNeedsQuoting(ident, IdenitfierPart.UNKNOWM);
   }

   @Override
   public String quoteDate(Date quote) {
      DateFormat format = new SimpleDateFormat((String) dialectConfig.getConfig(DialectConfigKey.OUTPUT_DATE_FORMAT));
      return quoteString(format.format(quote));
   }
}
