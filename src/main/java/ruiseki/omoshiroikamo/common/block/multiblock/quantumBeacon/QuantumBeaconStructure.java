package ruiseki.omoshiroikamo.common.block.multiblock.quantumBeacon;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static ruiseki.omoshiroikamo.plugin.structureLib.StructureLibUtils.ofBlockAdderWithPos;

import com.gtnewhorizon.structurelib.alignment.constructable.IMultiblockInfoContainer;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import ruiseki.omoshiroikamo.common.block.multiblock.TieredMultiblockInfoContainer;
import ruiseki.omoshiroikamo.common.init.ModBlocks;

public class QuantumBeaconStructure {

    public static IStructureDefinition<TEQuantumBeaconT1> STRUCTURE_DEFINITION_TIER_1;
    public static IStructureDefinition<TEQuantumBeaconT2> STRUCTURE_DEFINITION_TIER_2;
    public static IStructureDefinition<TEQuantumBeaconT3> STRUCTURE_DEFINITION_TIER_3;
    public static IStructureDefinition<TEQuantumBeaconT4> STRUCTURE_DEFINITION_TIER_4;
    public static IStructureDefinition<TEQuantumBeaconT5> STRUCTURE_DEFINITION_TIER_5;
    public static IStructureDefinition<TEQuantumBeaconT6> STRUCTURE_DEFINITION_TIER_6;

    @SuppressWarnings("unchecked")
    public static void registerStructureInfo() {
        StructureDefinition.Builder<TEQuantumBeaconT1> builder1 = StructureDefinition.builder();
        StructureDefinition.Builder<TEQuantumBeaconT2> builder2 = StructureDefinition.builder();
        StructureDefinition.Builder<TEQuantumBeaconT3> builder3 = StructureDefinition.builder();
        StructureDefinition.Builder<TEQuantumBeaconT4> builder4 = StructureDefinition.builder();
        StructureDefinition.Builder<TEQuantumBeaconT5> builder5 = StructureDefinition.builder();
        StructureDefinition.Builder<TEQuantumBeaconT6> builder6 = StructureDefinition.builder();

        builder1.addShape(QuantumBeaconShapes.STRUCTURE_TIER_1, transpose(QuantumBeaconShapes.SHAPE_TIER_1))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 0))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 0),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 0),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 0)));

        builder2.addShape(QuantumBeaconShapes.STRUCTURE_TIER_2, transpose(QuantumBeaconShapes.SHAPE_TIER_2))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 1))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 1),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 1),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 1)));

        builder3.addShape(QuantumBeaconShapes.STRUCTURE_TIER_3, transpose(QuantumBeaconShapes.SHAPE_TIER_3))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 2))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 2),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 2),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 2)));

        builder4.addShape(QuantumBeaconShapes.STRUCTURE_TIER_4, transpose(QuantumBeaconShapes.SHAPE_TIER_4))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 3))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 3),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 3),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 3)));

        builder5.addShape(QuantumBeaconShapes.STRUCTURE_TIER_5, transpose(QuantumBeaconShapes.SHAPE_TIER_5))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 4))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 4),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 4),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 4)));

        builder6.addShape(QuantumBeaconShapes.STRUCTURE_TIER_6, transpose(QuantumBeaconShapes.SHAPE_TIER_6))
            .addElement('Q', ofBlock(ModBlocks.QUANTUM_BEACON.get(), 5))
            .addElement(
                'P',
                ofChain(
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 0),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 1),
                    ofBlock(ModBlocks.MACHINE_BASE.get(), 2)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TEQuantumBeacon::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FIRE_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_FLIGHT.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NIGHT_VISION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_WATER_BREATHING.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_STRENGTH.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_HASTE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_REGENERATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SATURATION.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_RESISTANCE.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_JUMP_BOOST.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_SPEED.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 5),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 5),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 5)));

        IStructureDefinition<TEQuantumBeaconT1> definition1 = builder1.build();
        STRUCTURE_DEFINITION_TIER_1 = definition1;
        IStructureDefinition<TEQuantumBeaconT2> definition2 = builder2.build();
        STRUCTURE_DEFINITION_TIER_2 = definition2;
        IStructureDefinition<TEQuantumBeaconT3> definition3 = builder3.build();
        STRUCTURE_DEFINITION_TIER_3 = definition3;
        IStructureDefinition<TEQuantumBeaconT4> definition4 = builder4.build();
        STRUCTURE_DEFINITION_TIER_4 = definition4;
        IStructureDefinition<TEQuantumBeaconT5> definition5 = builder5.build();
        STRUCTURE_DEFINITION_TIER_5 = definition5;
        IStructureDefinition<TEQuantumBeaconT6> definition6 = builder6.build();
        STRUCTURE_DEFINITION_TIER_6 = definition6;

        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT1.class, new TieredMultiblockInfoContainer<>(definition1));
        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT2.class, new TieredMultiblockInfoContainer<>(definition2));
        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT3.class, new TieredMultiblockInfoContainer<>(definition3));
        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT4.class, new TieredMultiblockInfoContainer<>(definition4));
        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT5.class, new TieredMultiblockInfoContainer<>(definition5));
        IMultiblockInfoContainer
            .registerTileClass(TEQuantumBeaconT6.class, new TieredMultiblockInfoContainer<>(definition6));
    }

}
