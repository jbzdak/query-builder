package cx.ath.jbzdak.sqlbuilder;


import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: Jacek Bzdak
 */
public abstract class AbstractQueryDescriptor implements QueryDescriptor {


   public static final Pattern PARAMETER_PATTERN = Pattern.compile("\\{([\\w\\d\\-_]+)}");

   final Map<String, String> metadata;

   protected AbstractQueryDescriptor(Map<String, String> metadata) {
      this.metadata = metadata;
   }

   public abstract String getParametrValue(String name);

   @Override
   public String getName() {
      return getMetadata("name");
   }

   @Override
   public String getMetadata(String metadataKey) {
      
      String value = metadata.get(metadataKey);
      if (value == null){
         return "";
      }
      return value.trim();

   }

   protected String replaceParameters(String pattern) {
      Matcher matcher = PARAMETER_PATTERN.matcher(pattern); 
      StringBuffer buffer = new StringBuffer();
      while (matcher.find()){
         String value = getParametrValue(matcher.group(1));
         matcher.appendReplacement(buffer, Matcher.quoteReplacement(value!=null?value:matcher.group(1)));
      }
      matcher.appendTail(buffer);
      return buffer.toString();
   }

   @Override
   public String getRenderedMetadata(String metadataKey){
      return getRenderedMetadata(metadataKey, Collections.emptyList());
   }

   @Override
   public String getRenderedMetadata(String metadataKey, Collection<Object> arguments) {
      String paramReplaced = replaceParameters(getMetadata(metadataKey));

      return MessageFormat.format(paramReplaced, arguments.toArray());
   }


   @Override
   public Map<String, String> getRenderedMetadata() {
      return new AbstractMap<String, String>(){
         @Override
         public Set<Entry<String, String>> entrySet() {
            return new AbstractSet<Entry<String, String>>() {

               
               
               @Override
               public Iterator<Entry<String, String>> iterator() {
                  return new Iterator<Entry<String, String>>() {
                     
                     Iterator<String> iter = metadata.keySet().iterator();
                     
                     @Override
                     public boolean hasNext() {
                        return iter.hasNext();
                     }

                     @Override
                     public Entry<String, String> next() {
                        String key = iter.next();
                        return new SimpleImmutableEntry<String, String>(
                              key,
                              getRenderedMetadata(key)
                        );
                     }

                     @Override
                     public void remove() {
                        throw new UnsupportedOperationException();
                     }
                  };
               }

               @Override
               public int size() {
                  return metadata.size();
               }
            };
         }
      };
   }

   @Override
   public Map<String, String> getMetadata() {
      return Collections.unmodifiableMap(metadata);
   }
}
