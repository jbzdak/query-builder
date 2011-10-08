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

package cx.ath.jbzdak.sqlbuilder.generic.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by: Jacek Bzdak
 */
public abstract class ConfigurationKey<T> {

   public static void registerConfig(Class clazz){
      for (Field field : clazz.getFields()) {
         if(ConfigurationKey.class.isAssignableFrom(field.getType())
                 && Modifier.isStatic(field.getModifiers())
                 && Modifier.isFinal(field.getModifiers())
                 && Modifier.isPublic(field.getModifiers())){
            try {
               ConfigurationKey configKey = (ConfigurationKey) field.get(null);
               configKey.parentClass = clazz;
               configKey.keyName = field.getName();
            } catch (IllegalAccessException e) {
               throw new RuntimeException(e); //Should not happen, field is public
            }
         }
      }
   }

   volatile String keyName;

   volatile Class parentClass;

   public abstract T getDefault(Configuration context);

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof ConfigurationKey)) return false;

      ConfigurationKey configKey = (ConfigurationKey) o;

      if (keyName != null ? !keyName.equals(configKey.keyName) : configKey.keyName != null) return false;
      if (parentClass != null ? !parentClass.equals(configKey.parentClass) : configKey.parentClass != null)
         return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = keyName != null ? keyName.hashCode() : 0;
      result = 31 * result + (parentClass != null ? parentClass.hashCode() : 0);
      return result;
   }
}
