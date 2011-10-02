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

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractParameter<T> implements Parameter<T> {

   String name;

   final String type;

   T defaultValue;

   public abstract T fromString(String string);

   public abstract T fromObject(Object o);

   protected AbstractParameter(String type, String name) {
      if(name.startsWith(":")){
         name = name.substring(1);
      }
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public T getDefaultValue() {
      return defaultValue;
   }

   public void setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
   }

   public void setDefaultValueAsString(String defaultValue) {
      this.defaultValue = fromString(defaultValue);
   }

   public String getType() {
      return type;
   }
}
