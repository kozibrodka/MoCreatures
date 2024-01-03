// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.item;


import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class HayStack extends TemplateItem
{

    public HayStack(Identifier i)
    {
        super(i);
        maxStackSize = 16;
    }
}
