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

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.DialectHolder;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfigKey;
import cx.ath.jbzdak.sqlbuilder.generic.BooleanTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.DialectTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.NoopTransformer;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionConfigTransformer implements Transformer<Object, ExpressionConfigItem>{

   public static final ExpressionConfigTransformer EXPRESSION_CONFIG_TRANSFORMER = new ExpressionConfigTransformer();

   private final Map<ExpressionConfigKey, Transformer<?, String>> transformerMap
           = new EnumMap<ExpressionConfigKey, Transformer<?, String>>(ExpressionConfigKey.class);

   private ExpressionConfigTransformer() {
      transformerMap.put(ExpressionConfigKey.PARAMETER_REGEXP_PATTERN, new NoopTransformer<String>());
      transformerMap.put(ExpressionConfigKey.DIALECT, new DialectTransformer());
      transformerMap.put(ExpressionConfigKey.IGNORE_EXPRESSIONS_WITH_UNBOUND_PARAMS, new BooleanTransformer());
   }

   Object transform(ExpressionConfigKey key, String value) {
      return transformerMap.get(key).transform(value);
   }

   public Object transform(ExpressionConfigItem source) {
      return transform(source.key, source.value);
   }


}
