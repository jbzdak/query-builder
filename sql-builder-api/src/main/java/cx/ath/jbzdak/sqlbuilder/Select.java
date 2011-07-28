package cx.ath.jbzdak.sqlbuilder;

import cx.ath.jbzdak.sqlbuilder.booleanExpression.BooleanExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class Select extends SQLObject{

   List<SQLFactory> columnExpressions = new ArrayList<SQLFactory>();

   List<Table> from = new ArrayList<Table>();

   BooleanExpression where;

   Integer limit;

   public Select() {
   }

   public Select(SQLObject sqlObject) {
      super(sqlObject);
   }

   public Select(Dialect dialect) {
      super(dialect);
   }

   public void addColumnExpression(ColumnExpression... columnExpression){
      columnExpressions.addAll(Arrays.asList(columnExpression));
      for (ColumnExpression expression : columnExpression) {
         expression.registerParent(this);
      }
   }



   public void addFrom(Table table){
      from.add(table);
      table.registerParent(this);
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

   public void setWhere(BooleanExpression where) {
      this.where = where;
      where.registerParent(this);
   }


   public List<SQLFactory> getColumnExpressions() {
      return columnExpressions;
   }

   public List<Table> getFrom() {
      return from;
   }

   public BooleanExpression getWhere() {
      return where;
   }
}
