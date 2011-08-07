package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.SQLLiteral;
import cx.ath.jbzdak.sqlbuilder.SQLObject;
import cx.ath.jbzdak.sqlbuilder.literal.LiteralFactory;

/**
 * Created by: Jacek Bzdak
 */
public class Range extends SQLObject{

   SQLLiteral from;

   SQLLiteral to;

   public Range() {
   }

   public Range(SQLObject parent, int from, int to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory();
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLObject parent, float from, float to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory(); ;
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLObject parent, double from, double to) {
      super(parent);
      LiteralFactory literalFactory = this.getExpressionContext().getDialect().getLiteralFactory();
      this.from = literalFactory.create(from);
      this.to = literalFactory.create(to);
   }

   public Range(SQLLiteral to, SQLLiteral from) {
      this.to = to;
      this.from = from;
   }


   public SQLLiteral getFrom() {
      return from;
   }

   public void setFrom(SQLLiteral from) {
      this.from = from;
   }

   public SQLLiteral getTo() {
      return to;
   }

   public void setTo(SQLLiteral to) {
      this.to = to;
   }
}
