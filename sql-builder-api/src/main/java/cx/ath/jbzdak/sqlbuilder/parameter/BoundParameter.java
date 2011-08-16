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
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

import java.util.Collections;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public class BoundParameter<T> extends IntermediateSQLObject{

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

   public void setValue(T value) {
      this.value = value;
   }

   public void setValueFromObject(String value) {
      this.value = parent.fromObject(value);
   }

   public boolean isBound(){
      return getValue() !=null;
   }

   @Override
   public void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      if(!isBound()){
         throw new IllegalStateException("Parameter " + parent.getName() + "is nod bound but expression tries to render it") ;
      }

      super.appendTo(renderingContext, stringBuilder);
   }

   public Set<String> collectParameterNames() {
      return Collections.emptySet();
   }

}