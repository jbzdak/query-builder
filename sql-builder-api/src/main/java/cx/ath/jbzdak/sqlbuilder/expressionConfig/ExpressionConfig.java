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

package cx.ath.jbzdak.sqlbuilder.expressionConfig;

import cx.ath.jbzdak.sqlbuilder.Dialect;

import java.util.EnumMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionConfig {

   Dialect dialect;

   private final Map<ExpressionConfigKey, Object> values = new EnumMap<ExpressionConfigKey, Object>(ExpressionConfigKey.class);

   public ExpressionConfig() {
   }

   public ExpressionConfig(ExpressionConfig expressionConfig) {
      values.putAll(expressionConfig.values);
   }

   public Object get(ExpressionConfigKey key){
      if(values.containsKey(key)){
         return values.get(key);
      }
      return key.getDefault(this);
   }

   public Object put(ExpressionConfigKey key, Object value) {
      return values.put(key, value);
   }

}
