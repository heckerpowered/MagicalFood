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
package heckerpowered.magicalfood.common.world.level.block;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import heckerpowered.magicalfood.common.registries.MagicalFoodRegistries;
import heckerpowered.magicalfood.common.world.level.block.state.MagicalFarmBlockState;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Material;

/**
 * Represents a magical fram block with special effects.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFarmBlock extends FarmBlock {

    /**
     * Constructs a new farm block, this constructor should be called for
     * registration
     */
    public MagicalFarmBlock() {
        // Use the same properties as the vanilla farm block, note that Blocks.always
        // method is private and invisible here, we use the public implementation in the
        // MagicalFoodBlock class.
        super(BlockBehaviour.Properties.of(Material.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL)
                .isViewBlocking(MagicalFoodBlock::always).isSuffocating(MagicalFoodBlock::always));
        registerDefaultState(new StateDefinition.Builder<Block, BlockState>(this)
                .create(Block::defaultBlockState, (owner, values,
                        propertiesCodec) -> (BlockState) new MagicalFarmBlockState(owner, values, propertiesCodec))
                .any());
    }

    /**
     *
     *
     * @author Heckerpowered
     * @implNote
     */
    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    @FieldsAreNonnullByDefault
    public static final class FeatureData {

        private static Feature[] registeredFeatures;

        /**
         * A {@link BitSet} that stores features. To query whether a feature is enabled,
         * use {@link BitSet#get} method. Where the integer parameter is the feature's
         * ID.
         */
        private final BitSet featureSet;

        /**
         * Features that are enabled, cached for performance.
         */
        private List<Feature> features;

        /**
         * Constructs a new feature data, with the count of features as the size of
         * features data.
         */
        public FeatureData() {
            featureSet = new BitSet(Feature.featureCount);
        }

        /**
         * Constructs a new feature data with previously stored data, normally this
         * method should be called on load.
         *
         * @param data The previously stored feature data, a long array containing a
         *             sequence of bits.
         */
        public FeatureData(long[] data) {
            featureSet = BitSet.valueOf(data);

            // Cache for performance
            features = getFeaturesEnabled();
        }

        /**
         * Get the feature data.
         *
         * @return The feature data, a long array containing a sequence of bits.
         */
        public final long[] getData() {
            return featureSet.toLongArray();
        }

        /**
         * Get features that are currently enabled.
         *
         * @return The features that are currently enabled
         */
        private final List<Feature> getFeaturesEnabled() {
            // Allocate a new list to store enabled features, with the the number of
            // features that enabled as the initial list size.
            final var list = new ArrayList<Feature>(featureSet.cardinality());

            // Iterate over the true bits in a BitSet, true bits means enabled features
            for (var index = featureSet.nextSetBit(0); index >= 0; index = featureSet.nextSetBit(index + 1)) {
                list.add(getFeature(index));
            }

            return list;
        }

        /**
         * Returns a boolean that indicates whether the specified feature is enabled.
         *
         * @param featureID The feature ID
         * @return {@code true} if the feature is enabled, {@code false} otherwise
         * @throws IndexOutOfBoundsException if the specified ID is negative
         */
        public final boolean isFeatureEnabled(@Nonnegative final int featureID) {
            return featureSet.get(featureID);
        }

        /**
         * Enable the specified feature.
         *
         * @param featureID The feature ID
         * @throws IndexOutOfBoundsException if the specified ID is negative
         */
        public final void enableFeature(@Nonnegative final int featureID) {
            // Determine whether the feature is already enabled
            if (isFeatureEnabled(featureID)) {
                return;
            }

            featureSet.set(featureID);
            features.add(getFeature(featureID));
        }

        /**
         * Disable the specified feature.
         *
         * @param featureID The feature ID
         * @throws IndexOutOfBoundsException if the specified ID is negative
         */
        public final void disableFeature(@Nonnegative final int featureID) {
            featureSet.clear(featureID);
            features.remove(getFeature(featureID));
        }

        /**
         * Get feature by the specified ID
         *
         * @param featureID The feature ID
         * @return The feature with the specified ID
         */
        public static final @NotNull Feature getFeature(@Nonnegative final int featureID) {
            // Determine if the registered features is cached
            if (registeredFeatures == null) {
                // Get all registered features
                final var registeredFeatures = MagicalFoodRegistries.FARM_FEATURES.getValues();

                // Allocate a new feature array for index access
                FeatureData.registeredFeatures = new Feature[registeredFeatures.size()];

                // Copy the features in the collection to the array
                registeredFeatures.toArray(FeatureData.registeredFeatures);
            }

            return FeatureData.getFeature(featureID);
        }
    }

    public static class Feature {
        /**
         * The count of features, each of which occupies one bit. For memory alignment
         * reasons, the size of the occupied bits is a multiple of 8.
         */
        static int featureCount;

        /**
         * Assign a new feature ID, starting from 0. The feature ID is continuous.
         *
         * @return
         */
        protected static final int getFeatureId() {
            return featureCount++;
        }

        @Override
        public final boolean equals(@Nullable final Object object) {
            if (object instanceof final Feature feature) {
                return feature.getClass() == this.getClass();
            }

            return false;
        }
    }
}
