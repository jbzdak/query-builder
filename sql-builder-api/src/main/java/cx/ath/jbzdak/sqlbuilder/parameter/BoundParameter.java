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

import cx.ath.jbzdak.sqlbuilder.*;

/**
 * Created by: Jacek Bzdak
 */
public class BoundParameter<T> extends PeerIntermediateSQLObject {

   private Parameter<T> parent;

   private T value;

   public void setParent(Parameter<T> parent) {
      this.parent = parent;
   }

   public T getValue() {
      if(value == null){
         return parent.getDefaultValue();
      }
      return value;
   }

   protected Parameter<T> getParent() {
      return parent;
   }

   public String getType() {
      return parent.getType();
   }

   public void setValue(T value) {
      T oldValue = this.getValue();
      this.value = value;
      propertyChangeSupport.firePropertyChange("value", oldValue, this.getValue());
   }

   public void setValueFromObject(String value) {
      this.value = parent.fromObject((Object) value);
   }

   public boolean isBound(){
      return getValue() !=null;
   }

}
