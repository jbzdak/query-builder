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
public class BinaryExpressionType {

   public static final String LIKE = "LIKE";

   public static final String EQUALS = "=";

   public static final String NE = "<>";

   public static final String LT = "<";

   public static final String GT = ">";

   public static final String LTE = "<=";

   public static final String GTE = ">=";

   public static final String IN = "IN";

   public static final String DIVIDE = "/";

   public static final String MINUS = "/";

   private static final FakeEnum<String> FAKE_ENUM = new FakeEnum<String>(BinaryExpressionType.class, String.class);

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
