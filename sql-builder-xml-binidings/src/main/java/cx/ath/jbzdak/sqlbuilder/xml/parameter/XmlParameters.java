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

import cx.ath.jbzdak.sqlbuilder.parameter.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
@XmlType
public class XmlParameters {

   List<XmlParameter> xmlParameters = new ArrayList<XmlParameter>();


   @XmlTransient
   public List<Parameter<?>> getParameters(){
      List<Parameter<?>> params = new ArrayList<Parameter<?>>();
      for (XmlParameter parameter : xmlParameters) {
         params.add(parameter.createParameter());
      }
      return params;
   }

   @XmlElements({
           @XmlElement(name = "parameter", type = XmlDefaultParameter.class),
           @XmlElement(name = "tableParameter", type =XmlTableParameter.class),
           @XmlElement(name = "integerParameter", type =XmlIntegerParameter.class)
   })
   public List<XmlParameter> getXmlParameters() {
      return xmlParameters;
   }

   public void setXmlParameters(List<XmlParameter> xmlParameters) {
      this.xmlParameters = xmlParameters;
   }
}
