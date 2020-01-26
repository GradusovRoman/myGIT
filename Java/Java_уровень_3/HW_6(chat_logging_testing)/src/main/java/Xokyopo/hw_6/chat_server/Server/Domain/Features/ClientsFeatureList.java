package Xokyopo.hw_6.chat_server.Server.Domain.Features;

import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures.SendMessageALL;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures.SendPrivateMessage;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures.SendNoFeatureEat;

import java.util.HashMap;
import java.util.Map;

public class ClientsFeatureList {
    //список фич(пока будет реализоват так потом буду надется измени)
    private final Map<String, Send> featureList = new HashMap<>();
    private final Send noFeature = new SendNoFeatureEat();
    private final ClientsList clientsList;

    public ClientsFeatureList(ClientsList clientsList) {
        this.clientsList = clientsList;
        this.featureList.put("SendMessage", new SendMessageALL(clientsList));
        this.featureList.put("SendPrivateMessage", new SendPrivateMessage(clientsList));
    }

    public Send getFeature(String name) {
        return this.featureList.getOrDefault(name, this.noFeature);
    }
}
