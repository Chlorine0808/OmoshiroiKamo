package ruiseki.omoshiroikamo.core.common.block.state;

import com.gtnewhorizon.gtnhlib.blockstate.core.BlockProperty;
import com.gtnewhorizon.gtnhlib.blockstate.core.BlockState;
import com.gtnewhorizon.gtnhlib.blockstate.properties.DirectionBlockProperty;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import static com.gtnewhorizon.gtnhlib.blockstate.registry.BlockPropertyRegistry.getBlockState;
import static com.gtnewhorizon.gtnhlib.blockstate.registry.BlockPropertyRegistry.registerProperty;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

public class BlockStateUtils {

    public static void registerFacingProp(Class<? extends Block> clazz) {

        registerProperty(clazz, DirectionBlockProperty.facing(0b11,
            dir -> switch (dir) {
                case SOUTH -> 0;
                case EAST  -> 1;
                case NORTH -> 2;
                case WEST  -> 3;
                default   -> 0;
            },
            meta -> switch (meta) {
                case 0 -> SOUTH;
                case 1 -> EAST;
                case 2 -> NORTH;
                case 3 -> WEST;
                default -> NORTH;
            }
        ));
    }

    public static void setFacingProp(World world, int x, int y, int z, ForgeDirection facing) {
        BlockState state = getBlockState(world, x, y, z);
        state.setPropertyValue("facing", facing);
        state.place(world, x, y, z);
        state.close();
    }

    public static ForgeDirection getFacingProp(World world, int x, int y, int z) {
        BlockState state = getBlockState(world, x, y, z);
        return state.getPropertyValue("facing");
    }

    public static float getFacingAngle(ForgeDirection facing) {
        return switch (facing) {
            case SOUTH -> 270f;
            case WEST  -> 90f;
            case NORTH -> 0f;
            case EAST  -> 180f;
            default    -> 0f;
        };
    }
}
