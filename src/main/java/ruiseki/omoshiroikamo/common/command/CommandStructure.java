package ruiseki.omoshiroikamo.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import ruiseki.omoshiroikamo.common.structure.StructureManager;

/**
 * 構造体設定をリロードするコマンド
 * 使用法: /okstructure reload
 */
public class CommandStructure extends CommandBase {

    @Override
    public String getCommandName() {
        return "ok";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/ok reload - Reload structure configurations";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2; // OP required
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sendUsage(sender);
            return;
        }

        String subcommand = args[0].toLowerCase();

        switch (subcommand) {
            case "reload":
                reloadStructures(sender);
                break;
            case "status":
                showStatus(sender);
                break;
            default:
                sendUsage(sender);
                break;
        }
    }

    private void reloadStructures(ICommandSender sender) {
        sender.addChatMessage(
            new ChatComponentText(EnumChatFormatting.YELLOW + "[OmoshiroiKamo] Reloading structure configurations..."));

        try {
            StructureManager.getInstance()
                .reload();

            if (StructureManager.getInstance()
                .hasErrors()) {
                int errorCount = StructureManager.getInstance()
                    .getErrorCollector()
                    .getErrorCount();
                sender.addChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.RED + "[OmoshiroiKamo] Reload completed with " + errorCount + " error(s)!"));
                sender.addChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.GRAY + "Check: config/omoshiroikamo/structures/errors.txt"));
            } else {
                sender.addChatMessage(
                    new ChatComponentText(
                        EnumChatFormatting.GREEN + "[OmoshiroiKamo] Structure configurations reloaded successfully!"));
            }
        } catch (Exception e) {
            sender.addChatMessage(
                new ChatComponentText(EnumChatFormatting.RED + "[OmoshiroiKamo] Reload failed: " + e.getMessage()));
        }
    }

    private void showStatus(ICommandSender sender) {
        StructureManager manager = StructureManager.getInstance();

        sender
            .addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "=== OmoshiroiKamo Structure Status ==="));

        sender.addChatMessage(
            new ChatComponentText(
                EnumChatFormatting.WHITE + "Initialized: "
                    + (manager.isInitialized() ? EnumChatFormatting.GREEN + "Yes" : EnumChatFormatting.RED + "No")));

        if (manager.hasErrors()) {
            String summary = manager.getErrorCollector()
                .getSummary();
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Errors: " + summary));
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Errors: None"));
        }
    }

    private void sendUsage(ICommandSender sender) {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "Usage:"));
        sender.addChatMessage(
            new ChatComponentText(
                EnumChatFormatting.WHITE + "  /ok reload"
                    + EnumChatFormatting.GRAY
                    + " - Reload structure configurations"));
        sender.addChatMessage(
            new ChatComponentText(
                EnumChatFormatting.WHITE + "  /ok status" + EnumChatFormatting.GRAY + " - Show current status"));
    }
}
