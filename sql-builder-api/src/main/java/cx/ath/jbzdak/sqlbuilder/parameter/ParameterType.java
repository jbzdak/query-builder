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

package cx.ath.jbzdak.sqlbuilder.parameter;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLLiteralType;

/**
 * Created by: Jacek Bzdak
 */
public class ParameterType {

   public static final String DEFAULTT_PARAMETER = "default";

   public static final String UNQUOTED_STRING_PARAMETER = "unquoted";

   public static final String TABLE_PARAMETER = "table";

   public static final String INTEGER_PARAMETER = SQLLiteralType.INTEGER;

   public static final String FLOAT_PARAMETER = SQLLiteralType.FLOATING_POINT;

   public static final String NUMERIC_PARAMETER = SQLLiteralType.DOUBLE;

   public static final String QUOTED_STRING = SQLLiteralType.STRING;

   public static final String DATE = SQLLiteralType.DATE;

   public static final String DATETIME = SQLLiteralType.DATETIME;

   public static final String SET = SQLLiteralType.SET;





   private ParameterType() {
   }

}
