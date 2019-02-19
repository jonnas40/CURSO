var num=1;
var player=1;
var gameid;
var user;
var pass;

////////////////////////////////////////////////////
//   Funcao para verificar se um numero e par
////////////////////////////////////////////////////
function isPair(num) {
  if(num%2 == 0){
    return true;
  }
  else {
    return false;
  }
}

////////////////////////////////////////////////////
//   Funcao para registar o utilizador, se ja existir
//   entao confirma se a password esta correta
////////////////////////////////////////////////////
function register(username, password){

  let loginfo = {
    nick: username,   //argumentos usados no fetch
    pass: password
  }
  let tofetch = {
    method: 'post',
    body: JSON.stringify(loginfo)
  }
  fetch("http://twserver.alunos.dcc.fc.up.pt:8008/register",tofetch)
  .then(function(response){
    return response.json();
  })
  .then(function(data){
    if (data.error != null) {     // se a palavra passe estiver errada
      document.getElementById('logerror').style.display = "block";
    }
    else{
      // atualiza o topo direito com o nome do jogador
      document.getElementById('logname').innerHTML = username;
      // atualiza o nome nas classificacoes
      document.getElementById('claname').innerHTML = "vs PC";
      config();
    }
  })
  .catch(function(err){
    console.log("Fetch Failed", err);
  })
}

////////////////////////////////////////////////////
//   Funcao ao dado um tamanho, retorna uma hash
//   para identificacao do jogo
////////////////////////////////////////////////////
function join(username, password, board_Size){
  document.getElementById('config').style.display = "none";
  let bsize = {
    rows: parseInt(board_Size.rows),
    columns: parseInt(board_Size.columns)
  }
  let joininfo = {
    group: 40,
    nick: username,
    pass: password,
    size: bsize
  }
  let tofetch = {
    method: 'post',
    body: JSON.stringify(joininfo)
  }
  fetch("http://twserver.alunos.dcc.fc.up.pt:8008/join",tofetch)
  .then(function(response){
    return response.json();
  })
  .then(function(data){
    if(data.error == null){
      gameid = data.game;
      document.getElementById('gameid').innerHTML = "gameid: " + String(gameid);
      document.getElementById('bdesist').setAttribute("onclick", "leave("+'gameid'+")");
      var log = document.getElementById('log');
      while (log.firstChild){             // limpa o log
        log.removeChild(log.firstChild);
      }
      update(gameid, username);
      // funcao para fazer o tabuleiro com o 1 dar overload para que tenha as definicoes online
      makeBoard(board_Size.rows, board_Size.columns, 1);
    }
  })
  .catch(function(error) {
    console.log('Fetch failed', error);
  })
}

////////////////////////////////////////////////////
//   Funcao que vai receber o numero da coluna que o
//   adversario jogou
////////////////////////////////////////////////////
function update(gameid, username){
  let prevturn = "";
  var column;
  eventSource = new EventSource("http://twserver.alunos.dcc.fc.up.pt:8008/update?nick="+username+"&game="+gameid);
  eventSource.onmessage = function(event){
    u = JSON.parse(event.data);
    column = u.column;
    turn = u.turn;
    // se o numero da jogada for par
    if(column != undefined && turn != document.getElementById('logname') && isPair(num)==true){
      jog2PutChip(column, turn);
      num++;
    }
    else if(column != undefined && isPair(num)==false){ // se o numero da jogada for impar
      jog1PutChip(column, turn);
      num++
    }
    if (u.winner != undefined && num != 1){  // em caso de vitoria
      if(u.winner == username){
        writelog("lwin");
      }
      else{
        writelog("nwin");
      }
      analyze(0, 1);
      analyze(1, 1);
      winner();
      eventSource.close();  // fecha o SSE
      num = 1;
    }
  }
  eventSource.onerror = function(err){
    console.log("EventSource error:", err);
  }
}

////////////////////////////////////////////////////
//   Funcao que notifica o servidor da jogada feita
////////////////////////////////////////////////////
function notify(col, colu) {
  let noteinfo = {
    nick: username,
    pass: password,
    game: gameid,
    column: col
  }
  let tofetch = {
    method: 'post',
    body: JSON.stringify(noteinfo)
  }
  fetch("http://twserver.alunos.dcc.fc.up.pt:8008/notify", tofetch)
  .then(function(response){
    if(response.status == 200){   // se o servidor retorna OK
      return response.json();
    }
    else{
      writelog("impossible");
      return response.json();
    }
  })
  .catch(function(error) {
    console.log('Fetch failed', error);
  })
}

////////////////////////////////////////////////////
//   Funcao para sair do jogo
////////////////////////////////////////////////////
function leave(gameid){
  let leaveinfo = {
    nick: username,
    pass: password,
    game: gameid
  }
  let tofetch = {
    method:'post',
    body: JSON.stringify(leaveinfo)
  }
  fetch("http://twserver.alunos.dcc.fc.up.pt:8008/leave", tofetch)
  .then(function(response){
    return response.json();
  })
  .then(function(data){
    if(data.winner == undefined){           //Quando um jogador sai sem emparelhar
      writelog("leave");

    }

    else if(data.winner != document.getElementById('logname').innerHTML){  // se o jogador sair
      writelog("playleave");
    }

    else if(data.winner == document.getElementById('logname').innerHTML){  // se o adversario sair
      writelog("opleave");
    }

  })
  .catch(function(error){
    console.log('Fetch failed', error);
  })
}

////////////////////////////////////////////////////
//   Funcao para obter os rankings
////////////////////////////////////////////////////
function ranking(){
  let aux_size = {
    rows: parseInt(document.getElementById('nalt').value),
    columns: parseInt(document.getElementById('nlar').value)
  }
  let rankinfo = {
    size: aux_size
  }

  let tofetch = {
    method: 'post',
    body: JSON.stringify(rankinfo)
  }
  fetch("http://twserver.alunos.dcc.fc.up.pt:8008/ranking", tofetch)
  .then(function(response){
    return response.json();
  })
  .then(function(data){
    var body = document.getElementById('rankbody');
    while (body.firstChild){
      body.removeChild(body.firstChild);  // limpa o corpo da tabela caso ja tenha algo la
    }
    // escreve a tabela dos rankings
    const width = 3;
    const height = data.ranking.length;
    for (let i = 0; i < height; i++){
      var row = document.createElement('tr');
      var cell = document.createElement('td');
      cell.innerHTML = i+1;
      row.appendChild(cell);
      var cell = document.createElement('td');
      cell.innerHTML = data.ranking[i].nick;
      row.appendChild(cell);
      var cell = document.createElement('td');
      cell.innerHTML = data.ranking[i].victories;
      row.appendChild(cell);
      var cell = document.createElement('td');
      cell.innerHTML = data.ranking[i].games;
      row.appendChild(cell);
      body.appendChild(row);
    }
  })
  .catch(function(error){
    console.log('Fetch failed', error);
  })
}

////////////////////////////////////////////////////
//   Funcao para desativar o atributo onclick das colunas
////////////////////////////////////////////////////
function winner() {
  var columns = document.getElementById('game').childNodes;
  for (var i = 0; i < columns.length; i++) {
    columns[i].removeAttribute("onclick");
    columns[i].style.cursor = "auto";
  }
}

////////////////////////////////////////////////////
//   Funcao que poe a peca do jogador 2
////////////////////////////////////////////////////
function jog2PutChip(column, turn){
  var columns = document.getElementById('game').childNodes;
  var j2_slots = columns[column].childNodes;
  for (let i = j2_slots.length-1; i>=0; i--){
    if(j2_slots[i].style.backgroundColor == "white"){
      j2_slots[i].style.backgroundColor = "#f4dc22";
      break;
    }
  }
}

////////////////////////////////////////////////////
//   Funcao que poe a peca do jogador 1
////////////////////////////////////////////////////
function jog1PutChip(column, turn){
  var columns = document.getElementById('game').childNodes;
  var j1_slots = columns[column].childNodes;
  for (let i = j1_slots.length-1; i>=0; i--){
    if(j1_slots[i].style.backgroundColor == "white"){
      j1_slots[i].style.backgroundColor = "#ea3a3a";
      break;
    }
  }
}
