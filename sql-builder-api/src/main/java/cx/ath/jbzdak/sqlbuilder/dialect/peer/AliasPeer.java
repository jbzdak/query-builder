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

package cx.ath.jbzdak.sqlbuilder.dialect.peer;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import cx.ath.jbzdak.sqlbuilder.Alias;
import cx.ath.jbzdak.sqlbuilder.RenderingContext;
import cx.ath.jbzdak.sqlbuilder.SQLPeer;
import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfigKey;
import cx.ath.jbzdak.sqlbuilder.dialect.config.IdentifierQuotingStrategy;

import java.awt.image.renderable.RenderContext;

/**
 * Created by: Jacek Bzdak
 */
public class AliasPeer extends AbstractPeer<Alias> {


   @Override
   protected void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
       stringBuilder.append(' ');
      IdentifierQuotingStrategy quotingStrategy =
              (IdentifierQuotingStrategy) renderingContext.getDialect().getDialectConfig().getConfig(DialectConfigKey.ALIAS_QUOTING_STRATEGY);
      stringBuilder.append(quotingStrategy.quoteIdentifier(renderingContext.getDialect(), parent.getAlias()));
      stringBuilder.append(' ');
   }

   public void appendToInternal(StringBuilder stringBuilder) {

   }

}
