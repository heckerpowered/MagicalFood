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
package heckerpowered.magicalfood.common.world.item;

import javax.annotation.ParametersAreNonnullByDefault;

import org.jetbrains.annotations.NotNull;

import heckerpowered.magicalfood.common.MagicalFood;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Represents a creative mode tab for Magical Food mod, displaying the items and
 * blocks.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class MagicalFoodCreativeModeTab {
    /**
     * This function is called when creative mode tabs can be registered
     *
     * @param event The event related to register the creative mode tab,event is
     *              fired on the {@link FMLJavaModLoadingContext#getModEventBus()}
     *              mod-specific event bus, on both {@link LogicalSide} logical
     *              sides.
     */
    @SubscribeEvent
    public static final void onRegister(@NotNull final CreativeModeTabEvent.Register event) {
        // Register the creative mode tab here
        event.registerCreativeModeTab(
                // The name of the tab, must be unique
                MagicalFood.getResource(MagicalFood.MODID),
                // The icon supplier is lazy-evaluated, so we just simply construct a new
                // ItemStack
                builder -> builder.icon(() -> new ItemStack(MagicalFoodItem.MAGICAL_FARM_BLOCK.get()))
                        // Add items to display in the tab, the displayItems method accepts a generator
                        // that accepts three parameters.
                        //
                        // The first parameter type is a FeatureFlagSet
                        // that stores up to 64 features that can be turned on or off with a 64-bit
                        // data. For example, if some items are experimental, you can decide whether to
                        // add experimental items to the creative mode tab by first determining whether
                        // the experimental feature is enabled by this type.
                        //
                        // The second parameter type is CreativeModeTab.Output, which by default has
                        // only one implementation, ItemDisplayBuilder. You need to use the accept
                        // method of the type to add any item derived from the ItemLike type to this
                        // creative mode tab. You can also use the acceptAll method to add all ItemStack
                        // instances in the collection to this creative mode tab. accept and acceptAll
                        // methods can both specify the visibility of the item, such as only on the
                        // search tab, only on the current creative mode tab, or both. By default, items
                        // are visible on both the search tab and the current creative mode tab.
                        //
                        // The second parameter type is boolean, indicating if the player who is
                        // currently viewing the creative mode tab has operator permissions, you can use
                        // this parameter to add items that only visible if the player has operator
                        // permissions.
                        //
                        // Normally this generator function will only be called once, but if the
                        // player's permissions or a feature is changed, this creative mode tab will be
                        // rebuilt to display different content than the previous tab.
                        .displayItems((enabledFeatures, output, displayOperatorCreativeTab) -> {
                            // Add items to display here and build the creative mode tab later
                            output.accept(MagicalFoodItem.MAGICAL_FARM_BLOCK.get());
                        }).build());
    }
}
