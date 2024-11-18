package net.somta.juggle.console.application.assembler;

import net.somta.juggle.console.domain.order.vo.CreateOrderVO;
import net.somta.juggle.console.interfaces.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author husong
 * @since 1.2.2
 */
@Mapper
public interface IOrderAssembler {
    IOrderAssembler IMPL = Mappers.getMapper(IOrderAssembler.class);

    OrderDTO voToDto(CreateOrderVO order);
}
