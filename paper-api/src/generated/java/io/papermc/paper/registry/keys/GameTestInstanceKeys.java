package io.papermc.paper.registry.keys;

import static net.kyori.adventure.key.Key.key;

import io.papermc.paper.annotation.GeneratedClass;
import io.papermc.paper.gametest.GameTestInstance;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import net.kyori.adventure.key.Key;
import org.jspecify.annotations.NullMarked;

/**
 * Vanilla keys for {@link RegistryKey#TEST_INSTANCE}.
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
public final class GameTestInstanceKeys {
    /**
     * {@code minecraft:always_pass}
     *
     * @apiNote This field is version-dependant and may be removed in future Minecraft versions
     */
    public static final TypedKey<GameTestInstance> ALWAYS_PASS = create(key("always_pass"));

    private GameTestInstanceKeys() {
    }

    /**
     * Creates a typed key for {@link GameTestInstance} in the registry {@code minecraft:test_instance}.
     *
     * @param key the value's key in the registry
     * @return a new typed key
     */
    public static TypedKey<GameTestInstance> create(final Key key) {
        return TypedKey.create(RegistryKey.TEST_INSTANCE, key);
    }
}
