package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanExpression;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * Created by: Jacek Bzdak
 */
public interface Dialect {

   SQLPeer getPeer(SQLFactory sqlFactory);

   String getStringQuote();

   String getIdentifierQuote();

   boolean identifierNeedsQuoting(String identifier);

   DialectConfig getDialectConfig();

   Select select();

   BooleanExpression booleanExpression();
}
