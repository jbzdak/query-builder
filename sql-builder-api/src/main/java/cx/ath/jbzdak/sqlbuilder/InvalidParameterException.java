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

/**
 * Created by: Jacek Bzdak
 */
public class InvalidParameterException extends QueryRenderingException{

   private static final long serialVersionUID = -1275538904586145522L;

   public InvalidParameterException() {
   }

   public InvalidParameterException(String message) {
      super(message);
   }

   public InvalidParameterException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidParameterException(Throwable cause) {
      super(cause);
   }
}
