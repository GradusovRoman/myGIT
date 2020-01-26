package Xokyopo.hw_6.chat_server.Server.DAOAdapters.LoggerAsapte;

public class LoggerAdapte implements LoggerAdapterRequest{
    private final LoggerAdapterResponse loggerAdapterResponse;

    public LoggerAdapte(LoggerAdapterResponse loggerAdapterResponse) {
        this.loggerAdapterResponse = loggerAdapterResponse;
    }
}
