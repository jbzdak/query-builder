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
import cx.ath.jbzdak.sqlbuilder.xml.*;
import cx.ath.jbzdak.sqlbuilder.xml.boolExp.*;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlDefaultParameter;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlParameters;
import cx.ath.jbzdak.sqlbuilder.xml.parameter.XmlIntegerParameter;
import cx.ath.jbzdak.sqlbuilder.xml.query.XmlSelect;
import cx.ath.jbzdak.sqlbuilder.xml.query.XmlSimpleQuery;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;

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

   private XmlQueryCollection createDefaultCollection(){
      XmlQueryCollection queryCollection = new XmlQueryCollection();
      queryCollection.setXmlDialect("default");
      XmlDialectConfig dialectConfig = new XmlDialectConfig();
      dialectConfig.getConfigItems().add(new DialectConfigItem(DialectConfigKey.PRETTIFY_SQL, PrettifySQLLevel.PRETTY.toString()));
      queryCollection.setXmlDialectConfig(dialectConfig);
      return queryCollection;
   }

   private Marshaller marshaller;

   @Test
   public void testSimpleQuery() throws Exception {
      XmlQueryCollection queryCollection = createDefaultCollection();
      queryCollection.getQueryTags().add(new XmlSimpleQuery("foo-query-name", "Query that does foo", "SELECT * FROM foo"));
      System.out.println(marshall(queryCollection));
   }

   @Test
   public void testSelect() throws Exception {
      XmlQueryCollection queryCollection = createDefaultCollection();
      XmlSelect select = new XmlSelect();
      select.setName("select");

      select.getSelect().add(new XmlColumnExpression("foo", "FOO", "FOO_SCHEMA"));
      select.getSelect().add(new XmlColumnExpression("bar"));
      select.getSelect().add(new XmlColumnExpression("baz", "BAZ", "BAZ_SCHEMA"));
      select.getSelect().add(new XmlRaw("foobar AS barfoo"));

      select.getFrom().add(new XmlRaw("FOO"));
      select.getFrom().add(new XmlTable("BAZ"));
      select.getFrom().add(new XmlTable("FOOBAZ", null, "BARFOO"));

      XmlParameters parameters = new XmlParameters();
      parameters.getXmlParameters().add(new XmlIntegerParameter(":foo", ""));
      parameters.getXmlParameters().add(new XmlDefaultParameter(":bar", "BAR"));
      select.setParameters(parameters);

      {

         XmlAnd xmlAnd = new XmlAnd();

         xmlAnd.setOperands(
                 Arrays.asList(
                         (Object) new XmlBinaryExpression("foo = 2"),
                         (Object) new XmlBinaryExpression("foo = 2"),
                         new XmlIsNull(false, new XmlTable("foo", "FOO_SCHEMA", "as"))
                 )
         );

         XmlOr xmlOr = new XmlOr();

         xmlOr.setOperands(Arrays.asList(
                 (Object) xmlAnd,
                 new XmlBinaryExpression("baz <> 32")
         ));

         select.setWhere(new XmlBooleanCondition(xmlOr));
      }

      queryCollection.getQueryTags().add(select);
      System.out.println(marshall(queryCollection));
   }
}
