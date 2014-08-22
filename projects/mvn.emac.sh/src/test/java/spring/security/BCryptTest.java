/**
 * 
 */
package spring.security;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Emac
 *
 */
public class BCryptTest {

	@Test
	public void testOpenBSD() {
		Assert.assertTrue(BCrypt.checkpw("123456", BCrypt.hashpw("123456", BCrypt.gensalt())));
		Assert.assertTrue(BCrypt.checkpw("my password", "$2a$10$pgnNLzL8xkTI9gMXZzDcUO4yb9ltvDbtF.wm.mfV24X1fgi8tsJwS"));
		Assert.assertFalse(BCrypt.checkpw("123456", "$2a$10$pgnNLzL8xkTI9gMXZzDcUO4yb9ltvDbtF.wm.mfV24X1fgi8tsJwS"));
	}

}
