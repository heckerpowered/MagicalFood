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
package heckerpowered.magicalfood.common.world.item.enchantment;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * {@code MagicalFood} mod's enchantment categories, which is mainly constructed
 * an enchantment category that can only be applied to hoes.
 *
 * @author Heckerpowered
 * @see EnchantmentCategory
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@FieldsAreNonnullByDefault
public final class MagicalFoodEnchantmentCategory {

    /**
     * Do not let anyone instantiate this class
     */
    private MagicalFoodEnchantmentCategory() {
    }

    /**
     * An enchantment category that only allows enchantments to be applied to hoes
     */
    public static final EnchantmentCategory HOE = EnchantmentCategory.create("HOE", item -> item instanceof HoeItem);

    /**
     * Slots that enchantments related to hoes applicable, the specific values are
     * main hand and off hand, do not modify this field in any means.
     */
    public static final EquipmentSlot[] HOE_SLOT = new EquipmentSlot[] { EquipmentSlot.MAINHAND,
            EquipmentSlot.OFFHAND };
}
