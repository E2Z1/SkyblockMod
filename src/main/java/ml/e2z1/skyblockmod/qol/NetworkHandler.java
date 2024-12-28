package ml.e2z1.skyblockmod.qol;

import io.netty.channel.ChannelPipeline;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class NetworkHandler {

    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        ChannelPipeline pipeline = event.manager.channel().pipeline();

        // Add the PacketInterceptor to the pipeline
        pipeline.addBefore("packet_handler", "lapis_packet_interceptor", new PacketInterceptor());
    }
}
