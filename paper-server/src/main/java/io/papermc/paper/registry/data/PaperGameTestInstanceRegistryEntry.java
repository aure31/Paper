package io.papermc.paper.registry.data;

import io.papermc.paper.gametest.GameTestEnvironment;
import io.papermc.paper.gametest.GameTestHelper;
import io.papermc.paper.registry.data.gametest.GameTestInstanceRegistryEntry;
import org.bukkit.Rotation;
import org.bukkit.structure.Structure;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import java.util.OptionalInt;
import java.util.function.Consumer;

@NullMarked
public class PaperGameTestInstanceRegistryEntry implements GameTestInstanceRegistryEntry {
    protected Type type;
    protected GameTestEnvironment environment;
    protected Structure structure;
    protected OptionalInt maxTicks = OptionalInt.empty();
    protected OptionalInt setupTicks = OptionalInt.empty();
    protected boolean required = true;


    @Override
    public Type type() {
        return type;
    }

    @Override
    public GameTestEnvironment environment() {
        return null;
    }

    @Override
    public Structure structure() {
        return null;
    }

    @Override
    public @Positive int maxTicks() {
        return 0;
    }

    @Override
    public @NonNegative int setupTicks() {
        return 0;
    }

    @Override
    public boolean required() {
        return false;
    }

    @Override
    public Rotation rotation() {
        return null;
    }

    @Override
    public boolean manualOnly() {
        return false;
    }

    @Override
    public @Positive int maxAttempts() {
        return 0;
    }

    @Override
    public @Positive int requiredSuccess() {
        return 0;
    }

    @Override
    public boolean skyAccess() {
        return false;
    }

    @Override
    public @NonNegative int padding() {
        return 0;
    }

    @Override
    public @Nullable Consumer<GameTestHelper> function() {
        return null;
    }
}
