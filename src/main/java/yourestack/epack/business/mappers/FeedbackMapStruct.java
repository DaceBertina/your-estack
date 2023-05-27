package yourestack.epack.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import yourestack.epack.business.domain.FeedbackDTO;
import yourestack.epack.business.model.CategoryEntity;
import yourestack.epack.business.model.EpackEntity;
import yourestack.epack.business.model.FeedbackEntity;
import yourestack.epack.business.model.UserEntity;

@Mapper(componentModel = "spring")
public interface FeedbackMapStruct {

    FeedbackMapStruct INSTANCE = Mappers.getMapper(FeedbackMapStruct.class);

    @Mapping(source="epackId", target="epackEntity", qualifiedByName = "epackIdToEpackEntity")
//    @Mapping(source="userId", target="userEntity", qualifiedByName = "userIdToUserEntity")
    FeedbackEntity feedbackDtoToFeedbackEntity(FeedbackDTO feedbackDto);

    @Mapping(source="epackEntity", target="epackId", qualifiedByName = "epackEntityToEpackId")
//    @Mapping(source="userEntity", target="userId", qualifiedByName = "userEntityToUserId")
    FeedbackDTO feedbackEntityToFeedbackDto(FeedbackEntity feedbackEntity);

    @Named("epackIdToEpackEntity")
    static EpackEntity epackIdToEpackEntity(Long  epackId) {
        if (epackId == null) return null;
        return new EpackEntity(epackId);
    }

    @Named("epackEntityToEpackId")
    static Long epackEntityToEpackId(EpackEntity epackEntity){
        if (epackEntity == null) return null;
        return epackEntity.getEpackId();
    }

    @Named("userIdToUserEntity")
    static UserEntity userIdToUserEntity(Long  userId) {
        if (userId == null) return null;
        return new UserEntity(userId);
    }

    @Named("userEntityToUserId")
    static Long userEntityToUserId(UserEntity userEntity){
        if (userEntity == null) return null;
        return userEntity.getUserId();
    }
}
