package io.papermc.paper.registry.data.gametest.environment.types;

import java.util.List;

public non-sealed interface AllOfType extends EnvironmentType {
    List<EnvironmentType> definitions();
}
