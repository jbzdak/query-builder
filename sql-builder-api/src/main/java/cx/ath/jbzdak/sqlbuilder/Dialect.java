package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanFactory;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

/**
 * Created by: Jacek Bzdak
 */
public interface Dialect {

   SQLPeer getPeer(IntermediateSQLFactory sqlFactory);

   String getStringQuote();

   String getIdentifierQuote();

   boolean identifierNeedsQuoting(String identifier);

   DialectConfig getDialectConfig();

   Select select();

   LiteralFactory getLiteralFactory();

   BooleanFactory getBooleanFactory();

   /**
    * @return
    */
   ExpressionConfig getDefaultExpressionConfig();

   <T> BoundParameter bindParameter(Parameter<T> source, T value);
}
