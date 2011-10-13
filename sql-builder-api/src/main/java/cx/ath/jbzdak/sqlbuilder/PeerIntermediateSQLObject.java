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

/**
 * Created by: Jacek Bzdak
 */
public abstract class PeerIntermediateSQLObject extends IntermediateSQLObject {

   private static final Logger LOGGER = LoggerFactory.getLogger(PeerIntermediateSQLObject.class);

   protected SQLPeer sqlPeer;

   private Dialect lastPeerGenerationDialect;

   protected PeerIntermediateSQLObject() {
      installDefaultPropertyChangeListeners();
   }

   protected PeerIntermediateSQLObject(ExpressionContext context) {
      this();
      setContext(context);
   }

   protected PeerIntermediateSQLObject(IntermediateSQLObject parent) {
      this();
      setContext(parent.getContext());
   }

   protected void installDefaultPropertyChangeListeners(){
      super.installDefaultPropertyChangeListeners();
      propertyChangeSupport.addPropertyChangeListener("context", new OnContextChangePCL());
   }


   protected void maybeRefreshPeer(ExpressionContext expressionContext){
      if(sqlPeer == null || !expressionContext.getDialect().equals(lastPeerGenerationDialect)){
         sqlPeer = expressionContext.getDialect().getPeer(this);
         lastPeerGenerationDialect = expressionContext.getDialect();
      }
   }

   protected void setSqlPeer(SQLPeer sqlPeer) {
      SQLPeer oldSqlPeer = this.sqlPeer;
      this.sqlPeer = sqlPeer;
      propertyChangeSupport.firePropertyChange("sqlPeer", oldSqlPeer, this.sqlPeer);
   }

   public void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder) {
      if(context == null){
         context = renderingContext.getExpressionContext();
      }
      collectChildren();
      updateContext();
      collectParameters();
      maybeRefreshPeer(renderingContext.getExpressionContext());
      sqlPeer.appendTo(renderingContext, stringBuilder);
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

}
