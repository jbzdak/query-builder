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

@XmlRootElement(name = "queryCollection")
public class XmlQueryCollection extends AbstractXmlQueryCollection {

   public static XmlQueryCollection create(InputStream inputStream) throws JAXBException {
      Unmarshaller unmarshaller = JaxbEntryPoint.DEFAULT_CONTEXT.createUnmarshaller();
      XmlQueryCollection unmarshal = (XmlQueryCollection) unmarshaller.unmarshal(inputStream);
      unmarshal.parsingFinished();
      return unmarshal;
   }

   public XmlQueryCollection() {
      XmlParsingContext.setXmlQueryCollection(this);
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


}
