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

import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundDefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundTableParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.bound.BoundUnquotedParameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultBoundParameterFactory implements Transformer<BoundParameter, Parameter<?>> {

   protected Map<String, Factory<? extends BoundParameter>> typeMap = new HashMap<String, Factory<? extends BoundParameter>>();

   public DefaultBoundParameterFactory() {
      registerDefaultMappings();
   }

   public DefaultBoundParameterFactory(Map<String, Factory<? extends BoundParameter>> typeMap) {
      this.typeMap = typeMap;
   }

   protected void registerDefaultMappings(){
      registerType(ParameterType.TABLE_PARAMETER, BoundTableParameter.class);
      registerType(ParameterType.UNQUOTED_STRING_PARAMETER, BoundUnquotedParameter.class);
      registerType(ParameterType.DEFAULTT_PARAMETER, BoundDefaultParameter.class);
   }

   public void registerType(String type, final Class<? extends BoundParameter> boundParameter){
      typeMap.put(type, new Factory<BoundParameter>() {
         public BoundParameter create() {
            try {
               return boundParameter.newInstance();
            } catch (InstantiationException e) {
               throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
               throw new RuntimeException(e);
            }
         }
      });
   }

   public BoundParameter transform(Parameter<?> source) {
      Factory<? extends BoundParameter> factory = typeMap.get(source.getType());
      BoundParameter bound;
      if(factory == null){
         bound = new BoundParameter();
      }else{
         bound = factory.create();
      }
      bound.setParent(source);
      return bound;
   }
}



