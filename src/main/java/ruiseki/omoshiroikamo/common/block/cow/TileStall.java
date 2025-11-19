package ruiseki.omoshiroikamo.common.block.cow;

import static ruiseki.omoshiroikamo.common.entity.cow.EntityCowsCow.PROGRESS_NBT;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import lombok.Getter;
import ruiseki.omoshiroikamo.api.client.IProgressTile;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistry;
import ruiseki.omoshiroikamo.api.entity.cow.CowsRegistryItem;
import ruiseki.omoshiroikamo.api.fluid.SmartTank;
import ruiseki.omoshiroikamo.common.block.abstractClass.AbstractTE;
import ruiseki.omoshiroikamo.common.entity.cow.EntityCowsCow;

public class TileStall extends AbstractTE implements IFluidHandler, IProgressTile {

    public static final int amount = FluidContainerRegistry.BUCKET_VOLUME * 10;

    public SmartTank tank = new SmartTank(amount);

    private int progress;
    private int maxProgress;

    @Getter
    private int cowType;
    private int cowGain;
    private int cowGrowth;
    private boolean cowIsChild;
    private NBTTagCompound cowNBT;
    private final Random rand = new Random();

    public TileStall() {}

    public void setCow(EntityCowsCow cow) {
        cowNBT = new NBTTagCompound();
        cow.writeEntityToNBT(cowNBT);
        cowType = cow.getType();
        cowGain = cow.getGain();
        cowGrowth = cow.getGrowth();
        cowIsChild = cow.isChild();
    }

    public boolean hasCow() {
        return cowNBT != null;
    }

    public EntityCowsCow getCow(World world) {
        if (cowNBT == null) {
            return null;
        }
        EntityCowsCow cow = new EntityCowsCow(world);
        cowNBT.setInteger(PROGRESS_NBT, maxProgress);
        cow.readEntityFromNBT(cowNBT);
        return cow;
    }

    private CowsRegistryItem getCowDesc() {
        return CowsRegistry.INSTANCE.getByType(cowType);
    }

    @Override
    public float getProgress() {
        return ((float) progress / (float) maxProgress) * 100f;
    }

    @Override
    public void setProgress(float percent) {
        progress = (int) ((percent / 100f) * maxProgress);
    }

    @Override
    public boolean isActive() {
        return getProgress() > 0;
    }

    @Override
    protected boolean processTasks(boolean redstoneCheckPassed) {
        if (!this.worldObj.isRemote && hasCow() && !cowIsChild) {
            progress++;
            if (progress >= maxProgress) {
                CowsRegistryItem cowDesc = getCowDesc();
                FluidStack fluid = cowDesc.createMilkFluid();
                int gain = cowGain;

                if (fluid != null) {
                    if (gain >= 5) {
                        fluid.amount += cowDesc.createMilkFluid().amount;
                    }
                    if (gain >= 10) {
                        fluid.amount += cowDesc.createMilkFluid().amount;
                    }

                    int filled = tank.fill(fluid, true);
                    if (filled >= fluid.amount) {
                        resetMaxProgress();
                    }
                }
            }
        }
        return false;
    }

    private void resetMaxProgress() {
        if (!hasCow()) {
            return;
        }

        CowsRegistryItem cowDesc = getCowDesc();
        int baseTime = cowDesc.getMaxTime() + rand.nextInt(cowDesc.getMaxTime() - cowDesc.getMinTime());
        maxProgress = Math.max(1, (int) ((baseTime * (10f - cowGrowth + 1f)) / 10f) * 2);
        progress = 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (!canDrain(from, resource.getFluid())) {
            return null;
        }
        return tank.drain(resource, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return tank.canDrainFluidType(fluid);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    @Override
    protected void writeCommon(NBTTagCompound root) {
        super.writeCommon(root);
        tank.writeCommon("tank", root);
        root.setInteger("progress", progress);
        root.setInteger("maxProgress", maxProgress);
        root.setInteger("cowType", cowType);
        root.setInteger("cowGain", cowGain);
        root.setInteger("cowGrowth", cowGrowth);
        root.setBoolean("cowIsChild", cowIsChild);
        if (cowNBT != null) {
            root.setTag("cowNBT", cowNBT);
        }
    }

    @Override
    protected void readCommon(NBTTagCompound root) {
        super.readCommon(root);
        tank.readCommon("tank", root);
        progress = root.getInteger("progress");
        maxProgress = root.getInteger("maxProgress");
        cowType = root.getInteger("cowType");
        cowGain = root.getInteger("cowGain");
        cowGrowth = root.getInteger("cowGrowth");
        cowIsChild = root.getBoolean("cowIsChild");
        if (root.hasKey("cowNBT")) {
            cowNBT = root.getCompoundTag("cowNBT");
        }
    }

    @Override
    public TileEntity getTileEntity() {
        return this;
    }
}
