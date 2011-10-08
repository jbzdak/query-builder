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

package cx.ath.jbzdak.sqlbuilder.postgresql.config;

import cx.ath.jbzdak.sqlbuilder.generic.config.Configuration;
import cx.ath.jbzdak.sqlbuilder.generic.config.ConfigurationKey;

/**
 * Created by: Jacek Bzdak
 */
public class PostgreSQLConfig {

   /**
    * Controls how strings are quoted.
    */
   public static final ConfigurationKey<StringQuotingStyle> STRING_QUOTING_STYLE = new ConfigurationKey<StringQuotingStyle>() {
      @Override
      public StringQuotingStyle getDefault(Configuration context) {
         return StringQuotingStyle.UNICODE_PREFER_SINGLE_QUOTES;
      }
   };

   /**
    * If strings are quoted using unicode notation, controls what char is considered an unicode escape
    */
   public static final ConfigurationKey<String> UNICODE_QUOTE_CHAR = new ConfigurationKey<String>() {
      @Override
      public String getDefault(Configuration context) {
         return "\\";
      }
   };


   static {
      ConfigurationKey.registerConfig(PostgreSQLConfig.class);
   }
}
