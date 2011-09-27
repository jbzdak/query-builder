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

package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.dialect.DefaultDialect;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

/**
 * Created by: Jacek Bzdak
 */
public class DialectHolder {

   private static final Internal INTERNAL = new Internal();


   public static Dialect getDefaultDialect(){
      return INTERNAL.getDefaultDialect();
   }

   public static Dialect getByName(String name){
      return INTERNAL.getByName(name);
   }

   private static class Internal{

      ServiceLoader<Dialect> serviceLoader;

      Map<String, Dialect> dialectMap;

      private Internal() {
         serviceLoader = ServiceLoader.load(Dialect.class);
      }

      public Dialect getDefaultDialect(){
         try {
            return serviceLoader.iterator().next();
         } catch (NoSuchElementException e) {
            return new DefaultDialect();
         }
      }


      public synchronized Dialect getByName(String name){
         if(dialectMap == null){
            dialectMap = new HashMap<String, Dialect>();
            for (Dialect dialect : serviceLoader) {
               dialectMap.put(dialect.dialectName(), dialect);
            }
         }
         Dialect dialect = dialectMap.get(name);
         if(dialect == null){
            throw new NoDialectFoundException("Couldn't find dialect '" + name + "'");
         }
         return dialect;
      }


   }


}
