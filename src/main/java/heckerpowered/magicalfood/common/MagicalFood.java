/**
* Copyright (C) 2022 Heckerpowered Corporation
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
package heckerpowered.magicalfood.common;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import heckerpowered.magicalfood.common.world.block.MagicalFoodBlock;
import heckerpowered.magicalfood.common.world.item.MagicalFoodItem;
import heckerpowered.magicalfood.common.world.item.enchantment.MagicalFoodEnchantment;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * The magical food mod
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mod(MagicalFood.MODID)
public final class MagicalFood {

    /** Define mod id in a common place for everything to reference */
    public static final String MODID = "magicalfood";

    /**
     * Construct a new MagicalFood mod instance, this constructor should not be
     * called manually.
     */
    public MagicalFood() {
        // Get the mod's event bus to allow subscription to Mod specific events
        final var eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Add deferred registers' event handlers to the event bus
        MagicalFoodItem.DEFERRED_REGISTER.register(eventBus);
        MagicalFoodBlock.DEFERRED_REGISTER.register(eventBus);
        MagicalFoodEnchantment.DEFERRED_REGISTER.register(eventBus);
    }

    /**
     * Returns a resource location with {@code #MODID} as the namespace and
     * {@code path} as the path.
     *
     * @param path The path of the resource location
     * @return An immutable location of a resource, in terms of a path and
     *         namespace.
     * @throws NullPointerException if {@code path} is null
     */
    public static final @NotNull ResourceLocation getResource(@NotNull final String path) {
        return new ResourceLocation(MODID, path);
    }
}
