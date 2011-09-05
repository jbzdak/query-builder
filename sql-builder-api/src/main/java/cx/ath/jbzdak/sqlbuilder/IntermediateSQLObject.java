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

import javax.management.RuntimeErrorException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by: Jacek Bzdak
 */
public abstract class IntermediateSQLObject implements IntermediateSQLFactory{

   protected SQLPeer sqlPeer;

   private Dialect lastPeerGenerationDialect;

   protected ExpressionContext context;

   protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

   protected IntermediateSQLObject() {
      propertyChangeSupport.addPropertyChangeListener("context", new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            setSqlPeer(null);
            updateContext();
         }
      });

      propertyChangeSupport.addPropertyChangeListener(new PropertyChangeListener() {
         public void propertyChange(PropertyChangeEvent evt) {
            if (evt instanceof IntermediateSQLFactory) {
               IntermediateSQLFactory factory = (IntermediateSQLFactory) evt;
               factory.setContext(context);
            }
         }
      });
   }

   protected void updateContext(){
      for (Field field : getClass().getFields()) {
         updateSingleField(field);
      }
   }

   private void updateSingleField(Field f){
      boolean b = f.isAccessible();
      try{
         f.setAccessible(true);
         updateObject(f.get(this));
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } finally {
         f.setAccessible(b);
      }
   }

   private void updateObject(Object o){
      if(o == null) return;
      if (o instanceof IntermediateSQLFactory) {
         IntermediateSQLFactory factory = (IntermediateSQLFactory) o;
         factory.setContext(context);
         return;
      }
      if (o instanceof Collection) {
         Collection collection = (Collection) o;
         for (Object c : collection) {
            updateObject(c);
         }
         return;
      }
      if(o.getClass().isArray()){
         for(int ii = 0; ii < Array.getLength(o); ii++){
            updateObject(Array.get(o, ii));
         }
         return;
      }
   }

   protected void maybeRefreshPeer(ExpressionContext expressionContext){
      if(!expressionContext.getDialect().equals(lastPeerGenerationDialect)){
         sqlPeer = expressionContext.getDialect().getPeer(this);
         lastPeerGenerationDialect = expressionContext.getDialect();
      }
   }

   public void setContext(ExpressionContext expressionContext) {
      ExpressionContext oldExpressionContext = this.context;
      this.context = expressionContext;
      propertyChangeSupport.firePropertyChange("context", oldExpressionContext, this.context);
   }

   public ExpressionContext getContext() {
      return context;
   }

   protected void setSqlPeer(SQLPeer sqlPeer) {
      SQLPeer oldSqlPeer = this.sqlPeer;
      this.sqlPeer = sqlPeer;
      propertyChangeSupport.firePropertyChange("sqlPeer", oldSqlPeer, this.sqlPeer);
   }


   public StringBuilder toSQL(RenderingContext renderContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendToInternal(renderContext, stringBuilder);
      return stringBuilder;
   }


   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      appendToInternal(renderingContext, stringBuilder);
   }

   public void appendToInternal(RenderingContext renderingContext, StringBuilder stringBuilder) {
      maybeRefreshPeer(renderingContext.getExpressionContext());
      sqlPeer.appendTo(renderingContext, stringBuilder);
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      propertyChangeSupport.removePropertyChangeListener(listener);
   }

   public PropertyChangeListener[] getPropertyChangeListeners() {
      return propertyChangeSupport.getPropertyChangeListeners();
   }

   public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
   }

   public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
   }

   public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
      return propertyChangeSupport.getPropertyChangeListeners(propertyName);
   }

   public boolean hasListeners(String propertyName) {
      return propertyChangeSupport.hasListeners(propertyName);
   }
}
