package beans;

import lombok.Getter;

import java.util.Optional;

import static java.util.Optional.empty;

/**
 * @author Emac
 * @since 2017-05-26
 */
@Getter
public class OptPojo {

    private Optional<String> content = empty();

    public OptPojo setContent(Optional<String> content) {
        this.content = content;
        return this;
    }
}
