package io.papermc.paper.registry.data.gametest.environment;

import io.papermc.paper.gametest.GameTestEnvironment;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.gametest.environment.types.EnvironmentType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface GameTestEnvironmentRegistryEntry {

    EnvironmentType environment();

    @ApiStatus.NonExtendable
    interface Builder extends GameTestEnvironmentRegistryEntry, RegistryBuilder<GameTestEnvironment> {
        Builder environment(EnvironmentType type);
    }
}
