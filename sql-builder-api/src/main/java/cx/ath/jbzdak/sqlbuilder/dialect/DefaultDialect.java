package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.*;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.DateLiteral;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;

import java.io.FileReader;
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

   @Override
   protected Map<Class, Transformer<SQLPeer, SQLFactory>> createTransformerMap() {
      Map<Class, Transformer<SQLPeer, SQLFactory>> transformerMap = new HashMap<Class, Transformer<SQLPeer, SQLFactory>>();

      put(transformerMap, Alias.class, AliasPeer.class);
      put(transformerMap, ColumnExpression.class, ColumnExpressionPeer.class);
      put(transformerMap, SQLLiteral.class, UnquotedLiteralPeer.class);
      put(transformerMap, StringLiteral.class, StringPeer.class);
      put(transformerMap, DateLiteral.class, DatePeer.class);
      put(transformerMap, Table.class, TablePeer.class);
      put(transformerMap, JoinUsing.class, UsingJoinPeer.class);
      put(transformerMap, JoinOn.class, OnJoinPeer.class);
      put(transformerMap, Select.class, SelectPeer.class);
      put(transformerMap, BooleanExpression.class, BooleanExpressionPeer.class);
      put(transformerMap, RawString.class, RawStringPeer.class);

      return transformerMap;
   }

   protected static void put(Map<Class, Transformer<SQLPeer, SQLFactory>> map, Class<? extends SQLFactory> objectClass, Class<? extends SQLPeer> peerClass){
      map.put(objectClass, new DefaultTransformer(peerClass));
   }

   public String getStringQuote() {
      return "'";
   }

   public String getIdentifierQuote(){
      return "\"";
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

