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

package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.*;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfigKey;
import cx.ath.jbzdak.sqlbuilder.xml.query.AbstractQuery;
import cx.ath.jbzdak.sqlbuilder.xml.query.XmlSelect;
import cx.ath.jbzdak.sqlbuilder.xml.query.XmlSimpleQuery;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.InputStream;
import java.util.*;

/**
 * Created by: Jacek Bzdak
 */

@XmlType(propOrder = {"xmlDialectConfig", "xmlDefaultExpressionConfig", "queryTags"})
@XmlRootElement(name = "queryCollection")
public class XmlQueryCollection implements QueryCollection{



  public static XmlQueryCollection create(InputStream inputStream) throws JAXBException {
     Unmarshaller unmarshaller = JaxbEntryPoint.DEFAULT_CONTEXT.createUnmarshaller();
     XmlQueryCollection unmarshal = (XmlQueryCollection) unmarshaller.unmarshal(inputStream);
     unmarshal.parsingFinished();
     return unmarshal;
  }

   String xmlDialect;

   @XmlTransient
   private Dialect dialect;

   XmlDialectConfig xmlDialectConfig;

   @XmlTransient
   private DialectConfig dialectConfig;


   XmlExpressionConfig xmlDefaultExpressionConfig;

   ExpressionConfig defaultExpressionConfig;

   List<AbstractQuery> queryTags = new ArrayList<AbstractQuery>();

   Map<String, QueryTag> queries;

   public XmlQueryCollection() {
      XmlParsingContext.setXmlQueryCollection(this);
   }

   public void parsingFinished(){
      XmlParsingContext.remove();
   }

   public void prepare(){
   }

   @XmlAttribute(required = true, name = "dialect")
   public String getXmlDialect() {
      return xmlDialect;
   }

   public void setXmlDialect(String xmlDialect) {
      this.xmlDialect = xmlDialect;
   }

   @XmlElement(nillable = true, required = false, name = "dialectConfig")
   public XmlDialectConfig getXmlDialectConfig() {
      return xmlDialectConfig;
   }

   public void setXmlDialectConfig(XmlDialectConfig xmlDialectConfig) {
      this.xmlDialectConfig = xmlDialectConfig;
   }

   @XmlElements({
           @XmlElement(name = "simpleQuery", type = XmlSimpleQuery.class),
           @XmlElement(name = "select", type = XmlSelect.class)
   })
   public List<AbstractQuery> getQueryTags() {
      return queryTags;
   }

   public void setQueryTags(List<AbstractQuery> queryTags) {
      this.queryTags = queryTags;
   }

   @XmlTransient
   public Map<String, QueryTag> getQueries() {
      if (queries == null) {
         queries = new HashMap<String, QueryTag>();
         for (QueryTag queryTag : queryTags) {
            queries.put(queryTag.getName(), queryTag);
         }
      }
      return queries;
   }

   public void setQueries(Map<String, QueryTag> queries) {
      this.queries = queries;
   }

   public SQLFactory getQuery(String name){
      return queries.get(name).createQuery();
   }

   public Dialect getDialect() {
      if (dialect == null) {
         ConfigurableDialectHolder configurableDialectHolder = new ConfigurableDialectHolder();
         dialect = configurableDialectHolder.getDialect(xmlDialect, dialectConfig);
      }
      return dialect;
   }

   @XmlTransient
   public ExpressionConfig getDefaultExpressionConfig() {
      if (defaultExpressionConfig == null) {
         if(xmlDefaultExpressionConfig!=null){
            defaultExpressionConfig = xmlDefaultExpressionConfig.createConfig();
         }else{
            defaultExpressionConfig = new ExpressionConfig();
         }
         defaultExpressionConfig.put(ExpressionConfigKey.DIALECT, getDialect());
      }
      return defaultExpressionConfig;
   }

   public DialectConfig getDialectConfig() {
      if (dialectConfig == null) {
         dialectConfig = xmlDialectConfig.createDialectConfig();
      }
      return dialectConfig;
   }

   @XmlElement(nillable = true, required = false, name = "defaultExpressionConfig")
   public XmlExpressionConfig getXmlDefaultExpressionConfig() {
      return xmlDefaultExpressionConfig;
   }

   public void setXmlDefaultExpressionConfig(XmlExpressionConfig xmlDefaultExpressionConfig) {
      this.xmlDefaultExpressionConfig = xmlDefaultExpressionConfig;
   }

   public Set<String> getQueryNames() {
      return queries.keySet();
   }




   public void setJdbcUrl(String jdbc) {
      //To change body of implemented methods use File | Settings | File Templates.
   }

   public void setCredentials(String username, String password) {
      //To change body of implemented methods use File | Settings | File Templates.
   }
}
