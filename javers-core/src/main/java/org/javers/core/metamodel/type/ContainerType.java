package org.javers.core.metamodel.type;

import java.lang.reflect.Type;

import static org.javers.common.reflection.ReflectionUtil.extractClass;

/**
 * Collection or Array
 *
 * @author bartosz walacik
 */
public abstract class ContainerType extends EnumerableType {

    ContainerType(Type baseJavaType) {
        super(baseJavaType, 1);
    }

    /**
     * never returns null
     */
    public Type getItemType() {
        Type rawType = getConcreteClassTypeArguments().get(0);

        try {
            return rawType.getTypeName().contains("Definition") ? Class.forName(
                            rawType.getTypeName().replace(".core.definition", ".core.entity").replace("Definition", "")) : rawType;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * never returns null
     */
    public Class getItemClass() {
        return extractClass(getItemType());
    }
}
