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

package cx.ath.jbzdak.sqlbuilder.xml;

import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
@XmlType(name = "expressionConfig")
public class XmlExpressionConfig {

   List<ExpressionConfigItem> expressionConfigItems = new ArrayList<ExpressionConfigItem>();

   @XmlElement(name = "item")
   public List<ExpressionConfigItem> getExpressionConfigItems() {
      return expressionConfigItems;
   }

   public void setExpressionConfigItems(List<ExpressionConfigItem> expressionConfigItems) {
      this.expressionConfigItems = expressionConfigItems;
   }

   public ExpressionConfig createConfig(){
      ExpressionConfig parent = XmlParsingContext.getCollection().getDefaultExpressionConfig();
      ExpressionConfig config;
      if(parent!=null){
         config = new ExpressionConfig(parent);
      }else{
         config = new ExpressionConfig();
      }

      for (ExpressionConfigItem configItem : expressionConfigItems) {
         config.put(configItem.key, ExpressionConfigTransformer.EXPRESSION_CONFIG_TRANSFORMER.transform(configItem));
      }

      return config;
   }
}

