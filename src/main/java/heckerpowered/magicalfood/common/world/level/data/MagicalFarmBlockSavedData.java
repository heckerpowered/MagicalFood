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

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mod.EventBusSubscriber
public final class MagicalFarmBlockSavedData extends SavedData {

    private static final Map<ServerLevel, MagicalFarmBlockSavedData> DATA = new HashMap<>();
    private final Map<BlockPos, MagicalFarmBlock.FeatureData> FEATURES = new LinkedHashMap<>();
    private final ServerLevel level;

    public MagicalFarmBlockSavedData(@NotNull final ServerLevel level) {
        this.level = level;
    }

    @Override
    public CompoundTag save(@NotNull final CompoundTag compoundTag) {
        for (final var entry : FEATURES.entrySet()) {
            compoundTag.putLongArray(entry.getKey().toShortString(), entry.getValue().getData());
        }

        return compoundTag;
    }

    public final Map<BlockPos, MagicalFarmBlock.FeatureData> getFeatureMap() {
        return FEATURES;
    }

    private static final MagicalFarmBlockSavedData load(@NotNull final ServerLevel level,
            @NotNull final CompoundTag compoundTag) {
        final var savedData = new MagicalFarmBlockSavedData(level);
        for (final var key : compoundTag.getAllKeys()) {
            final var location = parseLocation(key);
            final var data = compoundTag.getLongArray(key);

            if (level.getBlockState(location) instanceof final MagicalFarmBlockState blockState) {
                savedData.FEATURES.put(location, new MagicalFarmBlock.FeatureData(data));
            }
        }

        return savedData;
    }

    private static final BlockPos parseLocation(@NotNull final String location) {
        final var locations = location.split(",", 3);
        return new BlockPos(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]),
                Integer.parseInt(locations[2]));
    }

    public static final @NotNull MagicalFarmBlockSavedData getSavedData(@NotNull final ServerLevel level) {
        return DATA.computeIfAbsent(level,
                key -> key.getDataStorage().computeIfAbsent(compoundTag -> load(level, compoundTag),
                        () -> new MagicalFarmBlockSavedData(level), "magical_farm"));
    }
}
