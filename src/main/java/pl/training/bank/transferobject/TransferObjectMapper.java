package pl.training.bank.transferobject;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class TransferObjectMapper {

    public <Entity, Type> List<Type> map(List<Entity> in, Class<Type> type) {
        ModelMapper modelMapper = new ModelMapper();
        return in.parallelStream()
                .map(element -> modelMapper.map(element, type))
                .collect(Collectors.toList());
    }

    public <DTO, Type> Type map(DTO to, Class<Type> type) {
        return new ModelMapper().map(to, type);
    }

}
