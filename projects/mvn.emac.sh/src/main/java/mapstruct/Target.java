package mapstruct;

import lombok.Data;

/**
 * @author Emac
 * @since 2020-08-30
 */
@Data
public class Target {

    private String order_no;

    private Integer sku_number;

    private Integer total_fee;

    private Integer real_fee;

    private Integer real_paid;

    private Integer coupon_fee;

    private Integer post_fee;

    private String receiver_name;

    private String receiver_province;

    private String receiver_city;

    private String receiver_area;

    private String receiver_address;
}
