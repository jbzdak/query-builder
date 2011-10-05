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

package cx.ath.jbzdak.sqlbuilder.xml.query;

import cx.ath.jbzdak.sqlbuilder.Namespace;
import cx.ath.jbzdak.sqlbuilder.xml.QueryTag;
import cx.ath.jbzdak.sqlbuilder.xml.XmlExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlParameters;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by: Jacek Bzdak
 */
@XmlType
public abstract class AbstractQuery implements QueryTag{

   String name;

   String description;

   XmlParameters parameters = new XmlParameters();

   XmlExpressionConfig xmlExpressionConfig = new XmlExpressionConfig();

   public AbstractQuery() {
   }

   protected AbstractQuery(String name, String description) {
      this.name = name;
      this.description = description;
   }

   @XmlAttribute(required = true)
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @XmlElement(required = false, nillable = true, namespace = Namespace.NAMESPACE)
   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @XmlElement(required = false, nillable = true, namespace = Namespace.NAMESPACE)
   public XmlParameters getParameters() {
      return parameters;
   }

   public void setParameters(XmlParameters parameters) {
      this.parameters = parameters;
   }

   @XmlElement(required = false, nillable = true, namespace = Namespace.NAMESPACE, name = "expressionConfig")
   public XmlExpressionConfig getXmlExpressionConfig() {
      return xmlExpressionConfig;
   }

   public void setXmlExpressionConfig(XmlExpressionConfig xmlExpressionConfig) {
      this.xmlExpressionConfig = xmlExpressionConfig;
   }
}
