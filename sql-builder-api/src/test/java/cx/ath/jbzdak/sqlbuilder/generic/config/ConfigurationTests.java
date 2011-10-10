package cx.ath.jbzdak.sqlbuilder.generic.config;

import com.sun.deploy.config.Config;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by: Jacek Bzdak
 */
public class ConfigurationTests {

   @Test
   public void testEquals() throws Exception {
      Assert.assertEquals(ConfigA.KEY_1, ConfigA.KEY_1);
   }

   @Test
   public void testNonEqual() throws Exception {
      Assert.assertFalse(ConfigA.KEY_1.equals(ConfigB.KEY_1));
   }

   @Test
   public void testNonEqual2() throws Exception {
      Assert.assertFalse(ConfigA.KEY_1.equals(ConfigA.KEY_2));
   }
}
