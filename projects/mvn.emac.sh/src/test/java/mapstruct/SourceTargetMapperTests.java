package mapstruct;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Emac
 * @since 2020-08-30
 */
public class SourceTargetMapperTests {

    @Test
    public void testMap() {
        Source s = new Source();
        s.setTradeSn("foo");
        Target target = SourceTargetMapper.MAPPER.sourceToTarget(s);
        Assertions.assertEquals(s.getTradeSn(), target.getOrder_no());
    }
}
