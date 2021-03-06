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

import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;
import cx.ath.jbzdak.sqlbuilder.ParameterDescriptor;

/**
 * Created by: Jacek Bzdak
 */
public interface Parameter<T>  extends ParameterDescriptor{

   String getName();

   T getDefaultValue();

   String getType();

   void setDefaultValue(T defaultValue);

   void setDefaultValueAsString(String defaultValue);

   T fromObject(Object o);

   T fromString(String string);
   
   String humanReadableForm(T value);

   boolean isRequired();

   void setRequired(boolean required);

   /**
    * Default implementation:
    * <code>
    *       @Override
   public int compareTo(Parameter o) {
      int result = index - o.getIndex();
      if(result != 0){
      return result;
      }
      return name.compareTo(o.getName());
   }

    * </code>
    * @param o
    * @return
    */
   int compareTo(ParameterDescriptor o);
}
