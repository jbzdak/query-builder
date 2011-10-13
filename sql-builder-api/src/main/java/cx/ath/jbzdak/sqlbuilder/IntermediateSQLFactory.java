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

import java.beans.PropertyChangeListener;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public interface IntermediateSQLFactory {

   void setContext(ExpressionContext expressionContext);

//   ExpressionContext getContext();

   StringBuilder toSQL(RenderingContext renderContext);

   void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder);

   Set<String> collectParameters();

   public void collectChildren();

   Set<IntermediateSQLFactory> getChildren();

   Set<String> getSqlParts();

   boolean containsUnboundParams();


   /* ************************************************************
    *  PropertyChange metohods.
    * ************************************************************
    */

   void addPropertyChangeListener(PropertyChangeListener listener);

   void removePropertyChangeListener(PropertyChangeListener listener);

   PropertyChangeListener[] getPropertyChangeListeners();

   void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

   void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

   PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

   boolean hasListeners(String propertyName);


}
