package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanExpressionMarker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class Select extends SQLObject{

   List<IntermediateSQLFactory> columnExpressions = new ArrayList<IntermediateSQLFactory>();

   List<Table> from = new ArrayList<Table>();

   BooleanExpressionMarker where;

   Integer limit;

   public Select() {
   }

   public Select(SQLObject sqlObject) {
      super(sqlObject);
   }

   public Select(ExpressionContext expressionContext) {
      super(expressionContext);
   }



   public void addColumnExpression(ColumnExpression... columnExpression){
      columnExpressions.addAll(Arrays.asList(columnExpression));
   }



   public void addFrom(Table table){
      from.add(table);
   }

   public void addFrom(String table){
      addFrom(new Table(table));
   }


   public Integer getLimit() {
      return limit;
   }

   public void setLimit(Integer limit) {
      this.limit = limit;
   }

   public void setWhere(BooleanExpressionMarker where) {
      this.where = where;
   }


   public List<IntermediateSQLFactory> getColumnExpressions() {
      return columnExpressions;
   }

   public List<Table> getFrom() {
      return from;
   }

   public BooleanExpressionMarker getWhere() {
      return where;
   }
}
