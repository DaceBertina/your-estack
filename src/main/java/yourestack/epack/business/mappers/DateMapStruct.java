package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface DateMapStruct {

    default LocalDateTime dateStringToLocalDateTime(String dateString) {
        if (dateString.isEmpty()) return null;
        return LocalDateTime.parse(dateString);
    }

    default String localDateTimeToDateString(LocalDateTime localDateTime) {
        if (localDateTime == null) return "";
        return String.valueOf(localDateTime);
    }

}
