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

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.PrettifySQLLevel;
import cx.ath.jbzdak.sqlbuilder.xml.DialectConfigItem;
import cx.ath.jbzdak.sqlbuilder.xml.XmlDialectConfig;
import cx.ath.jbzdak.sqlbuilder.xml.XmlQueryCollection;
import cx.ath.jbzdak.sqlbuilder.xml.query.XmlSimpleQuery;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/**
 * Created by: Jacek Bzdak
 */
public class MarshallerTests {

   @Before
   public void setUp() throws Exception {
      marshaller = QueryCollectionFactory.DEFAULT_CONTEXT.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//      marshaller.setProperty(Marshaller.JAXB_ENCODING, );

   }

   private String marshall(Object o) throws JAXBException {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      marshaller.marshal(o, outputStream);
      return new String(outputStream.toByteArray(), Charset.forName("UTF-8"));
   }

   private Marshaller marshaller;

   @Test
   public void testName() throws Exception {
      XmlQueryCollection queryCollection = new XmlQueryCollection();
      queryCollection.setXmlDialect("default");
      XmlDialectConfig dialectConfig = new XmlDialectConfig();
      dialectConfig.getConfigItems().add(new DialectConfigItem(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.PRETTY.toString()));
      queryCollection.setXmlDialectConfig(dialectConfig);
      queryCollection.getQueryTags().add(new XmlSimpleQuery("foo-query-name", "Query that does foo", "SELECT * FROM foo"));
      System.out.println(marshall(queryCollection));
   }
}
