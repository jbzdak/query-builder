package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.*;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.DateLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.DefaultLiteralFactory;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultDialect extends AbstractDialect{

   public DefaultDialect() {
      super(new DialectConfig());
   }

   public DefaultDialect(DialectConfig dialectConfig) {
      super(dialectConfig);
   }

   DefaultLiteralFactory defaultLiteralFactory = new DefaultLiteralFactory();

   DefaultBooleanFactory defaultBooleanFactory = new DefaultBooleanFactory();

   @Override
   protected Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> createTransformerMap() {
      Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> transformerMap = new HashMap<Class, Transformer<SQLPeer, IntermediateSQLFactory>>();

      putPeer(transformerMap, Alias.class, AliasPeer.class);
      putPeer(transformerMap, ColumnExpression.class, ColumnExpressionPeer.class);
      putPeer(transformerMap, SQLLiteral.class, UnquotedLiteralPeer.class);
      putPeer(transformerMap, StringLiteral.class, StringPeer.class);
      putPeer(transformerMap, DateLiteral.class, DatePeer.class);
      putPeer(transformerMap, Table.class, TablePeer.class);
      putPeer(transformerMap, JoinUsing.class, UsingJoinPeer.class);
      putPeer(transformerMap, JoinOn.class, OnJoinPeer.class);
      putPeer(transformerMap, Select.class, SelectPeer.class);
      putPeer(transformerMap, AbstractBinaryBooleanExpression.class, BooleanExpressionPeer.class);
      putPeer(transformerMap, RawString.class, RawStringPeer.class);
      putPeer(transformerMap, NAryBooleanExpression.class, NAryBooleanExpressionPeer.class);
      putPeer(transformerMap, BetweenCondition.class, BetweenConditionPeer.class);
      putPeer(transformerMap, UnaryBooleanExpresson.class, UnaryBooleanExpressionPeer.class);
      putPeer(transformerMap, Not.class, NotPeer.class);
      putPeer(transformerMap, SelectAllExpression.class, SelectAllPeer.class);

      return transformerMap;
   }


   public String getStringQuote() {
      return "'";
   }

   public String getIdentifierQuote(){
      return "\"";
   }

   public LiteralFactory getLiteralFactory() {
      return defaultLiteralFactory;
   }

   public DefaultBooleanFactory getBooleanFactory() {
      return defaultBooleanFactory;
   }

   public boolean identifierNeedsQuoting(String identifier){
      try {
         Charset.forName("ASCII").newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).encode(CharBuffer.wrap(identifier));
         return false;
      } catch (CharacterCodingException e) {
         return true;
      }
   }
}

