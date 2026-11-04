package io.papermc.paper.registry.data.gametest.environment.types;

import org.bukkit.GameRule;

public non-sealed interface GameRulesType<T> extends EnvironmentType {

    GameRule<T> gamerule();
}
