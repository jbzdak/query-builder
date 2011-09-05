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

   Map<String, Object> parameterValues;

   Map<String, Parameter> parameters;

   Map<String, BoundParameter> boundParameters;

   public ExpressionContext() {
      this(DialectHolder.getDefaultDialect());
   }

   public ExpressionContext(Dialect dialect) {
      this.dialect = dialect;
      expressionConfig = new ExpressionConfig(this.getDialect().getDefaultExpressionConfig());
   }

   public Set<String> collectParameterNames(Object... objects){
      return collectParameterNames(Arrays.asList(objects));
   }

   public Set<String> collectParameterNames(Iterable objects){
      return collectParameterNames(Collections.<Object>emptySet(), objects);
   }

   public Set<String> collectParameterNames(Collection<String> namesToAdd, Object... objects){
      return collectParameterNamesI(namesToAdd, Arrays.asList(objects));
   }

   public Set<String> collectParameterNamesI(Collection<String> namesToAdd, Iterable objects){
      Set<String> parameterNames = new HashSet<String>(namesToAdd);
      for (Object object : objects) {
         if (object instanceof String) {
            String s = (String) object;
            parameterNames.addAll(collectParametersFromString(s));
         } else if (object instanceof IntermediateSQLFactory) {
            IntermediateSQLFactory o = (IntermediateSQLFactory) object;
            parameterNames.addAll(o.collectParameterNames());
         } else if (object instanceof Collection){
            parameterNames.addAll(collectParameterNames((Iterable) object));
         } else{
            throw new InvalidParameterException();
         }
      }
      return parameterNames;
   }



   public Map<String, BoundParameter> getBoundParameters() {
      if (boundParameters == null) {
         boundParameters = new HashMap<String, BoundParameter>();
         for (Parameter s : parameters.values()) {
            boundParameters.put(s.getName(), dialect.bindParameter(s, parameterValues.get(s.getName())));
         }
      }
      return boundParameters;
   }

   /**
    * List of {@link String} and {@link IntermediateSQLFactory}.
    * @param objects
    */
   public Map<String, Parameter> collectParameters(Object... objects){

      Map<String, Parameter> parameters = new HashMap<String, Parameter>();
      for (String parameterName : collectParameterNames(objects)) {
         if(this.parameters.containsKey(parameterName)){
           parameters.put(parameterName, this.parameters.get(parameterName));
         }else{
            parameters.put(parameterName, new DefaultParameter(parameterName));
         }
      }
      return parameters;
   }

   public void collectAndUpdateParameters(SQLFactory parent){
      Map<String, Parameter> collectParameters = collectParameters(parent);
      boundParameters = null;
      for (Map.Entry<String, Parameter> entry : collectParameters.entrySet()) {
         if(!parameters.containsKey(entry.getKey())){
            parameters.put(entry.getKey(), entry.getValue());
         }
      }
   }

   public Object setParameterValue(String parameterName, Object value) {
      if(!parameters.containsKey(parameterName)){
         throw new InvalidParameterException("Unknown parameter name '" + parameterName + "'");
      }
      return parameterValues.put(parameterName, value);
   }

   private List<String> collectParametersFromString(String s){
      Pattern pattern = (Pattern) expressionConfig.get(ExpressionConfigKey.PARAMETER_REGEXP_PATTERN);
      Matcher matcher = pattern.matcher(s);
      List<String> parameters = new ArrayList<String>();
      while (matcher.find()){
         parameters.add(matcher.group(1));
      }
      return parameters;
   }

   public void addParameter(Parameter p){
      parameters.put(p.getName(), p);
   }

   public ExpressionContext(SQLObject sqlObject) {
      this(sqlObject.getExpressionContext().getDialect());
   }

   public RenderingContext renderingContext(){
      return new RenderingContext(dialect);
   }

   public RenderingContext renderingContext(IntermediateSQLFactory sqlFactory){
      RenderingContext context = new RenderingContext(dialect);
      return context;
   }

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
