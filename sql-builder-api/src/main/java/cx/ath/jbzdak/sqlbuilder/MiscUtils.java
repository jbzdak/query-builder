package cx.ath.jbzdak.sqlbuilder;

/**
 * Created by: Jacek Bzdak
 */
public class MiscUtils {

   public static String toSQL(RenderingContext renderingContext, IntermediateSQLFactory intermediateSQLFactory){
      StringBuilder builder = new StringBuilder();
      intermediateSQLFactory.appendToInternal(renderingContext, builder);
      return builder.toString();
   }
}
