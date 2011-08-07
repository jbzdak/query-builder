package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.AbstractBinaryBooleanExpression;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.BetweenCondition;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.NAryBooleanExpression;
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

   @Override
   protected Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> createTransformerMap() {
      Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> transformerMap = new HashMap<Class, Transformer<SQLPeer, IntermediateSQLFactory>>();

      put(transformerMap, Alias.class, AliasPeer.class);
      put(transformerMap, ColumnExpression.class, ColumnExpressionPeer.class);
      put(transformerMap, SQLLiteral.class, UnquotedLiteralPeer.class);
      put(transformerMap, StringLiteral.class, StringPeer.class);
      put(transformerMap, DateLiteral.class, DatePeer.class);
      put(transformerMap, Table.class, TablePeer.class);
      put(transformerMap, JoinUsing.class, UsingJoinPeer.class);
      put(transformerMap, JoinOn.class, OnJoinPeer.class);
      put(transformerMap, Select.class, SelectPeer.class);
      put(transformerMap, AbstractBinaryBooleanExpression.class, BooleanExpressionPeer.class);
      put(transformerMap, RawString.class, RawStringPeer.class);
      put(transformerMap, NAryBooleanExpression.class, NAryBooleanExpressionPeer.class);
      put(transformerMap, BetweenCondition.class, BetweenConditionPeer.class);

      return transformerMap;
   }

   protected static void put(Map<Class, Transformer<SQLPeer, IntermediateSQLFactory>> map, Class<? extends IntermediateSQLFactory> objectClass, Class<? extends SQLPeer> peerClass){
      map.put(objectClass, new DefaultTransformer(peerClass));
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

   public boolean identifierNeedsQuoting(String identifier){
      try {
         Charset.forName("ASCII").newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).encode(CharBuffer.wrap(identifier));
         return false;
      } catch (CharacterCodingException e) {
         return true;
      }
   }
}

