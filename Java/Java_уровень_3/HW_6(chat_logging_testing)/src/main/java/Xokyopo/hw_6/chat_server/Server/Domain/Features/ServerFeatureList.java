package Xokyopo.hw_6.chat_server.Server.Domain.Features;

import Xokyopo.hw_6.chat_server.Server.Domain.ClientsList;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ClientFeatures.SendNoFeatureEat;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.Interface.Send;
import Xokyopo.hw_6.chat_server.Server.Domain.Features.ServerFeatures.SendClientList;

import java.util.HashMap;
import java.util.Map;

public class ServerFeatureList {
    private final Map<String, Send> featureList = new HashMap<>();
    private final Send noFeature = new SendNoFeatureEat();
    private final ClientsList clientsList;

    public ServerFeatureList(ClientsList clientsList) {
        this.clientsList = clientsList;
        this.featureList.put("SendClientList", new SendClientList(clientsList));
    }

    public Send getFeature(String name) {
        return this.featureList.getOrDefault(name, this.noFeature);
    }



}
