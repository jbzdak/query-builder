/*
 * Copyright (c) 2011 for Jacek Bzdak
 *
 * This file is part of query builder.
 *
 * Query builder is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Query builder is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Query builder.  If not, see <http://www.gnu.org/licenses/>.
 */

package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.booleanExpression.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.InvalidConfigurationException;
import cx.ath.jbzdak.sqlbuilder.dialect.peer.*;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.*;
import cx.ath.jbzdak.sqlbuilder.parameter.*;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundDefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundTableParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundUnquotedParameter;

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

   public static final String DEFAULT_DIALECT_NAME = "default";

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
      putPeer(transformerMap, BoundTableParameter.class, TableParameterPeer.class);
      putPeer(transformerMap, BoundUnquotedParameter.class, UnquotedParameterPeer.class);
      putPeer(transformerMap, BoundParameter.class, ParameterPeer.class);
      putPeer(transformerMap, ParameterLiteral.class, ParameterLiteralPeer.class);
      putPeer(transformerMap, BoundDefaultParameter.class, DefaultParameterPeer.class);
      putPeer(transformerMap, IntegerLiteral.class, IntegerPeer.class);

      return transformerMap;
   }

   public String dialectName() {
      return DEFAULT_DIALECT_NAME;
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

   @Override
   protected Transformer<BoundParameter, AbstractParameter<?>> createDefaultParameterFactory() {
      return new DefaultParameterFactory();
   }

   @Override
   protected QuotingManager createDefaultQuotingManager() {
      return new DefaultQuotingManager(getDialectConfig(), "\"", "\"", "'", "''" );
   }

}

