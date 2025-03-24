package me.samulsz.utils;

import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

public class NbtUtils {
    private static Class<?> NBTTagCompoundClass;
    private static Class<?> CraftItemStackClass;
    private static Class<?> ItemStackClass;
    private static Method asNMSCopy;
    private static Method hasNBTTagCompound;
    private static Method getNBTTagCompound;
    private static Method setString;
    private static Method getString;
    private static Method setInt;
    private static Method getInt;
    private static Method setBoolean;
    private static Method getBoolean;
    private static Method setNBTTagCompound;
    private static Method asCraftMirror;

    public void load() {
        try {
            CraftItemStackClass = Class.forName("org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack");
            NBTTagCompoundClass = Class.forName("net.minecraft.server.v1_8_R3.NBTTagCompound");
            ItemStackClass = Class.forName("net.minecraft.server.v1_8_R3.ItemStack");
            asNMSCopy = CraftItemStackClass.getDeclaredMethod("asNMSCopy", ItemStack.class);
            hasNBTTagCompound = ItemStackClass.getDeclaredMethod("hasTag");
            getNBTTagCompound = ItemStackClass.getDeclaredMethod("getTag");
            setString = NBTTagCompoundClass.getDeclaredMethod("setString", String.class, String.class);
            getString = NBTTagCompoundClass.getDeclaredMethod("getString", String.class);
            setInt = NBTTagCompoundClass.getDeclaredMethod("setInt", String.class, int.class);
            getInt = NBTTagCompoundClass.getDeclaredMethod("getInt", String.class);
            setBoolean = NBTTagCompoundClass.getDeclaredMethod("setBoolean", String.class, boolean.class);
            getBoolean = NBTTagCompoundClass.getDeclaredMethod("getBoolean", String.class);
            setNBTTagCompound = ItemStackClass.getDeclaredMethod("setTag", NBTTagCompoundClass);
            asCraftMirror = CraftItemStackClass.getDeclaredMethod("asCraftMirror", ItemStackClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public org.bukkit.inventory.ItemStack setInt(org.bukkit.inventory.ItemStack item, String tag, int valor) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
            } else {
                nbtTag = NBTTagCompoundClass.newInstance();
            }
            setInt.invoke(nbtTag, tag, valor);
            setNBTTagCompound.invoke(nmsItem, nbtTag);
            return (org.bukkit.inventory.ItemStack) asCraftMirror.invoke(null, nmsItem);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getInt(org.bukkit.inventory.ItemStack item, String tag) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
                return (int) getInt.invoke(nbtTag, tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public org.bukkit.inventory.ItemStack setString(org.bukkit.inventory.ItemStack item, String tag, String valor) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
            } else {
                nbtTag = NBTTagCompoundClass.newInstance();
            }
            setString.invoke(nbtTag, tag, valor);
            setNBTTagCompound.invoke(nmsItem, nbtTag);
            return (org.bukkit.inventory.ItemStack) asCraftMirror.invoke(null, nmsItem);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getString(org.bukkit.inventory.ItemStack item, String tag) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
                return (String) getString.invoke(nbtTag, tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ItemStack setBoolean(org.bukkit.inventory.ItemStack item, String tag, boolean valor) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
            } else {
                nbtTag = NBTTagCompoundClass.newInstance();
            }
            setBoolean.invoke(nbtTag, tag, valor);
            setNBTTagCompound.invoke(nmsItem, nbtTag);
            return (org.bukkit.inventory.ItemStack) asCraftMirror.invoke(null, nmsItem);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean getBoolean(org.bukkit.inventory.ItemStack item, String tag) {
        try {
            Object nmsItem = asNMSCopy.invoke(null, item);
            Object nbtTag;
            if ((boolean) hasNBTTagCompound.invoke(nmsItem)) {
                nbtTag = getNBTTagCompound.invoke(nmsItem);
                return (boolean) getBoolean.invoke(nbtTag, tag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
