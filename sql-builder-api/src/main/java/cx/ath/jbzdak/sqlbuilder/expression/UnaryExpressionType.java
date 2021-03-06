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

package cx.ath.jbzdak.sqlbuilder.expression;

import fakeEnum.FakeEnum;

import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public class UnaryExpressionType {

   /**
    * Noop expression. For example if foo and baz are boolean columns ... WHERE foo and baz.
    *
    *
    */
   public static final String NOOP = "";


   public static final String IS_NULL = "IS NULL";

   public static final String IS_NON_NULL = "IS NOT NULL";

   public static final String NOT = "NOT";

   public static final String MINUS = "-";


   public static final FakeEnum<String> FAKE_ENUM = new FakeEnum<String>(UnaryExpressionType.class, String.class);

   public static String nameOf(String value) {
      return FAKE_ENUM.nameOf(value);
   }

   public static Collection<? extends String> values() {
      return FAKE_ENUM.values();
   }

   public static String valueOf(String s) {
      return FAKE_ENUM.valueOf(s);
   }
}
