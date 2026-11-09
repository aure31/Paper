package io.papermc.paper.gametest;
import io.papermc.paper.adventure.PaperAdventure;
import io.papermc.paper.math.BlockPosition;
import net.kyori.adventure.text.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.block.CraftBlockType;
import org.bukkit.craftbukkit.block.data.CraftBlockData;
import org.bukkit.craftbukkit.util.CraftLocation;
import org.bukkit.craftbukkit.util.CraftVector;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class PaperGameTestHelper implements io.papermc.paper.gametest.GameTestHelper {
    private final GameTestHelper handle;
    private final World world;
    private final Server server;
    private final Location origin;
    private final Map<Logger, Set<String>> logRecords = new HashMap<>();

    public PaperGameTestHelper(GameTestHelper handle) {
        this.handle = handle;
        world = handle.getLevel().getWorld();
        origin = CraftLocation.toBukkit(handle.absolutePos(new BlockPos(0, 0, 0)), world);
        server = handle.getLevel().getCraftServer();
    }

    @Override
    public World world() {
        return world;
    }

    @Override
    public Server server() {
        return server;
    }

    @Override
    public Location origin() {
        return origin.clone();
    }

    @Override
    public void succeed() {
        handle.succeed();
    }

    @Override
    public Block getRelativeBlockAt(final int x, final int y, final int z) {
        return world.getBlockAt(absoluteLocation(x, y, z));
    }

    @Override
    public void runBeforeTestEnd(final Runnable runnable) {
        handle.runBeforeTestEnd(runnable);
    }

    @Override
    public void runAfterDelay(final long delay, final Runnable runnable) {
        handle.runAfterDelay(delay,runnable);
    }

    @Override
    public long getTick() {
        return handle.getTick();
    }

    @Override
    public Location absoluteLocation(final Vector relative) {
        return origin.clone().add(relative);
    }

    @Override
    public Location absoluteLocation(final double x, final double y, final double z) {
        return origin.clone().add(x,y,z);
    }

    @Override
    public Location relativeLocation(final Location absolute) {
        return origin.clone().subtract(absolute);
    }

    @Override
    public Location relativeLocation(final Vector absolute) {
        return origin.clone().subtract(absolute);
    }

    @Override
    public void hookLogger(final Logger logger) {
        var handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                logRecords.get(logger).add(record.getMessage());
            }

            @Override
            public void flush() {}

            @Override
            public void close() throws SecurityException {}
        };
        logger.addHandler(handler);
        logRecords.put(logger, new HashSet<>());
        handle.runBeforeTestEnd(() -> clearHookLogger(logger, handler));
    }

    private void clearHookLogger(Logger logger, Handler handler) {
        logRecords.remove(logger);
        logger.removeHandler(handler);
    }

    @Override
    public void fail(final String message) {
        throw handle.assertionException(message);
    }

    @Override
    public void assertTrue(final boolean condition, final Component errorMessage) {
        if (!condition) {
            throw this.assertionException(errorMessage);
        }
    }

    @Override
    public void assertTrue(final boolean condition, final String errorMessage) {
        assertTrue(condition,Component.text(errorMessage));
    }

    @Override
    public RuntimeException assertionException(String message) {
        return new GameTestAssertException(
            net.minecraft.network.chat.Component.literal(message),
            (int) handle.getTick()
        );
    }

    @Override
    public RuntimeException assertionException(final Component message) {
        return new GameTestAssertException(
            PaperAdventure.asVanilla(message),
            (int) handle.getTick()
        );
    }

    @Override
    public void assertBlockData(final Vector blockPosition, final BlockData expected) {
        var got = origin().add(blockPosition).getBlock().getBlockData();
        if (!expected.matches(got)) {
            throw this.assertionException(
                blockPosition,
                "test.error.state_not_equal",
                ((CraftBlockData) got).getState(),
                ((CraftBlockData) expected).getState()
            );
        }
    }

    private GameTestAssertException assertionException(
        Vector blockPos,
        String descriptionId,
        Object... values
    ) {
        return handle.assertionException(
            CraftLocation.toBlockPos(origin().add(blockPos)),
            descriptionId,
            values
        );
    }

    @Override
    public <T extends BlockData> void assertBlockData(
        Vector blockPosition,
        Class<T> type,
        Predicate<T> test,
        Component errorMessage
    ) {
        BlockData data = origin().add(blockPosition).getBlock().getBlockData();
        if (!type.isInstance(data)) {
            throw this.assertionException(
                blockPosition,
                "test.error.block_property_missing",
                type.getName(),
                data.getClass().getName()
            );
        }
        //noinspection unchecked
        if (!test.test((T) data)) {
            throw this.assertionException(errorMessage);
        }
    }

    @Override
    public <T extends BlockData, G> void assertBlockProperty(
        Vector blockPosition,
        Class<T> type,
        Function<T, G> getter,
        G expected
    ) {
        BlockData data = origin().add(blockPosition).getBlock().getBlockData();
        if (!type.isInstance(data)) {
            throw this.assertionException(
                blockPosition,
                "test.error.block_property_missing",
                type.getName(),
                data.getClass().getName()
            );
        }
        @SuppressWarnings("unchecked")
        G actual = getter.apply((T) data);
        if (!actual.equals(expected)) {
            throw this.assertionException(
                blockPosition,
                "test.error.block_property_mismatch",
                type.getName(),
                expected,
                actual
            );
        }
    }

    @Override
    public void assertBlockType(Vector pos, BlockType expected) {
        handle.assertBlockPresent(
            CraftBlockType.bukkitToMinecraftNew(expected),
            CraftVector.toBlockPos(pos)
        );
    }

    @Override
    public void assertLocation(
        Entity entity,
        Vector expected
    ) {
        var vector = entity.getLocation().toVector();
        if (!vector.equals(expected)) {
            throw this.assertionException("Expected " + vector + " location to be " + expected);
        }
    }

    @Override
    public void assertBlockPosition(
        Entity entity,
        BlockPosition expected
    ) {
        var vector = entity.getLocation().toBlock().offset(
            -origin.getBlockX(),
            -origin.getBlockY(),
            -origin.getBlockZ()
        );
        if (!vector.equals(expected)) {
            throw this.assertionException(
                "Expected " + entity.getType().getKey() + " to be at " +
                    expected.x() + " " + expected.y() + " " + expected.z() +
                    ": found " + vector.x() + " " + vector.y() + " " + vector.z());
        }
    }

    @Override
    public void assertLogsContain(Logger logger, String message) {
        var records = logRecords.get(logger);
        if (records == null) {
            throw assertionException("The specified logger hasn't been registered");
        }
        if (!records.contains(message)) {
            throw assertionException("Expected \"" + message + "\" to be logged");
        }
    }
}
