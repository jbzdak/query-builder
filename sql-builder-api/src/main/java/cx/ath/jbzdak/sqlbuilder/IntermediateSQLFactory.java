package cx.ath.jbzdak.sqlbuilder;

import java.util.List;
import java.util.Set;

/**
 * Created by: Jacek Bzdak
 */
public interface IntermediateSQLFactory {

   void setContext(ExpressionContext expressionContext);

   StringBuilder toSQL(RenderingContext renderContext);

   void appendTo(RenderingContext renderingContext, StringBuilder stringBuilder);

   Set<String> collectParameterNames();



}
