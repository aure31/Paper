package io.papermc.paper.registry.data.gametest.environment.types;

public sealed interface EnvironmentType
    permits AllOfType, ClockTimeType, DifficultyType, FunctionType, GameRulesType,
            TimeLineAttributeType, WeatherType {

    // static public AllOfType createAllOf(List<EnvironmentType> attributes){
    //
    // }
}
