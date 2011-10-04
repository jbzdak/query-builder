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

package cx.ath.jbzdak.sqlbuilder.xml.parameter;

import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "defaultParameter")
public class XmlDefaultParameter extends XmlParameter{


   @Override
   public Parameter<?> createParameter() {
      DefaultParameter defaultParameter = new DefaultParameter(getName());
      defaultParameter.setDefaultValue(getDefaultValue());
      return defaultParameter;
   }
}
