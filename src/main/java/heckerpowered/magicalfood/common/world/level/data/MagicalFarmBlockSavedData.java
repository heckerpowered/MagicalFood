/**
* Copyright (C) 2023 Heckerpowered Corporation
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of this software
* and associated documentation files (the “Software”), to deal in the Software without
* restriction, including without limitation the rights to use, copy, modify, merge, publish,
* distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all copies or
* substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
* BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
* DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package heckerpowered.magicalfood.common.world.level.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import heckerpowered.magicalfood.common.world.level.block.MagicalFarmBlock;
import heckerpowered.magicalfood.common.world.level.block.state.MagicalFarmBlockState;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.fml.common.Mod;

/**
 * Represents a saved data that contains all {@link MagialFarmBlock}'s locations
 * and features that are enabled.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mod.EventBusSubscriber
public final class MagicalFarmBlockSavedData extends SavedData {

    /**
     * The map of saved datas, each level has a saved data.
     */
    private static final Map<ServerLevel, MagicalFarmBlockSavedData> DATA = new HashMap<>();

    /**
     * A map that maps {@link BlockPos} block locations to
     * {@link MagicalFarmBlock.FeatureData} feature data.
     */
    private final Map<BlockPos, MagicalFarmBlock.FeatureData> FEATURES = new LinkedHashMap<>();

    /**
     * The level the data is attached to.
     */
    private final ServerLevel level;

    /**
     * Constructs a new saved data stores under the specified level's folder.
     *
     * @param level The level, must in server side, the saved data will store to the
     *              level's folder.
     */
    public MagicalFarmBlockSavedData(@NotNull final ServerLevel level) {
        this.level = level;
    }

    /**
     * Save the current data to the specified {@link CompoundTag}, with the blocks'
     * locations as the key and feature datas as the value.
     *
     * @return The {@link CompoundTag} that stores the blocks' locations and
     *         feature datas, is the same instance of the {@link CompoundTag}
     *         parameter passed into this method.
     */
    @Override
    public CompoundTag save(@NotNull final CompoundTag compoundTag) {
        for (final var entry : FEATURES.entrySet()) {
            compoundTag.putLongArray(entry.getKey().toShortString(), entry.getValue().getData());
        }

        return compoundTag;
    }

    /**
     * Get a map that maps {@link BlockPos} block locations to
     * {@link MagicalFarmBlock.FeatureData} feature data.
     *
     * @return The map that maps {@link BlockPos} block locations to
     *         {@link MagicalFarmBlock.FeatureData} feature data.
     */
    public final Map<BlockPos, MagicalFarmBlock.FeatureData> getFeatureMap() {
        return FEATURES;
    }

    /**
     * Load the saved data from the supplied {@link CompoundTag}
     *
     * @param level       The level where the data stores.
     * @param compoundTag The {@link CompoundTag} that stores the data.
     * @return The loaded data
     */
    private static final @NotNull MagicalFarmBlockSavedData load(@NotNull final ServerLevel level,
            @NotNull final CompoundTag compoundTag) {
        // Construct a new saved data object with the specified level
        final var savedData = new MagicalFarmBlockSavedData(level);

        // Iterate over all keys, key stores the location of the block.
        for (final var key : compoundTag.getAllKeys()) {
            // Parse the key to the location of the block
            final var location = parseLocation(key);
            final var data = compoundTag.getLongArray(key);

            // Determine if the block state in the specified location is instance of
            // MagicalFarmBlockState, only MagicalFarmBlockState stores the feature data.
            if (level.getBlockState(location) instanceof final MagicalFarmBlockState blockState) {
                savedData.FEATURES.put(location, new MagicalFarmBlock.FeatureData(data));
            }
        }

        return savedData;
    }

    /**
     * Parse the location in the form of {@link String} to {@link BlockPos}, the
     * location string must correspond to the following format {@code x,y,z}. Where
     * the {@code x},{@code y} and {@code z} is the literal integer. Namely the
     * coordinates of the three xyz axises are separated by commas.
     *
     * @param location The location string to parse
     * @return The parsed location
     */
    private static @NotNull final BlockPos parseLocation(@NotNull final String location) {
        // Split the location string into three parts with a single comma as the regex
        final var locations = location.split(",", 3);
        return new BlockPos(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]),
                Integer.parseInt(locations[2]));
    }

    /**
     * Get the saved data by the specified level. Load the data for the level if
     * there is no data loaded previously.
     *
     * @param level The level, must in server side.
     * @return The saved data
     */
    public static final @NotNull MagicalFarmBlockSavedData getSavedData(@NotNull final ServerLevel level) {
        return DATA.computeIfAbsent(level,
                key -> key.getDataStorage().computeIfAbsent(compoundTag -> load(level, compoundTag),
                        () -> new MagicalFarmBlockSavedData(level), "magical_farm"));
    }
}
