package io.papermc.paper.gametest;

import io.papermc.paper.registry.data.gametest.environment.types.EnvironmentType;
import org.bukkit.Keyed;

public interface GameTestEnvironment extends Keyed {

    EnvironmentType environment();
}
