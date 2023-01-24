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

import heckerpowered.magicalfood.common.world.item.enchantment.MagicalFoodEnchantment;
import heckerpowered.magicalfood.common.world.item.enchantment.MagicalHoeEnchantment;
import heckerpowered.magicalfood.common.world.level.block.MagicalFarmBlock;
import heckerpowered.magicalfood.common.world.level.block.MagicalFoodBlock;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.ForgeHooks;

/**
 * {@link ForgeHooks}'s mixin class, this class will not be loaded if mixed with
 * {@link ForgeHooks} successfully. Do not reference this class manually. This
 * class is mainly used to inject {@link #onPlaceItemIntoWorld} method into
 * {@link ForgeHooks#onPlaceItemIntoWorld}.
 *
 * @author Heckerpowered
 * @see ForgeHooks
 * @see Mixin
 * @see MagicalHoeEnchantment
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mixin(ForgeHooks.class)
public final class ForgeHooksMixin {
    /**
     * Do not let anyone instantiate this class
     */
    private ForgeHooksMixin() {
    }

    /**
     * This method will be injected into the {@link ForgeHooks#onPlaceItemIntoWorld}
     * method and execute before the injected method returns. This method is mainly
     * used to replace {@link FarmBlock} to {@link MagicalFarmBlock} if a player
     * successfully performs a plowing action with the a hoe with
     * {@link MagicalHoeEnchantment} enchantment.
     *
     * @param context {@link ForgeHooks#onPlaceItemIntoWorld} method's parameter,
     *                represents the context in which the player interacts with a
     *                block. However, the player may be null.
     * @param info    A returnable callback info, the injected method's return type
     *                is not void so we need to add a {@link CallbackInfoReturnable}
     *                parameter to the end of the parameters even if we do not need
     *                it.
     */
    @Inject(method = "onPlaceItemIntoWorld", at = @At("TAIL"))
    private static final void onPlaceItemIntoWorld(@NotNull final UseOnContext context,
            @NotNull final CallbackInfoReturnable<InteractionResult> info) {
        // Get the world in which the action is performed
        final var level = context.getLevel();

        // Get the location of the interacted block, which is the block the player
        // right-clicked on
        final var location = context.getClickedPos();

        // Determines whether the interacting block has been turned into a farm (it
        // cannot be a magical farm, if the interacting block has been turned into a
        // farm this means the interaction was successful) and whether the item used for
        // the interaction has a MagicalHoe enchantment on it, if the enchantment level
        // is 0 it means the item does not have the specified enchantment on it.
        if (!level.getBlockState(location).is(Blocks.FARMLAND)
                || context.getItemInHand().getEnchantmentLevel(MagicalFoodEnchantment.MAGIC_HOE.get()) == 0) {
            // Return directly to save horizontal space in the code
            return;
        }

        // Set the block to magical farmland with 1,2 and 8 flags (same as vanilla),
        // which flags will cause block update, send changes to client and force any
        // re-renders to run on the main thread instead
        final var blockState = MagicalFoodBlock.MAGICAL_FARM_BLOCK.get().defaultBlockState();
        level.setBlock(location, blockState, 1 | 2 | 8);

        // Raise block change game event
        level.gameEvent(GameEvent.BLOCK_CHANGE, location, GameEvent.Context.of(context.getPlayer(), blockState));
    }
}
