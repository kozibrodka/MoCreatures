package net.kozibrodka.mocreatures.mocreatures;

import net.fabricmc.loader.api.FabricLoader;
import net.kozibrodka.mocreatures.network.PoisonPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.minecraft.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import org.lwjgl.opengl.GL11;

public class MocTick {

    public static boolean poisoned = false;
    public static boolean freezed = false;
    public static boolean burned = false;
    public static int poisoncounter = 0;
    public static int freezedcounter = 0;
    private long gameClock = 0L;
    static Minecraft mc = Minecraft.INSTANCE;


    public boolean MocreaturesTickInGame(Minecraft minecraft){
        if(minecraft.world != null && gameClock != minecraft.world.getTime())
        {
            gameClock = minecraft.world.getTime();
            onTick(minecraft);
        }
        renderTick();

        return true;
    }

    public boolean renderTick(){
        if(poisoned) {
            renderHaze(0.1F, "/assets/mocreatures/stationapi/textures/particle/maskgreen.png");
        }
        if(freezed){
            renderHaze(0.1F, "/assets/mocreatures/stationapi/textures/particle/maskblue.png");
        }
        /// czerwony na podpalenie ewentualnie.
        return true;
    }

    public boolean onTick(Minecraft minecraft)
    {

            ClientPlayerEntity player = minecraft.player;
            if(poisoned) {
                ++poisoncounter;
                if(poisoncounter > 200 || player.dead) {
                    poisoncounter = 0;
                    poisoned = false;
                }
                MoCTools.disorientEntity(player);
                if(poisoncounter % 20 == 0) {
                    if(!player.world.isRemote){
                        minecraft.world.addParticle("slime", player.x, player.y + 0.5D, player.z, 0.0D, 0.5D, 0.0D);
                        player.damage(null, 1);
                    }else{
                        PacketHelper.send(new PoisonPacket(3));
                    }
                }
            }

            if(freezed) {
                ++freezedcounter;
                if(freezedcounter > 200 || player.dead) {
                    freezedcounter = 0;
                    freezed = false;
                }
                MoCTools.slowEntity(player);
                if(freezedcounter % 20 == 0) {
                    if(!player.world.isRemote){
//                        minecraft.world.addParticle("bubble", player.x, player.y + 0.5D, player.z, 0.0D, 0.5D, 0.0D);
                        minecraft.world.addParticle("snowballpoof", player.x, player.y + 0.5D, player.z, 0.0D, 0.5D, 0.0D);
                        player.damage(null, 1);
                    }else{
                        PacketHelper.send(new PoisonPacket(4));
                    }
                }
            }

            if(burned) {
                if(player.dead || player.fireTicks < 1) {
                    burned = false;
                }
//                renderHaze(0.2F, "/mocreatures/maskred.png");
            }

        return true;
    }

    public static void renderHaze(float f, String s) {
        ScreenScaler scaledresolution = new ScreenScaler(mc.options, mc.displayWidth, mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.textureManager.getTextureId(s));
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        tessellator.vertex(0.0D, (double)j, -90.0D, 0.0D, 1.0D);
        tessellator.vertex((double)i, (double)j, -90.0D, 1.0D, 1.0D);
        tessellator.vertex((double)i, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.vertex(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
    }
}
