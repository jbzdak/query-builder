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

import cx.ath.jbzdak.sqlbuilder.ParameterDescriptor;
import cx.ath.jbzdak.sqlbuilder.ParameterValueDescriptor;

import java.util.Collections;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractParameter<T> implements Parameter<T>{

   String name;

   final String type;

   T defaultValue;

   boolean required;

   String description;

   int index;

   protected AbstractParameter(String type, String name) {
      if(name.startsWith(":")){
         name = name.substring(1);
      }
      this.name = name;
      this.type = type;
   }

   @Override
   public String humanReadableForm(T value) {
      return String.valueOf(value);
   }

   @Override
   public void setIndex(int index) {
      this.index = index;
   }

   @Override
   public int getIndex() {
      return index;
   }

   public boolean isRequired() {
      return required;
   }

   public void setRequired(boolean required) {
      this.required = required;
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

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public List<ParameterValueDescriptor> getValues() {
      return Collections.emptyList();
   }

   @Override
   public int compareTo(ParameterDescriptor o) {
      int result = index - o.getIndex();
      if(result != 0){
         return result;
      }
      return name.compareTo(o.getName());
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof AbstractParameter)) return false;

      AbstractParameter that = (AbstractParameter) o;

      if (name != null ? !name.equals(that.name) : that.name != null) return false;

      return true;
   }

   @Override
   public int hashCode() {
      return name != null ? name.hashCode() : 0;
   }
}
