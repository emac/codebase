package beans;

import lombok.Getter;

/**
 * @author Emac
 * @since 2017-05-26
 */
@Getter
public class PlainPojo {

    private String content;

    public PlainPojo setContent(String content) {
        this.content = content;
        return this;
    }
}
