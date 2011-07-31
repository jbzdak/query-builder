package cx.ath.jbzdak.sqlbuilder.booleanExpression;

import cx.ath.jbzdak.sqlbuilder.SQLObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by: Jacek Bzdak
 */
public class NAryBooleanExpression extends SQLObject implements BooleanExpressionMarker {


   List<BooleanExpressionMarker> expressions = new ArrayList<BooleanExpressionMarker>();

   NAryBooleanExpressionType type;

   public NAryBooleanExpression() {
   }

   public NAryBooleanExpression(NAryBooleanExpressionType type) {
      this.type = type;
   }

   public NAryBooleanExpressionType getType() {
      return type;
   }

   public List<BooleanExpressionMarker> getExpressions() {
      return Collections.unmodifiableList(expressions);
   }

   public void setExpressions(List<BooleanExpressionMarker> expressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }

   public void setSqlFactories(BooleanExpressionMarker... exressions) {
      this.expressions = new ArrayList<BooleanExpressionMarker>(expressions);
   }



}



