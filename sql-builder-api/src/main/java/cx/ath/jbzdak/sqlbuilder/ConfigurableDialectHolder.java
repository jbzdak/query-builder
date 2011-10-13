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
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Created by: Jacek Bzdak
 */
public class ConfigurableDialectHolder {

   ServiceLoader<Dialect> dialectServiceLoader = ServiceLoader.load(Dialect.class);

   List<String> dialectNames;

   public List<String> getDialectNames() {
      if (dialectNames == null) {
         dialectNames = new ArrayList<String>();
         for (Dialect dialect : dialectServiceLoader) {
            dialectNames.add(dialect.dialectName());
         }
      }
      return dialectNames;
   }

   public Dialect getDialect(String name){
      return getDialect(name, new DialectConfig());
   }

   public Dialect getDefaultDialect(){
      return getDefaultDialect(new DialectConfig());
   }

   public Dialect getDialect(String name, DialectConfig dialectConfig){
      if("default".equals(name)){
         return getDefaultDialect(dialectConfig);
      }
      for (Dialect dialect : dialectServiceLoader) {
         if(name.equals(dialect.dialectName())){
            dialect.setDialectConfig(dialectConfig);
            return dialect;
         }
      }

      throw new NoDialectFoundException("Can't find dialect '" + name + "'");
   }

   public Dialect getDefaultDialect(DialectConfig dialectConfig) {
      Dialect d;
      if(dialectServiceLoader.iterator().hasNext()){
         d = dialectServiceLoader.iterator().next();
      } else {
         d = new DefaultDialect(new DialectConfig());
      }
      d.setDialectConfig(dialectConfig);
      return d;
   }
}
