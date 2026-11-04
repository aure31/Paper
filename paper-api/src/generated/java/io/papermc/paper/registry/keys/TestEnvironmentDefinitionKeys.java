package io.papermc.paper.registry.keys;

import static net.kyori.adventure.key.Key.key;

import io.papermc.paper.annotation.GeneratedClass;
import io.papermc.paper.gametest.GameTestEnvironment;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.NullMarked;

/**
 * Vanilla keys for {@link RegistryKey#TEST_ENVIRONMENT}.
 *
 * @apiNote The fields provided here are a direct representation of
 * what is available from the vanilla game source. They may be
 * changed (including removals) on any Minecraft version
 * bump, so cross-version compatibility is not provided on the
 * same level as it is on most of the other API.
 */
@SuppressWarnings({
        "unused",
        "SpellCheckingInspection"
})
@NullMarked
@GeneratedClass
public final class TestEnvironmentDefinitionKeys {
    /**
     * {@code minecraft:default}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final TypedKey<GameTestEnvironment> DEFAULT = create(key("default"));

    private TestEnvironmentDefinitionKeys() {
    }

    /**
     * Creates a typed key for {@link GameTestEnvironment} in the registry {@code minecraft:test_environment}.
     *
     * @param key the value's key in the registry
     * @return a new typed key
     */
    public static TypedKey<GameTestEnvironment> create(final Key key) {
        return TypedKey.create(RegistryKey.TEST_ENVIRONMENT, key);
    }
}
