<#import "./layout.ftlh" as layout>

<@layout.layout ; spring>
<div>
    <div>
        <h1>Spring Boot Tutorial.</h1>
        <h2>Websockets.</h2>
        <div class="row">
            <form id="form">
                <div class="row">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" type="submit">Connect</button>
                    <button id="disconnect" type="submit" disabled="disabled">Disconnect</button>
                </div>
            </form>
        </div>
        <div class="row">
            <form>
                <div>
                    <label class="row" for="name">What is your name?</label>
                    <input type="text" id="name" placeholder="Your name here...">
                    <button id="send" type="submit" disabled="disabled">Send</button>
                </div>
            </form>
        </div>
    </div>
    <div>
        <div id="conversation">
            <table>
                <thead>
                <tr>
                    <th>Greetings:</th>
                </tr>
                </thead>
                <tbody id="greetings"></tbody>
            </table>
        </div>
    </div>
</div>

    <link href="../static/css/websocket.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="../static/scripts/WebSocket.js"></script>
</@layout.layout>