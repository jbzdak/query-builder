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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by: Jacek Bzdak
 */
public abstract class IntermediateSQLObject implements IntermediateSQLFactory {

   private static final Logger LOGGER = LoggerFactory.getLogger(IntermediateSQLObject.class);

   protected ExpressionContext context;
   protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
   protected Set<IntermediateSQLFactory> children = new HashSet<IntermediateSQLFactory>();
   protected Set<String> sqlParts = new HashSet<String>();

   protected void installDefaultPropertyChangeListeners(){

      propertyChangeSupport.addPropertyChangeListener(new PropagateContextPCL());
   }


   public void collectChildren() {
      children.clear();
      sqlParts.clear();
      for (Field field : getClass().getDeclaredFields()) {
         if(!Modifier.isStatic(field.getModifiers())){
            collectSingleField(field);
         }
      }
   }

   protected void updateContext(){
      collectChildren();
      for (IntermediateSQLFactory child : children) {
         child.setContext(context);
      }
   }

   public void collectParameters() {
      collectChildren();
      updateContext();
      context.collectParameters(children);
      context.collectParameters(sqlParts);
   }

   private void collectSingleField(Field f){
      boolean b = f.isAccessible();
      try{
         f.setAccessible(true);
         collectObject(f.get(this));
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } finally {
         f.setAccessible(b);
      }
   }

   private void collectObject(Object o){
      if(o == null) return;
      if (o instanceof IntermediateSQLFactory) {
         children.add((IntermediateSQLFactory) o);
         return;
      }
      if (o instanceof String) {
         sqlParts.add((String) o);
         return;
      }
      if (o instanceof Collection) {
         Collection collection = (Collection) o;
         for (Object c : collection) {
            collectObject(c);
         }
         return;
      }
      if(o.getClass().isArray()){
         for(int ii = 0; ii < Array.getLength(o); ii++){
            collectObject(Array.get(o, ii));
         }
         return;
      }
   }


   public void setContext(ExpressionContext expressionContext) {
      if(expressionContext != this.context){
         LOGGER.debug("Expression context of {} set from {} to {}", Arrays.asList(this, expressionContext, this.context));
         ExpressionContext oldExpressionContext = this.context;
         this.context = expressionContext;
         propertyChangeSupport.firePropertyChange("context", oldExpressionContext, this.context);
      }
   }

   protected ExpressionContext getContext() {
      return context;
   }

   public StringBuilder toSQL(RenderingContext renderContext) {
      StringBuilder stringBuilder = new StringBuilder();
      appendTo(renderContext, stringBuilder);
      return stringBuilder;
   }

   public Set<IntermediateSQLFactory> getChildren() {
      return Collections.unmodifiableSet(children);
   }

   public Set<String> getSqlParts() {
      return Collections.unmodifiableSet(sqlParts);
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

   /**
    * Property change listener that updates the context of any children set via setter with enables PropertyChangeEvent
    */
   protected class PropagateContextPCL implements PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent evt) {
         if (evt instanceof IntermediateSQLFactory) {
            IntermediateSQLFactory factory = (IntermediateSQLFactory) evt;
            factory.setContext(context);
         }
      }
   }
}
