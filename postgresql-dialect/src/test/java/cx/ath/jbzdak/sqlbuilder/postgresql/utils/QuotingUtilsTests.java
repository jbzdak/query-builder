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

package cx.ath.jbzdak.sqlbuilder.postgresql.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by: Jacek Bzdak
 */
public class QuotingUtilsTests {



   @Test
   public void testUnicode1() throws Exception {
      Assert.assertEquals("U&'foo'", QuotingUtils.quoteUnicode("foo", "\\", "'"));
   }

   @Test
   public void testUnicodeEscape() throws Exception {
      Assert.assertEquals("U&'foo\\\\'", QuotingUtils.quoteUnicode("foo\\", "\\", "'"));
   }

   @Test
   public void testUnicode2() throws Exception {
      Assert.assertEquals("U&'foo''bar'", QuotingUtils.quoteUnicode("foo'bar", "\\", "'"));
   }

   @Test
   public void testUnicode3() throws Exception {
      Assert.assertEquals("U&'\\0441\\043B\\043E\\043D'", QuotingUtils.quoteUnicode("\u0441\u043B\u043E\u043D", "\\", "'").toUpperCase());
   }

   @Test
   public void testUnicode4() throws Exception {
      Assert.assertEquals("U&'!0441!043B!043E!043D' UESCAPE '!'", QuotingUtils.quoteUnicode("\u0441\u043B\u043E\u043D", "!", "'").toUpperCase());
   }

   @Test
    public void testUnicode5() throws Exception {
       Assert.assertEquals("U&'!!' UESCAPE '!'", QuotingUtils.quoteUnicode("!", "!", "'").toUpperCase());
    }

   @Test
   public void testContainsUnicode(){
      Assert.assertFalse(QuotingUtils.containsNonAsciiChars("foo"));
   }

   @Test
   public void testContainsUnicode2(){
      Assert.assertTrue(QuotingUtils.containsNonAsciiChars("ąśćźń"));
   }

}
