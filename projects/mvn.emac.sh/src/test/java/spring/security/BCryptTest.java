/**
 * 
 */
package spring.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Emac
 *
 */
public class BCryptTest {

	@Test
	public void testOpenBSD() {
		assertTrue(BCrypt.checkpw("123456", BCrypt.hashpw("123456", BCrypt.gensalt())));
		assertTrue(BCrypt.checkpw("my password", "$2a$10$pgnNLzL8xkTI9gMXZzDcUO4yb9ltvDbtF.wm.mfV24X1fgi8tsJwS"));
		assertFalse(BCrypt.checkpw("123456", "$2a$10$pgnNLzL8xkTI9gMXZzDcUO4yb9ltvDbtF.wm.mfV24X1fgi8tsJwS"));
	}

}
