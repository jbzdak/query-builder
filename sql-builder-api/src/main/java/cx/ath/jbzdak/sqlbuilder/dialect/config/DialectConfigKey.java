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

package cx.ath.jbzdak.sqlbuilder.dialect.config;

import cx.ath.jbzdak.sqlbuilder.Dialect;
import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.IdentifierQuotingStrategy;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by: Jacek Bzdak
 */
@XmlEnum
public enum DialectConfigKey {
   @XmlEnumValue("identifier-quoting-strategy")
   IDENTIFIER_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return IdentifierQuotingStrategy.DEFAULT;
      }
   },
   @XmlEnumValue("alias-quoting-strategy")
   ALIAS_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return d.getDialectConfig().getConfig(IDENTIFIER_QUOTING_STRATEGY);
      }
   },
   @XmlEnumValue("table-quoting-strategy")
   TABLE_EXPRESSION_QUOTING_STRATEGY{
      @Override
      public Object getDefault(Dialect d) {
         return d.getDialectConfig().getConfig(IDENTIFIER_QUOTING_STRATEGY);
      }
   },
   @XmlEnumValue("prettify-sql")
   PRETTIFY_SQL{
      @Override
      public Object getDefault(Dialect d) {
         return PrettifySQLLevel.MINIMAL;
      }
   },
   /**
    * This key contains an instance of {@code Map<Class<? extends SQLFactory>, Class<? extends SQLPeer>>}
    */
   ADDITIONAL_PEERS{
      @Override
      public Object getDefault(Dialect d) {
         return new HashMap<Class<? extends IntermediateSQLFactory>, Class<? extends SQLPeer>>();
      }
   },
   @XmlEnumValue("parameter-factory")
   PARAMETER_FACTORY{
      @Override
      public Object getDefault(Dialect d) {
         return null;
      }
   },
   @XmlEnumValue("default-input-date-formats")
   DEFAULT_INPUT_DATE_FORMATS{
      @Override
      public Object getDefault(Dialect d) {
         return Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy");
      }
   },
   @XmlEnumValue("output-date-format")
   OUTPUT_DATE_FORMAT{
      @Override
      public Object getDefault(Dialect d) {
         return "yyyy-MM-dd";
      }
   };


   public abstract Object getDefault(Dialect d);


}
