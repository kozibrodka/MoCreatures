// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.kozibrodka.mocreatures.mocreatures;

import net.kozibrodka.mocreatures.entity.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.CharacterUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class MoCGUI extends Screen
{

    public MoCGUI(LivingEntity entityliving, String s)
    {
        xSize = 256;
        ySize = 181;
        screenTitle = "Choose your Pet's name:";
        NamedEntity = entityliving;
        NameToSet = s;
    }

    public void init()
    {
        buttons.clear();
        Keyboard.enableRepeatEvents(true);
        buttons.add(new ButtonWidget(0, width / 2 - 100, height / 4 + 120, "Done"));
    }

    public void removed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    public void tick()
    {
        updateCounter++;
    }

    protected void buttonClicked(ButtonWidget guibutton)
    {
        if(!guibutton.active)
        {
            return;
        }
        if(guibutton.id == 0)
        {
            if(NamedEntity instanceof EntityKitty)
            {
                ((EntityKitty)NamedEntity).name = NameToSet;
            }
            if(NamedEntity instanceof EntityHorse)
            {
                ((EntityHorse)NamedEntity).name = NameToSet;
            }
            if(NamedEntity instanceof EntityBigCat)
            {
                ((EntityBigCat)NamedEntity).name = NameToSet;
            }
            if(NamedEntity instanceof EntityDolphin)
            {
                ((EntityDolphin)NamedEntity).name = NameToSet;
            }
            if(NamedEntity instanceof EntityShark)
            {
                ((EntityShark)NamedEntity).name = NameToSet;
            }
            minecraft.setScreen(null);
        }
    }

    protected void keyPressed(char c, int i)
    {
        if(i == 14 && NameToSet.length() > 0)
        {
            NameToSet = NameToSet.substring(0, NameToSet.length() - 1);
        }
//        if(allowedCharacters.indexOf(c) < 0 || NameToSet.length() >= 15) goto _L2; else goto _L1
//_L1:
//        new StringBuilder();
//        this;
//        JVM INSTR dup_x1 ;
//        NameToSet;
//        append();
//        c;
//        append();
//        toString();
//        NameToSet;
//_L2:
        if (!(allowedCharacters.indexOf(c) < 0 || NameToSet.length() >= 15)) {
            this.NameToSet = this.NameToSet + c;
        }
    }

    public void render(int i, int j, float f)
    {
        renderBackground();
        int k = minecraft.textureManager.getTextureId("/assets/mocreatures/stationapi/textures/mob/mocname.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.textureManager.bindTexture(k);
        int l = (width - xSize) / 2;
        int i1 = (height - (ySize + 16)) / 2;
        drawTexture(l, i1, 0, 0, xSize, ySize);
        drawCenteredTextWithShadow(textRenderer, screenTitle, width / 2, 100, 0xffffff);
        drawCenteredTextWithShadow(textRenderer, NameToSet, width / 2, 120, 0xffffff);
        super.render(i, j, f);
    }

    protected String screenTitle;
    private LivingEntity NamedEntity;
    private int updateCounter;
    private static final String allowedCharacters;
    private String NameToSet;
    protected int xSize;
    protected int ySize;

    static
    {
        allowedCharacters = CharacterUtils.VALID_CHARACTERS;
    }
}
