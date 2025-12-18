package ruiseki.omoshiroikamo.common.block.multiblock.solarArray;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofBlock;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.ofChain;
import static com.gtnewhorizon.structurelib.structure.StructureUtility.transpose;
import static ruiseki.omoshiroikamo.plugin.structureLib.StructureLibUtils.ofBlockAdderWithPos;

import com.gtnewhorizon.structurelib.alignment.constructable.IMultiblockInfoContainer;
import com.gtnewhorizon.structurelib.structure.IStructureDefinition;
import com.gtnewhorizon.structurelib.structure.StructureDefinition;

import ruiseki.omoshiroikamo.common.block.multiblock.structure.TieredMultiblockInfoContainer;
import ruiseki.omoshiroikamo.common.init.ModBlocks;
import ruiseki.omoshiroikamo.common.util.Logger;

public class SolarArrayStructure {

    // spotless:off
    public static final String STRUCTURE_TIER_1 = "tier1";
    public static final String[][] SHAPE_TIER_1 = new String[][]{
        {
            "FFFFF",
            "FGGGF",
            "FGGGF",
            "FGGGF",
            "FFFFF"},
        {
            "     ",
            " A A ",
            "  Q  ",
            " A A ",
            "     ",
        }};
    public static final String STRUCTURE_TIER_2 = "tier2";
    public static final String[][] SHAPE_TIER_2 = new String[][]{
        {
            "FFFFFFF",
            "FGGGGGF",
            "FGGGGGF",
            "FGGGGGF",
            "FGGGGGF",
            "FGGGGGF",
            "FFFFFFF"
        },
        {
            "       ",
            "       ",
            "  A A  ",
            "   Q   ",
            "  A A  ",
            "       ",
            "       "
        }};
    public static final String STRUCTURE_TIER_3 = "tier3";
    public static final String[][] SHAPE_TIER_3 = new String[][]{
        {
            "FFFFFFFFF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FGGGGGGGF",
            "FFFFFFFFF"
        },
        {
            "         ",
            "         ",
            "   A A   ",
            "  A   A  ",
            "    Q    ",
            "  A   A  ",
            "   A A   ",
            "         ",
            "         "
        }};
    public static final String STRUCTURE_TIER_4 = "tier4";
    public static final String[][] SHAPE_TIER_4 = new String[][]{
        {
            "FFFFFFFFFFF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FGGGGGGGGGF",
            "FFFFFFFFFFF"
        },
        {
            "           ",
            "           ",
            "           ",
            "    A A    ",
            "   A   A   ",
            "     Q     ",
            "   A   A   ",
            "    A A    ",
            "           ",
            "           ",
            "           "
        }};
    public static final String STRUCTURE_TIER_5 = "tier5";
    public static final String[][] SHAPE_TIER_5 = new String[][]{
        {
            "FFFFFFFFFFFFF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FGGGGGGGGGGGF",
            "FFFFFFFFFFFFF"
        },
        {
            "             ",
            "             ",
            "             ",
            "             ",
            "    AA AA    ",
            "    A   A    ",
            "      Q      ",
            "    A   A    ",
            "    AA AA    ",
            "             ",
            "             ",
            "             ",
            "             "
        }};
    public static final String STRUCTURE_TIER_6 = "tier6";
    public static final String[][] SHAPE_TIER_6 = new String[][]{
        {
            "FFFFFFFFFFFFFFF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FGGGGGGGGGGGGGF",
            "FFFFFFFFFFFFFFF"
        },
        {
            "               ",
            "               ",
            "               ",
            "               ",
            "               ",
            "     AA AA     ",
            "     A   A     ",
            "       Q       ",
            "     A   A     ",
            "     AA AA     ",
            "               ",
            "               ",
            "               ",
            "               ",
            "               "
        }};
    // spotless:on

    public static final int[][] TIER_OFFSET = { { 2, 1, 2 }, { 3, 1, 3 }, { 4, 1, 4 }, { 5, 1, 5 }, { 6, 1, 6 },
        { 7, 1, 7 } };
    public static IStructureDefinition<TESolarArrayT1> STRUCTURE_DEFINITION_TIER_1;
    public static IStructureDefinition<TESolarArrayT2> STRUCTURE_DEFINITION_TIER_2;
    public static IStructureDefinition<TESolarArrayT3> STRUCTURE_DEFINITION_TIER_3;
    public static IStructureDefinition<TESolarArrayT4> STRUCTURE_DEFINITION_TIER_4;
    public static IStructureDefinition<TESolarArrayT5> STRUCTURE_DEFINITION_TIER_5;
    public static IStructureDefinition<TESolarArrayT6> STRUCTURE_DEFINITION_TIER_6;

    @SuppressWarnings("unchecked")
    public static void registerStructureInfo() {
        StructureDefinition.Builder<TESolarArrayT1> builder1 = StructureDefinition.builder();
        StructureDefinition.Builder<TESolarArrayT2> builder2 = StructureDefinition.builder();
        StructureDefinition.Builder<TESolarArrayT3> builder3 = StructureDefinition.builder();
        StructureDefinition.Builder<TESolarArrayT4> builder4 = StructureDefinition.builder();
        StructureDefinition.Builder<TESolarArrayT5> builder5 = StructureDefinition.builder();
        StructureDefinition.Builder<TESolarArrayT6> builder6 = StructureDefinition.builder();

        builder1.addShape(STRUCTURE_TIER_1, transpose(SHAPE_TIER_1))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 0))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 0),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 0),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 0)));

        builder2.addShape(STRUCTURE_TIER_2, transpose(SHAPE_TIER_2))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 1))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 1),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 1),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 1)));

        builder3.addShape(STRUCTURE_TIER_3, transpose(SHAPE_TIER_3))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 2))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 2),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 2),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 2)));

        builder4.addShape(STRUCTURE_TIER_4, transpose(SHAPE_TIER_4))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 3))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 3),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 3),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 3)));

        builder5.addShape(STRUCTURE_TIER_5, transpose(SHAPE_TIER_5))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 4))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 4),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 4),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 4)));

        builder6.addShape(STRUCTURE_TIER_6, transpose(SHAPE_TIER_6))
            .addElement('Q', ofBlock(ModBlocks.SOLAR_ARRAY.get(), 5))
            .addElement(
                'G',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 0),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 1),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 2),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 3),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 4),
                    ofBlock(ModBlocks.SOLAR_CELL.get(), 5)))
            .addElement(
                'A',
                ofChain(
                    ofBlockAdderWithPos(TESolarArray::addToMachine, ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_NULL.get(), 0),
                    ofBlock(ModBlocks.MODIFIER_PIEZO.get(), 0)))
            .addElement(
                'F',
                ofChain(
                    ofBlock(ModBlocks.BASALT_STRUCTURE.get(), 5),
                    ofBlock(ModBlocks.HARDENED_STRUCTURE.get(), 5),
                    ofBlock(ModBlocks.ALABASTER_STRUCTURE.get(), 5)));

        IStructureDefinition<TESolarArrayT1> definition1 = builder1.build();
        STRUCTURE_DEFINITION_TIER_1 = definition1;
        IStructureDefinition<TESolarArrayT2> definition2 = builder2.build();
        STRUCTURE_DEFINITION_TIER_2 = definition2;
        IStructureDefinition<TESolarArrayT3> definition3 = builder3.build();
        STRUCTURE_DEFINITION_TIER_3 = definition3;
        IStructureDefinition<TESolarArrayT4> definition4 = builder4.build();
        STRUCTURE_DEFINITION_TIER_4 = definition4;
        IStructureDefinition<TESolarArrayT5> definition5 = builder5.build();
        STRUCTURE_DEFINITION_TIER_5 = definition5;
        IStructureDefinition<TESolarArrayT6> definition6 = builder6.build();
        STRUCTURE_DEFINITION_TIER_6 = definition6;

        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT1.class, new TieredMultiblockInfoContainer<>(definition1));
        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT2.class, new TieredMultiblockInfoContainer<>(definition2));
        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT3.class, new TieredMultiblockInfoContainer<>(definition3));
        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT4.class, new TieredMultiblockInfoContainer<>(definition4));
        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT5.class, new TieredMultiblockInfoContainer<>(definition5));
        IMultiblockInfoContainer
            .registerTileClass(TESolarArrayT6.class, new TieredMultiblockInfoContainer<>(definition6));

        Logger.info("Register Solar Array Structure Info");
    }
}
