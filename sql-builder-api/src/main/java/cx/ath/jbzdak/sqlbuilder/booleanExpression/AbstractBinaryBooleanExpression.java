package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.DialectAware;
import cx.ath.jbzdak.sqlbuilder.SQLFactory;
import cx.ath.jbzdak.sqlbuilder.SQLObject;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractBinaryBooleanExpression extends SQLObject implements BooleanExpressionMarker {

   protected String type;

   protected SQLFactory rhs;

   protected SQLFactory lhs;

   protected AbstractBinaryBooleanExpression() {
   }


   protected AbstractBinaryBooleanExpression(String type) {
      this.type = type;
   }

   protected AbstractBinaryBooleanExpression(String type, SQLFactory rhs, SQLFactory lhs) {
      this.type = type;
      this.rhs = rhs;
      this.lhs = lhs;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public SQLFactory getRhs() {
      return rhs;
   }

   public SQLFactory getLhs() {
      return lhs;
   }

   @Override
   protected void registerParentforChildren() {
      if (rhs instanceof DialectAware) {
         DialectAware aware = (DialectAware) rhs;
         aware.registerParent(this);
      }
      if (lhs instanceof DialectAware) {
         DialectAware aware = (DialectAware) lhs;
         aware.registerParent(this);
      }

   }
}
