package ruiseki.omoshiroikamo.client.render.block.solarArray;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class SolarCellISBRH implements ISimpleBlockRenderingHandler {

    public static int renderId;

    public SolarCellISBRH() {
        renderId = RenderingRegistry.getNextAvailableRenderId();
    }

    private static final float HEIGHT = 13f / 16f;

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        Tessellator tess = Tessellator.instance;

        GL11.glTranslatef(-0.5f, -0.5f, -0.5f);

        renderer.setRenderBounds(0, 0, 0, 1, HEIGHT, 1);

        // BOTTOM
        tess.startDrawingQuads();
        renderer.renderFaceYNeg(block, 0, 0, 0, block.getIcon(0, metadata));
        tess.draw();

        // TOP
        tess.startDrawingQuads();
        renderer.renderFaceYPos(block, 0, 0, 0, block.getIcon(1, metadata));
        tess.draw();

        // NORTH
        tess.startDrawingQuads();
        renderer.renderFaceZNeg(block, 0, 0, 0, block.getIcon(2, metadata));
        tess.draw();

        // SOUTH
        tess.startDrawingQuads();
        renderer.renderFaceZPos(block, 0, 0, 0, block.getIcon(3, metadata));
        tess.draw();

        // WEST
        tess.startDrawingQuads();
        renderer.renderFaceXNeg(block, 0, 0, 0, block.getIcon(4, metadata));
        tess.draw();

        // EAST
        tess.startDrawingQuads();
        renderer.renderFaceXPos(block, 0, 0, 0, block.getIcon(5, metadata));
        tess.draw();

        GL11.glTranslatef(0.5f, 0.5f, 0.5f);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {

        renderer.setRenderBounds(0, 0, 0, 1, HEIGHT, 1);

        return renderer.renderStandardBlock(block, x, y, z);
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}
