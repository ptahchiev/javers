package org.javers.guavasupport
import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
/**
 * @author akrystian
 */
class MultimapBuilder {

    /**
     * Create multimap from Map<K, List<V>>.
     * @param source
     * @return
     */
    public static <K, V> Multimap create(Map<K, List<V>> source) {
        def multimap = ArrayListMultimap.create()
        def set = source.keySet()
        set.forEach { k ->
            def vs = source[k]
            vs.forEach{v -> multimap.put(k,v)}
        }
        multimap
    }
}
