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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class Configuration {

   private final Map<ConfigurationKey, Object> configuration
           = new HashMap<ConfigurationKey, Object>();


   public <T> T put(ConfigurationKey<T> key, T value){
      return (T) configuration.put(key, value);
   }

   public <T> T get(ConfigurationKey<T> key){
      T result = (T) configuration.get(key);
      if(result == null){
         result = key.getDefault(this);
      }
      return result;
   }
}
