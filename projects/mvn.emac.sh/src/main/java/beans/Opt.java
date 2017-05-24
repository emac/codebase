package beans;

import lombok.Data;

import java.util.Optional;

import static java.util.Optional.empty;

/**
 * @author Emac
 * @since 2017-05-24
 */
@Data
public class Opt {

    private Optional<String> content = empty();
}
