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

import cx.ath.jbzdak.sqlbuilder.events.ChildEventPropagator;
import cx.ath.jbzdak.sqlbuilder.util.EventListener;
import cx.ath.jbzdak.sqlbuilder.util.EventSupport;
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
public abstract class IntermediateSQLObject implements IntermediateSQLFactory{

   private static final Logger LOGGER = LoggerFactory.getLogger(IntermediateSQLObject.class);

   protected SQLPeer sqlPeer;

   private Dialect lastPeerGenerationDialect;

   private ExpressionContext context;

   protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

   protected EventSupport<String, IntermediateSQLObject> eventSupport
           = new EventSupport<String, IntermediateSQLObject>(this);

   protected Set<IntermediateSQLFactory> children = new HashSet<IntermediateSQLFactory>();

   protected Set<String> sqlParts = new HashSet<String>();

   protected IntermediateSQLObject() {
      installDefaultPropertyChangeListeners();
   }

   protected IntermediateSQLObject(ExpressionContext context) {
      this();
      this.context = context;
   }

   protected IntermediateSQLObject(IntermediateSQLFactory parent) {
      this();
      this.context = parent.getContext();
   }

   protected void installDefaultPropertyChangeListeners(){
      propertyChangeSupport.addPropertyChangeListener("context", new OnContextChangePCL());
      propertyChangeSupport.addPropertyChangeListener(new PropagateContextPCL());
   }

   protected void installDefaultEventListeners(){
      eventSupport.addListener(new ChildEventPropagator());
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

   protected void maybeRefreshPeer(ExpressionContext expressionContext){
      if(!expressionContext.getDialect().equals(lastPeerGenerationDialect)){
         sqlPeer = expressionContext.getDialect().getPeer(this);
         lastPeerGenerationDialect = expressionContext.getDialect();
      }
   }

   public void setContext(ExpressionContext expressionContext) {
      if(expressionContext != this.context){
         LOGGER.info("Expression context of {} set from {} to {}", Arrays.asList(this, expressionContext, this.context));
         ExpressionContext oldExpressionContext = this.context;
         this.context = expressionContext;
         propertyChangeSupport.firePropertyChange("context", oldExpressionContext, this.context);
      }
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
      appendTo(renderContext, stringBuilder);
      return stringBuilder;
   }

   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
//      eventSupport.fireEvent(ObjectLifecycle.PARAMETER_GATHERING);
//      eventSupport.fireEvent(ObjectLifecycle.PRE_RENDERING);
      collectChildren();
      updateContext();
      collectParameters();
      maybeRefreshPeer(renderingContext.getExpressionContext());
      sqlPeer.appendTo(renderingContext, stringBuilder);
   }

   public Set<IntermediateSQLFactory> getChildren() {
      return Collections.unmodifiableSet(children);
   }

   public Set<String> getSqlParts() {
      return Collections.unmodifiableSet(sqlParts);
   }

   public void fireEvent(String eventType) {
      eventSupport.fireEvent(eventType);
   }

   public void fireEvent(String eventType, Object additional) {
      eventSupport.fireEvent(eventType, additional);
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

   public void addListener(EventListener<? super String, ? super IntermediateSQLObject> eventListener) {
      eventSupport.addListener(eventListener);
   }

   public void addListener(String eventType, EventListener<? super String, ? super IntermediateSQLObject> eventListener) {
      eventSupport.addListener(eventType, eventListener);
   }

   public boolean removeListener(EventListener listener) {
      return eventSupport.removeListener(listener);
   }

   public boolean removeListener(String eventType, EventListener eventListener) {
      return eventSupport.removeListener(eventType, eventListener);
   }

   public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
      return propertyChangeSupport.getPropertyChangeListeners(propertyName);
   }

   public boolean hasListeners(String propertyName) {
      return propertyChangeSupport.hasListeners(propertyName);
   }

   /**
    * Property change listener that propeagates changed context tto children.
    */
   protected class OnContextChangePCL implements PropertyChangeListener {
      public void propertyChange(PropertyChangeEvent evt) {
         setSqlPeer(null);
         updateContext();
      }
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
