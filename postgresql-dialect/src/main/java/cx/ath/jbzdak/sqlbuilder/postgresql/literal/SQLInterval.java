package cx.ath.jbzdak.sqlbuilder.postgresql.literal;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Created by: Jacek Bzdak
 */
public class SQLInterval {

   Map<IntervalPart, Integer> intervalParts = new EnumMap<IntervalPart, Integer>(IntervalPart.class);

   public Map<IntervalPart, Integer> getIntervalParts() {
      return Collections.unmodifiableMap(intervalParts);
   }

   public Integer put(IntervalPart key, Integer value) {
      return intervalParts.put(key, value);
   }

   public Integer get(Object key) {
      return intervalParts.get(key);
   }

   @Override
   public String toString() {
      return super.toString();    //To change body of overridden methods use File | Settings | File Templates.
   }
}