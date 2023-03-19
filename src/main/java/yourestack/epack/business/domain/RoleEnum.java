package yourestack.epack.business.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    USER(1L, "user"),
    ADMIN(2L, "admin"),
    MANAGER(3L, "manager");

    private final Long id;

    private final String name;

    private RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @JsonValue
    public RoleEnum getName() {
        return RoleEnum.valueOf(name);
    }

    private static final Map<Long, RoleEnum> enumValuesMapping = new HashMap<>();

    static {
        for (RoleEnum type : RoleEnum.values()) {
            enumValuesMapping.put(
                    type.getId(),
                    type
            );
        }
    }

    public static RoleEnum castLongToEnum(long id) {
        return enumValuesMapping.get(id);
    }
}

