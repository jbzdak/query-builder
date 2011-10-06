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

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Created by: Jacek Bzdak
 */
public class JaxbEntryPoint {

   public static final JAXBContext DEFAULT_CONTEXT = buildContext("");

   public static final String DEFAULT_JAXB_PACKAGES =
           "cx.ath.jbzdak.sqlbuilder.xml";

   public static JAXBContext buildContext(String additionalPackages){

      String context = DEFAULT_JAXB_PACKAGES;
      if(additionalPackages != null && context.length()!=0){
         context+=":" + additionalPackages;
      }
      try {
         return JAXBContext.newInstance(context);
      } catch (JAXBException e) {
         throw new RuntimeException(e);
      }
   }


}
