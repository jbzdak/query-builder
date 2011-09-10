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

package cx.ath.jbzdak.sqlbuilder.dialect.config;

/**
 * Created by: Jacek Bzdak
 */
public class InvalidConfigurationException extends RuntimeException{


   private static final long serialVersionUID = -8365326212692573989L;

   public InvalidConfigurationException() {
   }

   public InvalidConfigurationException(String message) {
      super(message);
   }

   public InvalidConfigurationException(String message, Throwable cause) {
      super(message, cause);
   }

   public InvalidConfigurationException(Throwable cause) {
      super(cause);
   }
}
