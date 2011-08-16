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

package cx.ath.jbzdak.sqlbuilder.literal;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;

import java.sql.Date;

/**
 * Created by: Jacek Bzdak
 */
public interface LiteralFactory {

   SQLLiteral<Integer> create(int value);

   SQLLiteral<Float> create(float value);

   SQLLiteral<Double> create(double value);

   SQLLiteral<Integer> create(Integer value);

   SQLLiteral<Double> create(Number value);

   SQLLiteral<Date> create(Date date);

   SQLLiteral<Date> createDatetime(Date date);

   SQLLiteral<Date> createDate(Date date);

   SQLLiteral<String> create(String s);

   SQLLiteral create(String literalType, Object value);
}
