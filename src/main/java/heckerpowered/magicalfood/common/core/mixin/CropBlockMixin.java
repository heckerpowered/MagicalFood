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
package heckerpowered.magicalfood.common.core.mixin;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import heckerpowered.magicalfood.common.world.level.block.MagicalFarmBlock;
import heckerpowered.magicalfood.common.world.level.block.MagicalFoodBlock;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * {@link CropBlock}'s mixin class, this class will not be loaded if mixed with
 * {@link CropBlock} successfully. Do not reference this class manually. This
 * class is mainly used to enable {@link CropBlock} to be planted on
 * {@link MagicalFarmBlock}
 *
 * @author Heckerpowered
 * @see CropBlock
 * @see MagicalFarmBlock
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mixin(CropBlock.class)
public final class CropBlockMixin {
    /**
     * Do not let anyone instantiate this class
     */
    private CropBlockMixin() {
    }

    /**
     * This method will be injected into the {@link CropBlock#mayPlaceOn} method and
     * execute before the injected method returns. This method modifies the return
     * value of the injected method {@link CropBlock#mayPlaceOn} so that the
     * {@link CropBlock} can be placed on {@link MagicalFarmBlock}.
     *
     * @param blockState The state of a block in the world, including its properties
     *                   and values.
     * @param level      Provide access to the block and fluid state at a specific
     *                   position, as well as other related information, in the game
     *                   world.
     * @param location   The location of the block.
     * @param info       A returnable callback info, the injected method's return
     *                   type is not void so we need to add a
     *                   {@link CallbackInfoReturnable} parameter to the end of the
     *                   parameters even if we do not need it.
     */
    @Inject(method = "mayPlaceOn", at = @At("TAIL"), cancellable = true)
    private final void mayPlaceOn(@NotNull final BlockState blockState, @NotNull final BlockGetter level,
            @NotNull final BlockPos location, @NotNull final CallbackInfoReturnable<Boolean> info) {
        // Modify the return value, allow CropBlock to be placed on MagicalFarmBlock
        info.setReturnValue(info.getReturnValueZ() || blockState.is(MagicalFoodBlock.MAGICAL_FARM_BLOCK.get()));
    }
}
