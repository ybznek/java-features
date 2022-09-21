
enum ConnectionStateOld {
    NotConnected(false),
    Authenticated(true),
    NotAuthenticated(true);

    private final boolean connected;

    ConnectionStateOld(boolean connected) {
        this.connected = connected;
    }
}

public sealed interface ConnectionState /*permits Connected, NotConnected*/ {
}

sealed interface Connected extends ConnectionState {
    String host();

    Integer port();
}

record Authenticated(String host, Integer port, String user) implements Connected {
}

record NotAuthenticated(String host, Integer port) implements Connected {
}

record NotConnected() implements ConnectionState {
}
