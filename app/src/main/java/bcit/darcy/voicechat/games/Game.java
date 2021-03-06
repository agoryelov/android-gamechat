package bcit.darcy.voicechat.games;

import bcit.darcy.voicechat.GameClient;
import bcit.darcy.voicechat.Packet.Response;
import bcit.darcy.voicechat.VoiceChat;

public abstract class Game {
    boolean hasStarted = false;
    boolean hasEnded = false;
    GameClient client;
    VoiceChat voiceChat;
    int uuid;

    public Game(GameClient client, VoiceChat voice, int uuid) {
        this.client = client;
        this.uuid = uuid;
        this.voiceChat = voice;
    }

    public void startVoiceChat() {
        voiceChat.startSpeak();
        voiceChat.startListen();
        client.printMessage("Client: Voice chat started");
    }

    public void stopVoiceChat() {
        voiceChat.stopSpeak();
        voiceChat.startListen();
        client.printMessage("Client: Voice chat ended");
    }

    public void endGame() {
        hasEnded = true;
        stopVoiceChat();
        client.printMessage("Client: Game ended");
    }

    abstract public String[] getActions();
    abstract public boolean hasEnded();
    abstract public void handleAction(String action);
    abstract public void applyUpdate(Response update);
}
