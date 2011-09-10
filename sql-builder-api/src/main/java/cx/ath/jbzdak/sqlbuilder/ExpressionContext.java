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

import cx.ath.jbzdak.sqlbuilder.dialect.config.DialectConfig;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfig;
import cx.ath.jbzdak.sqlbuilder.expressionConfig.ExpressionConfigKey;
import cx.ath.jbzdak.sqlbuilder.parameter.BoundParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.DefaultParameter;
import cx.ath.jbzdak.sqlbuilder.parameter.Parameter;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionContext {

   final Dialect dialect;

   DialectConfig dialectConfig;

   ExpressionConfig expressionConfig;

   Map<String, Object> parameterValues = new HashMap<String, Object>();

   Map<String, Parameter> parameters = new HashMap<String, Parameter>();

   Map<String, BoundParameter> boundParameters;

   public ExpressionContext() {
      this(DialectHolder.getDefaultDialect());
   }

   public ExpressionContext(Dialect dialect) {
      this.dialect = dialect;
      expressionConfig = new ExpressionConfig(this.getDialect().getDefaultExpressionConfig());
   }

   public Map<String, BoundParameter> getBoundParameters() {
      if (boundParameters == null) {
         boundParameters = new HashMap<String, BoundParameter>();
         for (String name : parameterValues.keySet()) {
            Parameter p = parameters.get(name);
            BoundParameter boundParameter = dialect.bindParameter(p, parameterValues.get(name));
            boundParameter.setContext(this);
            boundParameters.put(name, boundParameter);
         }
         boundParameters = Collections.unmodifiableMap(boundParameters);
      }
      return boundParameters;
   }
   
   public void collectParameters(Collection<?> objects){
      for (Object object : objects) {
         if (object instanceof IntermediateSQLFactory) {
            IntermediateSQLFactory intermediateSQLFactory = (IntermediateSQLFactory) object;
            intermediateSQLFactory.collectParameters();
         }
         if (object instanceof String) {
            String s = (String) object;
            Set<String> params = collectParametersFromString(s);
            for (String param : params) {
               if(!parameters.containsKey(param)){
                  addParameter(new DefaultParameter(param));
               }
            }
         }
      }
   }

   public Object setParameterValue(String parameterName, Object value) {
      try {
         if(!parameters.containsKey(parameterName)){
            throw new InvalidParameterException("Unknown parameter name '" + parameterName + "'");
         }
         return parameterValues.put(parameterName, value);
      } finally {
         boundParameters = null;
      }
   }

   private Set<String> collectParametersFromString(String s){
      Pattern pattern = (Pattern) expressionConfig.get(ExpressionConfigKey.PARAMETER_REGEXP_PATTERN);
      Matcher matcher = pattern.matcher(s);
      Set<String> parameters = new HashSet<String>();
      while (matcher.find()){
         parameters.add(matcher.group(1));
      }
      return parameters;
   }

   public void addParameter(Parameter p){
      parameters.put(p.getName(), p);
   }

   public ExpressionContext(SQLObject sqlObject) {
      this(sqlObject.getContext().getDialect());
   }

   public RenderingContext renderingContext(){
      return new RenderingContext(this);
   }

//   public RenderingContext renderingContext(IntermediateSQLFactory sqlFactory){
//      RenderingContext context = new RenderingContext(dialect);
//      return context;
//   }

   public ExpressionConfig getExpressionConfig() {
      return expressionConfig;
   }


   public Dialect getDialect() {
      return dialect;
   }

   public DialectConfig getDialectConfig() {
      if (dialectConfig == null) {
         dialectConfig = new DialectConfig(dialect.getDialectConfig());
      }
      return dialectConfig;
   }


}
