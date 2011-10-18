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
import cx.ath.jbzdak.sqlbuilder.parameter.ParameterType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public class ExpressionContext {

   DialectConfig dialectConfig;

   ExpressionConfig expressionConfig;

   IntermediateSQLFactory rootExpression;

   Map<String, Object> parameterValues = new HashMap<String, Object>();

   Map<String, Parameter> parameters = new HashMap<String, Parameter>();

   Map<String, BoundParameter> boundParameters;

   Map<IntermediateSQLFactory, Set<String>> parametersByItem
           = new HashMap<IntermediateSQLFactory, Set<String>>();

   public ExpressionContext() {
      this(DialectHolder.getDefaultDialect());
   }

   public ExpressionContext(Dialect dialect) {
      expressionConfig = new ExpressionConfig(dialect.getDefaultExpressionConfig());
      expressionConfig.put(ExpressionConfigKey.DIALECT, dialect);
   }

   public ExpressionContext(ExpressionConfig expressionConfig) {
      this.expressionConfig = expressionConfig;
   }

   public void setRootExpression(IntermediateSQLFactory rootExpression) {
      this.rootExpression = rootExpression;
   }

   public Map<String, BoundParameter> getBoundParameters() {
      if (boundParameters == null) {
         boundParameters = new HashMap<String, BoundParameter>();
         for (String name : parameterValues.keySet()) {
            Parameter p = parameters.get(name);
            BoundParameter boundParameter = getDialect().bindParameter(p, parameterValues.get(name));
            boundParameter.setContext(this);
            boundParameters.put(name, boundParameter);
         }
         boundParameters = Collections.unmodifiableMap(boundParameters);
      }
      return boundParameters;
   }

   /**
    * Checks whether specified string is a parameter string. That is whether {@code paramPattern.matches(possibleParam.trim())};
    * @return
    */
   public boolean isParameter(String possibleParam){
      if(possibleParam==null){
         return false;
      }
      Pattern paramPattern = (Pattern) getExpressionConfig().get(ExpressionConfigKey.PARAMETER_REGEXP_PATTERN);
      return paramPattern.matcher(possibleParam).matches();
   }

   public boolean containsUnboundParams(IntermediateSQLFactory factory){
      Set<String> parameters = new HashSet<String>(parametersByItem.get(factory));
      for (String paramName : getBoundParameters().keySet()) {
         parameters.remove(paramName);
      }
      return parameters.size() != 0;
   }

   public <T> T resolveParameter(Parameter<T> parameter){
      addParameter(parameter);
      return (T) resolveParameter(parameter.getName());
   }

   public Object resolveParameter(String parameterName){
      if(parameterName.startsWith(":")){
         parameterName = parameterName.substring(1);
      }
      BoundParameter boundParameter = getBoundParameters().get(parameterName);
      if(boundParameter == null){
         return null;
      }
      return boundParameter.getValue();
   }

   public Set<String> collectParameters(IntermediateSQLFactory parent, Collection<?> objects){
      Set<String> collected = parametersByItem.get(parent);
      if(collected == null){
         collected = new HashSet<String>();
      }else{
         collected = new HashSet<String>(collected);
      }
      for (Object object : objects) {
         if (object instanceof IntermediateSQLFactory) {
            IntermediateSQLFactory intermediateSQLFactory = (IntermediateSQLFactory) object;
            collected.addAll(intermediateSQLFactory.collectParameters());
         }
         if (object instanceof String) {
            String s = (String) object;
            Set<String> params = collectParametersFromString(s);
            for (String param : params) {
               collected.add(param);
               if(!parameters.containsKey(param)){
                  DefaultParameter defaultParameter = new DefaultParameter(param);
                  addParameter(defaultParameter);
               }
            }

         }
      }
      collected = Collections.unmodifiableSet(collected);
      parametersByItem.put(parent, collected);
      return collected;
   }

   public Set<Parameter> getParameters(){
      return Collections.unmodifiableSet(new HashSet<Parameter>(parameters.values()));
   }

   public Set<Parameter> getParametersForItem(IntermediateSQLFactory intermediateSQLFactory){
      if(rootExpression.equals(intermediateSQLFactory)){
         return getParameters();
      }
      Set<Parameter> result = new HashSet<Parameter>();
      for (String name : parametersByItem.get(intermediateSQLFactory)) {
         result.add(parameters.get(name));
      }
      return Collections.unmodifiableSet(result);
   }



   public Object setParameterValue(String parameterName, Object value) {
      try {
         Parameter parameter = parameters.get(parameterName);
         if(parameter==null){
            throw new InvalidParameterException("Unknown parameter name '" + parameterName + "'");
         }
         return parameterValues.put(parameterName, parameter.fromObject(value));
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
      addParameter(p, true);
   }

   public void addParameter(Parameter p, boolean override){
      Parameter oldParam = parameters.get(p.getName());
      if(oldParam != null && !override){
         return;
      }
      if(oldParam != null &&
              ! ParameterType.DEFAULTT_PARAMETER.equals(oldParam.getType()) &&
              ! oldParam.getType().equals(p.getType())){
         throw new ParameterSetTwice("Parameter '" + p.getName() + "' is set twice, and it should be set only once.");
      }
      parameters.put(p.getName(), p);
   }

   public void addParameters(Collection<? extends Parameter<?>> p){
      for (Parameter abstractParameter : p) {
         addParameter(abstractParameter);
      }
    }

//   public Date parseDate(String string) {
//      DateFormat dateFormat = new SimpleDateFormat(
//              (String) getDialectConfig().getConfig(DialectConfigKey.DEFAULT_INPUT_DATE_FORMAT));
//      try {
//         return dateFormat.parse(string);
//      } catch (ParseException e) {
//         throw new QueryRenderingException("Can't parse '" + string + "' as date", e);
//      }
//   }

   public ExpressionContext(SQLObject sqlObject) {
      this(sqlObject.getContext().getDialect());
   }

   public RenderingContext renderingContext(){
      return new RenderingContext(this);
   }

   public ExpressionConfig getExpressionConfig() {
      return expressionConfig;
   }


   public Dialect getDialect() {
      return (Dialect) expressionConfig.get(ExpressionConfigKey.DIALECT);
   }

   public DialectConfig getDialectConfig() {
      if (dialectConfig == null) {
         dialectConfig = new DialectConfig(getDialect().getDialectConfig());
      }
      return dialectConfig;
   }


}
