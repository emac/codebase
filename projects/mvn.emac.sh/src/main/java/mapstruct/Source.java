package mapstruct;

import lombok.Data;

/**
 * @author Emac
 * @since 2020-08-30
 */
@Data
public class Source {

    private String tradeSn;

    private Integer skuNo;

    private Integer totalFee;

    private Integer realFee;

    private Integer realPaid;

    private Integer couponFee;

    private Integer postFee;

    private String buyerName;

    private String province;

    private String city;

    private String district;

    private String address;
}
