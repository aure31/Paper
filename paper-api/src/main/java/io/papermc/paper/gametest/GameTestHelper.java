package io.papermc.paper.gametest;

import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.Position;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;

public interface GameTestHelper {

    World world();

    Server server();

    Location origin();

    void succeed();

    Block getRelativeBlockAt(int x, int y, int z);

    void runBeforeTestEnd(Runnable runnable);

    void runAfterDelay(long delay, Runnable runnable);

    long getTick();

    Location absoluteLocation(Vector relative);

    Location absoluteLocation(double x, double y, double z);

    Location relativeLocation(Location absolute);

    Location relativeLocation(Vector absolute);

    void hookLogger(Logger logger);

    // ASSERT METHODS

    void fail(String message);

    void assertTrue(final boolean condition, final Component errorMessage);

    void assertTrue(final boolean condition, final String errorMessage);

    RuntimeException assertionException(String message);

    RuntimeException assertionException(Component message);

    void assertBlockData(Vector blockPosition, BlockData expected);

    <T extends BlockData> void assertBlockData(
        Vector blockPosition,
        Class<T> type,
        Predicate<T> test,
        Component errorMessage
    );

    <T extends BlockData, G> void assertBlockProperty(
        Vector blockPosition,
        Class<T> type,
        Function<T, G> getter,
        G expected
    );

    void assertBlockType(Vector pos, BlockType expected);

    void assertLocation(Entity entity, Vector expected);

    void assertBlockPosition(Entity entity, BlockPosition expected);

    default void assertBlockPosition(Entity entity, int x, int y, int z) {
        assertBlockPosition(entity, Position.block(x, y, z));
    }

    void assertLogsContain(Logger logger, String message);
}
