
package net.kozibrodka.mocreatures.item;


import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItem;

public class HayStack extends TemplateItem
{

    public HayStack(Identifier i)
    {
        super(i);
        maxCount = 16;
    }
}
