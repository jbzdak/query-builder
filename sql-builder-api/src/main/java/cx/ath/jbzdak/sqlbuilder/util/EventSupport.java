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

package cx.ath.jbzdak.sqlbuilder.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class EventSupport<ET, SRC> {

   protected final SRC source;

//   protected final List<EventListener> listeners = new ArrayList<EventListener>();

   protected final Map<ET, List<EventListener>> listeners = new HashMap<ET, List<EventListener>>();

   protected List<EventListener> getListenersFor(ET eventType){
      List<EventListener> result = listeners.get(eventType);
      if(result == null){
         result = new ArrayList<EventListener>();
         listeners.put(eventType, result);
      }
      return result;
   }

   public EventSupport(SRC source) {
      this.source = source;
   }

   public void addListener(EventListener<? super ET, ? super SRC> listener){
      getListenersFor(null).add(listener);
   }

   public void addListener(ET eventType, EventListener<? super ET, ? super SRC> listener){
      getListenersFor(eventType).add(listener);
   }

   public boolean removeListenerByClass(ET eventType, Class<? extends EventListener> clazz){
      boolean result = false;
      List<EventListener> listeners = getListenersFor(eventType);
      for (EventListener eventListener : listeners) {
         if(clazz.isInstance(eventListener)){
            result = true;
            listeners.remove(eventListener);
         }
      }
      return result;
   }


   public boolean removeListener(EventListener listener){
      boolean removed = false;
      for (List<EventListener> listenerList : listeners.values()) {
         removed |= listenerList.remove(listener);
      }
      return removed;
   }

   public boolean removeListener(ET eventType, EventListener eventListener){
      return getListenersFor(eventType).remove(eventListener);
   }

   public void fireEvent(ET eventType){
      fireEvent(eventType, null);
   }

   public void fireEvent(ET eventType, Object additional){
      Event<ET, SRC> event = new Event<ET, SRC>(eventType, source, additional);
      if(eventType != null){
         for (EventListener listener : getListenersFor(null)) {
            listener.onEvent(event);
         }
      }
      for (EventListener listener : getListenersFor(eventType)) {
         listener.onEvent(event);
      }
   }

}
