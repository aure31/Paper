package io.papermc.paper.registry.data.gametest.environment.types;

import java.util.List;

public sealed interface EnvironmentType
    permits AllOfType, ClockTimeType, DifficultyType, FunctionType, GameRulesType,
            TimeLineAttributeType, WeatherType {


}
