package mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Emac
 * @since 2020-08-30
 */
@Mapper
public interface SourceTargetMapper {

    SourceTargetMapper MAPPER = Mappers.getMapper(SourceTargetMapper.class);

    @Mapping(source = "tradeSn", target="order_no")
    @Mapping(source = "skuNo", target="sku_number")
    @Mapping(source = "totalFee", target="total_fee")
    @Mapping(source = "realFee", target="real_fee")
    @Mapping(source = "realPaid", target="real_paid")
    @Mapping(source = "couponFee", target="coupon_fee")
    @Mapping(source = "postFee", target="post_fee")
    @Mapping(source = "buyerName", target="receiver_name")
    @Mapping(source = "province", target="receiver_province")
    @Mapping(source = "city", target="receiver_city")
    @Mapping(source = "district", target="receiver_area")
    @Mapping(source = "address", target="receiver_address")
    Target sourceToTarget(Source s);
}
