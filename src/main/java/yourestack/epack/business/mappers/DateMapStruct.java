package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface DateMapStruct {

    DateMapStruct INSTANCE = Mappers.getMapper(DateMapStruct.class);

    default LocalDateTime dateStringToLocalDateTime(String dateString) {
        if (dateString.isEmpty()) return null;
        return LocalDateTime.parse(dateString);
    }

    default String localDateTimeToDateString(LocalDateTime localDateTime) {
        if (localDateTime == null) return "";
        return String.valueOf(localDateTime);
    }

}
