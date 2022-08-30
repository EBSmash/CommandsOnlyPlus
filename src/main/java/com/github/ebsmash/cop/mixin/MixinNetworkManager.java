package com.github.ebsmash.cop.mixin;

import com.github.ebsmash.cop.CommandsOnlyPlus;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {

    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
        if (packet instanceof CPacketChatMessage) {
            String message = ((CPacketChatMessage) packet).getMessage();
            if(CommandsOnlyPlus.enabled) {
                callbackInfo.cancel();
            }
            if(message.toLowerCase(Locale.ROOT).contains("-togglechat") || message.toLowerCase(Locale.ROOT).contains("-tc")){
                callbackInfo.cancel();
                CommandsOnlyPlus.enabled = !CommandsOnlyPlus.enabled;
            }
        }
    }


    @Inject(method = "channelRead0*", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        if(packet instanceof SPacketChat) {
            if (CommandsOnlyPlus.enabled) {
                callbackInfo.cancel();
            }
        }
    }
}
