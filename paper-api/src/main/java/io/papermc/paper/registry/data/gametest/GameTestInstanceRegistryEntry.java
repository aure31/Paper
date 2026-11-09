package io.papermc.paper.registry.data.gametest;

import io.papermc.paper.gametest.GameTestEnvironment;
import io.papermc.paper.gametest.GameTestInstance;
import io.papermc.paper.gametest.GameTestHelper;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.PigVariantRegistryEntry;
import org.bukkit.Rotation;
import org.bukkit.structure.Structure;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;

@NullMarked
@ApiStatus.NonExtendable
public interface GameTestInstanceRegistryEntry {

    GameTestInstance.Type type();

    GameTestEnvironment environment();

    Structure structure();

    @Positive
    int maxTicks();

    @NonNegative
    int setupTicks();

    boolean required();

    Rotation rotation();

    boolean manualOnly();

    @Positive
    int maxAttempts();

    @Positive
    int requiredSuccess();

    boolean skyAccess();

    @NonNegative int padding();

    @Nullable Consumer<GameTestHelper> function();

    @ApiStatus.NonExtendable
    interface Builder extends GameTestInstanceRegistryEntry, RegistryBuilder<GameTestInstance> {

        @Contract(value = "_ -> this", mutates = "this")
        Builder type(GameTestInstance.Type type);

        Builder environment(GameTestEnvironment environment);

        Builder structure(Structure structure);

        Builder maxTicks(
            @Positive int maxTicks
        );

        Builder setupTicks(
            @NonNegative int setupTicks
        );

        Builder required(boolean required);

        Builder rotation(Rotation rotation);

        Builder manualOnly(boolean manualOnly);

        Builder maxAttempts(@Positive int maxAttempts);

        Builder requiredSuccess(@Positive int requiredSuccess);

        Builder skyAccess(boolean skyAccess);

        Builder padding(@NonNegative int padding);

        Builder function(@Nullable Consumer<GameTestHelper> function);

    }
}
