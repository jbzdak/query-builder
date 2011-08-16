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

package cx.ath.jbzdak.sqlbuilder.dialect;

import cx.ath.jbzdak.sqlbuilder.IntermediateSQLFactory;
import cx.ath.jbzdak.sqlbuilder.generic.Factory;
import cx.ath.jbzdak.sqlbuilder.generic.Transformer;
import cx.ath.jbzdak.sqlbuilder.literal.StringLiteral;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import javax.management.monitor.StringMonitor;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class DefaultParameterFactory  implements Transformer<BoundParameter, Parameter> {

   Map<String, Factory<? extends BoundParameter>> typeMap;

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

   public BoundParameter transform(Parameter source) {
      BoundParameter bound = typeMap.get(source.getType()).create();
      bound.setParent(source);
      return bound;
   }
}



