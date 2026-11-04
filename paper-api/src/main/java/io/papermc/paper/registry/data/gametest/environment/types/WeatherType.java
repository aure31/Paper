package io.papermc.paper.registry.data.gametest.environment.types;

public non-sealed interface WeatherType extends EnvironmentType {

    enum Weather {
        Clear,
        Rain,
        Thunder
    }

    Weather weather();
}
