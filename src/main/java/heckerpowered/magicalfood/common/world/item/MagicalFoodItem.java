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
import heckerpowered.magicalfood.common.world.block.MagicalFarmBlock;
import heckerpowered.magicalfood.common.world.block.MagicalFoodBlock;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * The register class of magical food items, any items (including block in the
 * form of items) derived from {@code MagicalFood} mod should be registered at
 * this class.
 *
 * @author Heckerpowered
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFoodItem {

    /**
     * The deferred register of all the items derived from {@code MagicalFood} mod.
     */
    public static final DeferredRegister<Item> DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,
            MagicalFood.MODID);

    /**
     * Do not let anyone instantiate this class
     */
    private MagicalFoodItem() {
    }

    /**
     * The magical farm block in the form of item, see {@link MagicalFarmBlock} for
     * details related to this block
     */
    public static final RegistryObject<BlockItem> MAGICAL_FARM_BLOCK = DEFERRED_REGISTER.register("magical_farmland",
            () -> new BlockItem(MagicalFoodBlock.MAGICAL_FARM_BLOCK.get(), new Item.Properties()) {
                /**
                 * Returns {@code true} if this item has an enchantment glint. By default, this
                 * returns <code>stack.isItemEnchanted()</code>, but other items can override it
                 * (for instance, written books always return true).
                 */
                @Override
                public boolean isFoil(@NotNull final ItemStack stack) {
                    return true;
                }
            });
}
