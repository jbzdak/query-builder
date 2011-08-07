package cx.ath.jbzdak.sqlbuilder;

import java.util.*;

/**
 * Created by: Jacek Bzdak
 */
public class RenderingContext{

   LinkedList<IntermediateSQLFactory> parentExpressions = new LinkedList<IntermediateSQLFactory>();

   ContextInfo contextInfo;

   final ExpressionContext expressionContext;

   public RenderingContext(Dialect dialect) {
      this.expressionContext = new ExpressionContext(dialect);
   }

   public RenderingContext(ExpressionContext expressionContext) {
      this.expressionContext = expressionContext;
   }

   public RenderingContext(SQLObject sqlObject) {
      this.expressionContext = sqlObject.expressionContext;
   }

   public List<IntermediateSQLFactory> getParentExpressions() {
      return Collections.unmodifiableList(parentExpressions);
   }

   public IntermediateSQLFactory pop() {
      contextInfo = new ContextInfo();
      return parentExpressions.pop();
   }

   public void push(IntermediateSQLFactory sqlFactory) {
      contextInfo = new ContextInfo();
      parentExpressions.push(sqlFactory);
   }

   public IntermediateSQLFactory getRootExpression(){
      return parentExpressions.get(0);
   }

   public IntermediateSQLFactory getParentExpression(){
      int size = parentExpressions.size();
      if(size <= 1){
         return null;
      }
      return parentExpressions.get(size -2);
   }

   public List<Select> getSelects() {
      return contextInfo.getSelects();
   }

   public Select getParentSelect() {
      return contextInfo.getParentSelect();
   }

   public Dialect getDialect() {
      return expressionContext.getDialect();
   }

   public ExpressionContext getExpressionContext() {
      return expressionContext;
   }

   private class ContextInfo{

      List<Select> selects;

      public List<Select> getSelects() {
         if (selects == null) {
            selects = new ArrayList<Select>();
            for (IntermediateSQLFactory parentExpression : parentExpressions) {
               if (parentExpression instanceof Select) {
                  Select select = (Select) parentExpression;
                  selects.add(select);
               }
            }
         }
         return selects;
      }

      public Select getParentSelect(){
         if(getSelects().isEmpty()){
            return null;
         }
         return getSelects().get(getSelects().size() -1);
      }
   }


}
