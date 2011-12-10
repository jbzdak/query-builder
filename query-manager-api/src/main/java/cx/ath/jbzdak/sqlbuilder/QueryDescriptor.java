package cx.ath.jbzdak.sqlbuilder;

import java.util.Collection;
import java.util.Map;

/**
 *
 * Something that provides query metadata.
 *
 * Possible metadata keys:
 *
 * <ul>
 *    <li>description --- user readable description of this query</li>
 *    <li>chart.title --- title of chart if this query will be rendered as such</li>
 *    <li>chart.axis.x --- name of x axis of this chart</li>
 *    <li>chart.axis.x.unit --- unit of x axis of this chart</li>
 *    <li>chart.axis.y --- name of y axis of this chart</li>
 *    <li>chart.axis.y.unit --- unit of y axis of this chart</li>
 * </ul>
 *

 * Created by: Jacek Bzdak
 *
 */
public interface QueryDescriptor {
   
   public static final String NAME_METADATA_KEY = "name";
   public static final String DESCRIPTION_METADATA_KEY = "description";
   public static final String CHART_TITLE = "chart.title";
   public static final String CHART_AXIS_X = "chart.axis.x";
   public static final String CHART_AXIS_Y = "chart.axis.y";

   /**
    * Name of the query
    * @return
    */
   String getName();

//   /**
//    * User rendered
//    * @return
//    */
//   String getDescription();

   /**
    * Metadata is a piece of information about this query. Basic usage of this
    * is to present some kinds of information to user.
    *
    * If no metadata was specified for particular metadataKey this method should
    * return empty string.
    *
    * @param meatadataKey Key that defines particualr metadata piece.
    * @return
    */
   String getMetadata(String metadataKey);

   /**
    *
    * Returns keys that has been preprocessed.
    *
    * One can possibly get rendered metadata keys. Keys are rendered using Message format with
    * extension that enable weawing parameters into descriptions. For example if {@link #getMetadata(String)}
    * returns "Comparision of {dataSource1}, and {dataSource2}" While parsing parser would replace {dataSource1} with
    * result with user readable representations of parameter 'dataSource1'. After parameter replacement string
    * is passed through MessageFormat.
    *
    * @param metadataKey
    * @param arguments May be empty
    * @return
    */
   String getRenderedMetadata(String metadataKey, Collection<Object> arguments);

   String getRenderedMetadata(String metadataKey);

   /**
    * Returs descriptors of parameters used in this query.
    * @return
    */
   Collection<? extends ParameterDescriptor> getParameters();



   Map<String, String> getMetadata();

   Map<String, String> getRenderedMetadata();
}
