package org.javers.core.metamodel.clazz;

import org.javers.common.validation.Validate;

import java.util.Optional;

/**
 * Fluent builder for {@link EntityDefinition},
 * allows to set all optional attributes:
 * Id-property, ignoredProperties and typeAlias, for example:
 * <pre>
 * EntityDefinitionBuilder.entityDefinition(Person.class)
 *    .withIdPropertyName(idPropertyName)
 *    .withIgnoredProperties(ignoredProperties)
 *    .withTypeName(typeName)
 *    .build();
 *</pre>
 *
 * @since 1.4
 * @author bartosz.walacik
 */
public class EntityDefinitionBuilder extends ClientsClassDefinitionBuilder<EntityDefinitionBuilder>{
    private Optional<String> idPropertyName = Optional.empty();
    private boolean shallowReference;

    EntityDefinitionBuilder(Class<?> entity) {
        super(entity);
    }

    public static EntityDefinitionBuilder entityDefinition(Class<?> entity) {
        try {
            Class<?> result = entity.getCanonicalName().contains("Definition") ? Class.forName(
                            entity.getCanonicalName().replace(".core.definition", ".core.entity").replace("Definition", "")) : entity;
            return new EntityDefinitionBuilder(result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public EntityDefinitionBuilder withIdPropertyName(String idPropertyName) {
        Validate.argumentIsNotNull(idPropertyName);
        this.idPropertyName = Optional.of(idPropertyName);
        return this;
    }

    public EntityDefinitionBuilder withShallowReference(){
        this.shallowReference = true;
        return this;
    }

    @Override
    public EntityDefinition build() {
        return new EntityDefinition(this);
    }

    Optional<String> getIdPropertyName() {
        return idPropertyName;
    }

    boolean isShallowReference() {
        return shallowReference;
    }
}
