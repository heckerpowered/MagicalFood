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
package heckerpowered.magicalfood.common.world.level.block.state;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;

/**
 *
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFarmBlockState extends BlockState {

    /**
     * Construct a new {@link MagicalFarmBlockState}, normally this constructor
     * should be passed into {@link StateDefinition.Builder#Builder} constructor in
     * the form of reference.
     *
     * @param owner           Represents the block that this state belongs to.
     * @param values          Represents the properties and values of this block
     *                        state.
     * @param propertiesCodec Represents the codec used to encode and decode this
     *                        block state.
     */
    public MagicalFarmBlockState(@NotNull final Block owner,
            @NotNull final ImmutableMap<Property<?>, Comparable<?>> values,
            @NotNull final MapCodec<BlockState> propertiesCodec) {
        super(owner, values, propertiesCodec);
    }
}
