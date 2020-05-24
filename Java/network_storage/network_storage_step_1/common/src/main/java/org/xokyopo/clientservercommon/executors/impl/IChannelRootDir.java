package org.xokyopo.clientservercommon.executors.impl;

import io.netty.channel.Channel;

public interface IChannelRootDir {
    String getRootDir(Channel channel);
}
