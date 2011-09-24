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

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by: Jacek Bzdak
 */
public class TestDefaultQuotingManager {


   DefaultQuotingManager defaultQuotingManager;

   @Before
   public void setUp() throws Exception {
      setStrategy(IdentifierQuotingStrategy.ALWAYS);

   }

   @Test
   public void testIdentifier() throws Exception {
      Assert.assertEquals("\"foo\"", defaultQuotingManager.quoteIdentifier("foo"));
   }

   @Test
   public void testIdentifier2() throws Exception {
      Assert.assertEquals("\"\"\"\"", defaultQuotingManager.quoteIdentifier("\""));
   }

   @Test
   public void testString() throws Exception {
      Assert.assertEquals("'foo'", defaultQuotingManager.quoteString("foo"));
   }

   @Test
   public void testString2() throws Exception {
      Assert.assertEquals("''''", defaultQuotingManager.quoteString("'"));
   }

   private void setStrategy(IdentifierQuotingStrategy strategy){
      DialectConfig dialectConfig = new DialectConfig();
      dialectConfig.setConfig(DialectConfigKey.IDENTIFIER_QUOTING_STRATEGY, strategy);
      defaultQuotingManager = new DefaultQuotingManager(dialectConfig, "\"", "\"\"", "'", "''");
   }

   @Test
   public void testTestIdentifierStrategy() throws Exception {
      setStrategy(IdentifierQuotingStrategy.NEVER);
      Assert.assertEquals("foo", defaultQuotingManager.quoteIdentifier("foo"));
      Assert.assertEquals("\"", defaultQuotingManager.quoteIdentifier("\""));
   }

   @Ignore
    @Test
   public void testTestIdentifierStrategy2() throws Exception {
      setStrategy(IdentifierQuotingStrategy.WHEN_NEEDED);
      Assert.assertEquals("foo", defaultQuotingManager.quoteIdentifier("foo"));
      Assert.assertEquals("\"", defaultQuotingManager.quoteIdentifier("\"\"\"\""));
   }
}
