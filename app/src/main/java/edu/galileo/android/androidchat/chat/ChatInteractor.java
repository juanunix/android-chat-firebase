package edu.galileo.android.androidchat.chat;

/**
 * Created by ykro.
 */
public interface ChatInteractor {
    void sendMessage(String msg);

    void destroyChatListener();
    void subscribeForChatUpates();
    void unSubscribeForChatUpates();
    void changeConnectionStatus(boolean online);

    void setSender();
    void setRecipient(String recipient);
}
