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

/**
 *
 * @param <ET> EventType
 * @param <SRC> SourceObject
 */
public final class Event<ET, SRC> {

   final ET eventType;

   final SRC source;

   final Object additionalData;

   public Event(ET eventType, SRC source, Object additionalData) {
      this.eventType = eventType;
      this.source = source;
      this.additionalData = additionalData;
   }

   public Event(ET eventType, SRC source) {
      this(eventType, source, null);
   }

   public ET getEventType() {
      return eventType;
   }

   public SRC getSource() {
      return source;
   }

   public Object getAdditionalData() {
      return additionalData;
   }
}
