package io.papermc.paper.registry.data.gametest.environment.types;

import io.papermc.paper.gametest.GameTestEnvironment;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.gametest.environment.GameTestEnvironmentRegistryEntry;
import org.jetbrains.annotations.ApiStatus;
import java.util.List;

public non-sealed interface AllOfType extends EnvironmentType {
    List<EnvironmentType> definitions();
}
