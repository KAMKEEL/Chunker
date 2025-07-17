package com.hivemc.chunker.conversion.java.resolver.legacy;

import com.hivemc.chunker.conversion.encoding.base.Version;
import com.hivemc.chunker.conversion.encoding.java.base.resolver.identifier.legacy.JavaLegacyBlockIDResolver;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaLegacyBlockIDResolverTest {
    @Test
    public void testNumericInput() {
        JavaLegacyBlockIDResolver resolver = new JavaLegacyBlockIDResolver(new Version(1, 12, 2));
        Optional<Integer> result = resolver.from("3005");
        assertTrue(result.isPresent());
        assertEquals(3005, result.get().intValue());
    }
}
