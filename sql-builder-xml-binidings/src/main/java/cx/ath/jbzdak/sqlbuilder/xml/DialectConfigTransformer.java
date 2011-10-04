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

package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.dialect.IdentifierQuotingStrategy;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import cx.ath.jbzdak.sqlbuilder.generic.EnumTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.NewInstanceTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DialectConfigTransformer implements Transformer<Object, DialectConfigItem>{

   public static final DialectConfigTransformer DIALECT_CONFIG_TRANSFORMER = new DialectConfigTransformer();

   private static final Logger LOGGER = LoggerFactory.getLogger(DialectConfigTransformer.class);

   private final Map<DialectConfigKey, Transformer<?, String>> transformers =
           new HashMap<DialectConfigKey, Transformer<?, String>>();

   public DialectConfigTransformer() {
      transformers.put(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY, EnumTransformer.create(IdentifierQuotingStrategy.class));
      transformers.put(DialectConfigKey.ALIAS_QUOTING_STRATEGY, EnumTransformer.create(IdentifierQuotingStrategy.class));
      transformers.put(DialectConfigKey.TABLE_EXPRESSION_QUOTING_STRATEGY, EnumTransformer.create(IdentifierQuotingStrategy.class));
      transformers.put(DialectConfigKey.PRETTIFY_SQL, EnumTransformer.create(PrettifySQLLevel.class));
      transformers.put(DialectConfigKey.PARAMETER_FACTORY, new NewInstanceTransformer());
      transformers.put(DialectConfigKey.ADDITIONAL_PEERS, new Transformer<Object, String>() {
         public Object transform(String source) {
          throw new UnsupportedOperationException("Customizing of ADDITIONAL_PEERS is not possible for DIALECT_CONFIG");
         }
      });
   }

   public Object transform(DialectConfigItem source) {
      return transformers.get(source.key).transform(source.value);
   }
}
