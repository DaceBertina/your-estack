package yourestack.epack.business.domain;

import yourestack.epack.business.model.EpackEntity;

import java.time.OffsetDateTime;
import java.util.Set;

public class CategoryDTO {

    private Long categoryId;

    private String name;

    private Set<EpackEntity> categoryEpacks;

    private OffsetDateTime dateCreated;

    private OffsetDateTime lastUpdated;
}
