package ruiseki.omoshiroikamo.api.client;

import static com.gtnewhorizon.gtnhlib.client.renderer.cel.model.quad.properties.ModelQuadFacing.DIRECTIONS;
import static java.lang.Math.max;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

import com.gtnewhorizon.gtnhlib.client.model.ModelISBRH;
import com.gtnewhorizon.gtnhlib.client.model.baked.BakedModel;
import com.gtnewhorizon.gtnhlib.client.model.loading.ModelRegistry;
import com.gtnewhorizon.gtnhlib.client.renderer.TessellatorManager;
import com.gtnewhorizon.gtnhlib.client.renderer.cel.model.quad.ModelQuadView;
import com.gtnewhorizon.gtnhlib.client.renderer.cel.model.quad.properties.ModelQuadFacing;
import com.gtnewhorizons.angelica.api.ThreadSafeISBRH;

import cpw.mods.fml.client.registry.RenderingRegistry;
import ruiseki.omoshiroikamo.common.util.lib.LibMisc;

@ThreadSafeISBRH(perThread = true)
public class JsonModelISBRH extends ModelISBRH implements IItemRenderer {

    public static final JsonModelISBRH INSTANCE = new JsonModelISBRH();

    /**
     * Any blocks using a JSON model should return this for {@link Block#getRenderType()}.
     */
    public static final int JSON_ISBRH_ID = RenderingRegistry.getNextAvailableRenderId();

    public final Random RAND = new Random();

    public JsonModelISBRH() {
        ModelRegistry.registerModid(LibMisc.MOD_ID);
    }

    @Override
    public void renderInventoryBlock(Block block, int meta, int modelId, RenderBlocks renderer) {
        final Tessellator tesselator = TessellatorManager.get();
        final BakedModel model = getModel(renderer.blockAccess, block, meta, 0, 0, 0);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        tesselator.startDrawingQuads();

        int color = model.getColor(renderer.blockAccess, 0, 0, 0, block, meta, RAND);

        for (ModelQuadFacing dir : DIRECTIONS) {

            final var quads = model.getQuads(renderer.blockAccess, 0, 0, 0, block, meta, dir, RAND, -1, null);
            if (quads.isEmpty()) {
                continue;
            }

            for (ModelQuadView quad : quads) {

                if (quad.getColorIndex() != -1 && color == -1) {
                    color = block.getRenderColor(meta);
                }

                float r = (color & 0xFF) / 255f;
                float g = (color >> 8 & 0xFF) / 255f;
                float b = (color >> 16 & 0xFF) / 255f;

                final float shade = diffuseLight(quad.getComputedFaceNormal());
                tesselator.setColorOpaque_F(r * shade, g * shade, b * shade);
                renderQuad(quad, -0.5f, -0.5f, -0.5f, tesselator, null);
            }
        }

        GL11.glRotated(-90f, 0f, 1f, 0f);

        tesselator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        final Random random = world instanceof World worldIn ? worldIn.rand : RAND;
        final Tessellator tesselator = TessellatorManager.get();

        // Get the model!
        final int meta = world.getBlockMetadata(x, y, z);
        final var model = getModel(world, block, meta, x, y, z);

        int color = model.getColor(world, x, y, z, block, meta, RAND);

        boolean rendered = false;
        for (var dir : DIRECTIONS) {

            final var quads = model.getQuads(world, x, y, z, block, meta, dir, random, color, null);
            if (quads.isEmpty()) {
                continue;
            }

            rendered = true;
            for (final var quad : quads) {
                int quadColor = color;

                if (quad.getColorIndex() != -1) {
                    quadColor = BlockColor.getColor(block, world, x, y, z, quad.getColorIndex());
                }

                final float r = (quadColor & 255) / 255f;
                final float g = (quadColor >> 8 & 255) / 255f;
                final float b = (quadColor >> 16 & 255) / 255f;

                final int lm = getLightMap(block, quad, dir, world, x, y, z, renderer);
                tesselator.setBrightness(lm);

                final float shade = diffuseLight(quad.getComputedFaceNormal());
                tesselator.setColorOpaque_F(r * shade, g * shade, b * shade);
                renderQuad(quad, x, y, z, tesselator, null);
            }
        }

        return rendered;
    }

    private int getLightMap(Block block, ModelQuadView quad, ModelQuadFacing dir, IBlockAccess world, int x, int y,
        int z, RenderBlocks rb) {
        // If the face is aligned or external, pick light outside
        final float avgPos = getAveragePos(quad, dir);
        switch (dir) {
            case POS_X, POS_Y, POS_Z -> {
                if (avgPos >= 1.0) {
                    final int lx = x + dir.getStepX();
                    final int ly = y + dir.getStepY();
                    final int lz = z + dir.getStepZ();
                    return block.getMixedBrightnessForBlock(world, lx, ly, lz);
                }
            }
            case NEG_X, NEG_Y, NEG_Z -> {
                if (avgPos <= 0.0) {
                    final int lx = x + dir.getStepX();
                    final int ly = y + dir.getStepY();
                    final int lz = z + dir.getStepZ();
                    return block.getMixedBrightnessForBlock(world, lx, ly, lz);
                }
            }
        }

        // The face is inset to some degree, pick self light (if transparent)
        if (block.getLightOpacity(world, x, y, z) != 0) {
            return block.getMixedBrightnessForBlock(world, x, y, z);
        }

        // ...or greatest among neighbors otherwise
        int lm = block.getMixedBrightnessForBlock(world, x, y, z);;
        for (int i = 0; i < 6; i++) {
            final var neighbor = DIRECTIONS[i];
            final int lx = x + neighbor.getStepX();
            final int ly = y + neighbor.getStepY();
            final int lz = z + neighbor.getStepZ();
            lm = max(lm, block.getMixedBrightnessForBlock(world, lx, ly, lz));
        }

        return lm;
    }

    private float getAveragePos(ModelQuadView quad, ModelQuadFacing dir) {
        float avg = 0;
        for (int i = 0; i < 4; i++) {
            avg += switch (dir.getAxis()) {
                case X -> quad.getX(i);
                case Y -> quad.getY(i);
                case Z -> quad.getZ(i);
            };
        }
        return avg / 4;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return JSON_ISBRH_ID;
    }

    @Override
    public void renderQuad(ModelQuadView quad, float x, float y, float z, Tessellator tessellator,
        @Nullable IIcon overrideIcon) {
        super.renderQuad(quad, x, y, z, tessellator, overrideIcon);
    }

    @Override
    @SuppressWarnings("unused")
    public BakedModel getModel(IBlockAccess world, Block block, int meta, int x, int y, int z) {
        return super.getModel(world, block, meta, x, y, z);
    }

    public static void renderToEntity(ItemStack stack) {

        Block block = Block.getBlockFromItem(stack.getItem());
        if (block == null) return;

        int meta = stack.getItemDamage();
        BakedModel model = INSTANCE.getModel(null, block, meta, 0, 0, 0);

        Tessellator tesselator = TessellatorManager.get();

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        Minecraft.getMinecraft()
            .getTextureManager()
            .bindTexture(TextureMap.locationBlocksTexture);

        tesselator.startDrawingQuads();

        for (ModelQuadFacing dir : DIRECTIONS) {

            final var quads = model.getQuads(null, 0, 0, 0, block, meta, dir, INSTANCE.RAND, -1, null);
            if (quads.isEmpty()) {
                continue;
            }

            for (ModelQuadView quad : quads) {
                int color;
                if (stack.getItem() != null && quad.getColorIndex() != -1) {
                    color = stack.getItem()
                        .getColorFromItemStack(stack, quad.getColorIndex());
                } else {
                    color = block.getRenderColor(meta);
                }

                float r = (color & 0xFF) / 255f;
                float g = (color >> 8 & 0xFF) / 255f;
                float b = (color >> 16 & 0xFF) / 255f;

                final float shade = diffuseLight(quad.getComputedFaceNormal());
                tesselator.setColorOpaque_F(r * shade, g * shade, b * shade);
                INSTANCE.renderQuad(quad, -0.5f, -0.5f, -0.5f, tesselator, null);
            }
        }

        GL11.glRotated(180f, 0f, 1f, 0f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        tesselator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

    }
}
