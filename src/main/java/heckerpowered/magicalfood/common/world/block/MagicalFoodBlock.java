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
package heckerpowered.magicalfood.common.world.block;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import heckerpowered.magicalfood.common.MagicalFood;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * The register class for {@code MagicalFood} mod blocks, any blocks derived
 * from {@code MagicalFood} item should be registered at this class.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFoodBlock {

    /**
     * The deferred register of all the items derived from {@code MagicalFood} mod.
     */
    public static final DeferredRegister<Block> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,
            MagicalFood.MODID);

    /**
     * Do not let anyone instantiate this class
     */
    private MagicalFoodBlock() {
    }

    /**
     * See {@link MagicalFarmBlock} for details related to this block
     */
    public static final RegistryObject<MagicalFarmBlock> MAGICAL_FARM_BLOCK = DEFERRED_REGISTER.register(
            "magical_farmland", MagicalFarmBlock::new);

    /**
     * A utility method for block registration, referencing to this method will be
     * converted to {@link BlockBehaviour.StatePredicate}. Call this method manually
     * is meaningless, this method does not contain any code and always returns
     * {@code true}. This method is a public version of {@link Blocks#always}
     *
     * @param blockState  The block state for pridiction
     * @param blockGetter The block getter, normally a level
     * @param blockPos    The location of the block
     * @return Always returns {@code true}
     */
    public static boolean always(@NotNull final BlockState blockState, @NotNull final BlockGetter blockGetter,
            @NotNull final BlockPos blockPos) {
        // Return true and do nothing
        return true;
    }

    /**
     * A utility method for block registration, referencing to this method will be
     * converted to {@link BlockBehaviour.StatePredicate}. Call this method manually
     * is meaningless, this method does not contain any code and always returns
     * {@code false}. This method is a public version of {@link Blocks#always}
     *
     * @param blockState  The block state for pridiction
     * @param blockGetter The block getter, normally a level
     * @param blockPos    The location of the block
     * @return Always returns {@code false}
     */
    public static boolean never(@NotNull final BlockState blockState, @NotNull final BlockGetter blockGetter,
            @NotNull final BlockPos blockPos) {
        // Return false and do nothing
        return false;
    }
}
