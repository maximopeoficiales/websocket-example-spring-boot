var stompClient = null;
let apiEmpleados= "http://localhost:9000/api/empleado";
$(document).ready(function () {
    console.log("Index page is ready");
    connect();
     charginTableEmpleados();
    $("#send").click(function () {
        sendMessage();
    });
});

function connect() {
    //se hace una instancia al websocket indicado WebSocketConfig
    var socket = new SockJS('/our-websocket');
    //se inicia el socket
    stompClient = Stomp.over(socket);
    //se conecta al socket
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        //me suscribo a los metodos definidos WSService
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/topic/empleado', function (message) {
            let empleado = JSON.parse(message.body);
            console.log(empleado);
            showEmpleado(empleado);
        });
    });
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log("sending message");
    //envia un mensaje con la libreria sock js a determinara uri
    stompClient.send("/ws/message", {}, JSON.stringify({ 'messageContent': $("#message").val() }));
}
function showEmpleado(empleado) {
    $("#empleados").append(`
        <tr>
        <td>${empleado.id}</td>
        <td>${empleado.name}</td>
        <td>${empleado.lastName}</td>
        <td>${empleado.age}</td>
        <td>${empleado.ocupation}</td>
        </tr>
    `);
}
async function charginTableEmpleados(){
    let empleados = await (await fetch(apiEmpleados)).json();
    console.log(empleados);
    empleados.forEach(empleado =>{
        $("#empleados").append(`
        <tr>
        <td>${empleado.id}</td>
        <td>${empleado.name}</td>
        <td>${empleado.lastName}</td>
        <td>${empleado.age}</td>
        <td>${empleado.ocupation}</td>
        </tr>
    `);
    });
}