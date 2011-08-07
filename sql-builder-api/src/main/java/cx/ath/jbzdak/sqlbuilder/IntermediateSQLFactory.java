package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public interface IntermediateSQLFactory {

   void setContext(ExpressionContext expressionContext);

   StringBuilder toSQL(RenderingContext renderContext);

   void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder);

}
