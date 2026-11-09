package io.papermc.paper.gametest;

import org.bukkit.Keyed;
import org.bukkit.Rotation;
import org.bukkit.structure.Structure;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.util.function.Consumer;

@NullMarked
public interface GameTestInstance extends Keyed {

    enum Type {

        BLOCK_BASED,

        FUNCTION
    }

    Type type();

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
}
