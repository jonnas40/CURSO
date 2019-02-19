
////////////////////////////////////////////////////
//   Funcao que retira dados das configuracoes
////////////////////////////////////////////////////

function initialize(){
  const height = document.getElementById("alt").value;
  const width = document.getElementById("lar").value;
  var won = false;
  // 0 = Computador, 1 = Jogador
  if(document.getElementById('prijog').checked){
    var player = 1;
  }
  else {
    var player = 0;
  }
  //limpa o log
  cleanlog();
  //remove o modal das configuracoes
  document.getElementById('config').style.display = "none";
  makeBoard(height, width);
  if(player == 0){
    putChipPc();
  }
}

////////////////////////////////////////////////////
//   Construtor do Tabuleiro
////////////////////////////////////////////////////

function makeBoard(height, width, opt){
  var stop = false;
  const h = height;
  const w = width;
  var game = document.getElementById('game');
  while(game.firstChild){  //certifica-se que a div game esta vazia
      game.removeChild(game.firstChild);
  }
  for (let i = 0; i < w; i++) {
    var column = document.createElement("div");
    column.setAttribute("class" , "col");
    column.style.height = h * 64 + 12 + "px";     //atribui dados do tamanho as colunas
    column.style.width = 80 + "px";
    document.getElementById('game').style.height = h * 64 + 20 + "px";
    document.getElementById('game').style.width = w * 84 + "px";
    for (let j = 0; j < h; j++){
      var cell = document.createElement("div");
      cell.setAttribute("class" , "cel");
      cell.style.height = 60 + "px";              //atribui dados do tamanho das celulas
      cell.style.width = 60 + "px";
      cell.style.backgroundColor = "white";
      column.appendChild(cell);                   //adiciona a celula a div column
    }
    game.appendChild(column);                     //adiciona a coluna a div game
    if(opt == undefined){
      column.setAttribute("onclick" , "putChip(this)");
    }
    else{
      column.setAttribute("onclick" , "notify("+i+",this)");
    }
  }
}

////////////////////////////////////////////////////
//   Funcao de por o chip do jogador
////////////////////////////////////////////////////

function putChip(column){
  var player = 1;
  // gera todos os slots da coluna selecionada
  var slots = column.childNodes;
  if(slots[0].style.backgroundColor != "white"){
    writelog("impossible");
    return 1;
  }
  for (let i = slots.length-1; i>=0; i--){
    if(slots[i].style.backgroundColor == "white"){
      slots[i].style.backgroundColor = "#ea3a3a";
      analyze(player);
      player = 0;
      break;
    }
  }
  while(player == 0){
    player = putChipPc(player);
  }
}

////////////////////////////////////////////////////
//   Chip do Pc posta aleatoriamente
////////////////////////////////////////////////////

function putChipPc(){
  var player = 0;
  var game = document.getElementById('game');
  var columns = game.childNodes;
  for (;;){
    var bet = Math.floor((Math.random() * columns.length-1) + 1); //gera um numero aleatorio
    var slots = columns[bet].childNodes;
    if(slots[0].style.backgroundColor != "white"){
      return player;
    }
    for (let i = slots.length-1; i>=0; i--){
      if(slots[i].style.backgroundColor == "white"){
        slots[i].style.backgroundColor = "#f4dc22";
        analyze(player);
        player = 1;
        return player;
      }
    }
  }
}

function analyze(player, online){
  if(player == 1){
    var color = "rgb(234, 58, 58)"; //cor do jogador
  }
  else{
    var color = "rgb(244, 220, 34)"; //cor do CPU
  }
  var cont = 0;
  var game = document.getElementById('game');
  var columns = game.childNodes;

////////////////////////////////////////////////////
//   Analisa verticalmente
////////////////////////////////////////////////////

  for (let i = 0; i <columns.length; i++){
    var vslots = columns[i].childNodes;
    for (let j = vslots.length-1; j>=0; j--){
      if (vslots[j].style.backgroundColor == color){
        cont++;
        if(cont==4){
          for (let k = j; k < j + 4; k++){
            vslots[k].style.boxShadow = "0 0 25px white";
          }
          if(player == 1 && online == undefined){
            writelog("pwin");
            changeStats("win");
          }
          else if(player == 0 && online == undefined){
            writelog("cwin");
            changeStats("loss");
          }
          for (let k = 0; k < columns.length; k++){
            columns[k].removeAttribute("onclick");    //impossibilita o jogador
            columns[k].style.cursor = "auto";         //de fazer mais jogadas
          }
          return;
        }
      }
      else{
        cont = 0;
      }
    }
    cont = 0;
  }
  cont = 0;
////////////////////////////////////////////////////
//   Analisa horizontalmente
////////////////////////////////////////////////////

  for (let i = 0; i < vslots.length; i++){
    for (let j = 0; j < columns.length; j++){
      var hslots = columns[j].childNodes;
      if(hslots[i].style.backgroundColor == color){
        cont++;
        if(cont==4){
          for (let k = j; k > j - 4; k--){
            hslots = columns[k].childNodes;
            hslots[i].style.boxShadow = "0 0 25px white";
          }
          if(player == 1 && online == undefined){
            writelog("pwin");
            changeStats("win");
          }
          else if(player == 0 && online == undefined){
            writelog("cwin");
            changeStats("loss");
          }
          for (let k = 0; k < columns.length; k++){
            columns[k].removeAttribute("onclick");
            columns[k].style.cursor = "auto";
          }
          return;
        }
      }
      else{
        cont = 0;
      }
    }
    cont = 0;
  }
  cont = 0;
////////////////////////////////////////////////////
//   Analisa diagonalmente baixo-cima esquerda direita
////////////////////////////////////////////////////

  for (let slice = 0; slice < vslots.length + columns.length -1; slice++) {
    if(slice < columns.length){
      var z1 = 0;
    }
    else{
      var z1 = slice - columns.length + 1;
    }
    if(slice < vslots.length){
      var z2 = 0;
    }
    else{
      var z2 = slice - vslots.length + 1;
    }
    for (let j = slice - z2; j>=z1; j--) {
      var drslots = columns[slice - j].childNodes;
      if(drslots[j].style.backgroundColor == color){
        count++;
        if(count == 4){
          var x1 = slice - j;
          var x2 = j;
          var i = 0;
          while(i<4){
            var ddrslots = columns[x1 - i].childNodes;
            ddrslots[x2 + i].style.boxShadow = "0 0 25px white";
            i++;
          }
          if(player == 1 && online == undefined){
            writelog("pwin");
            changeStats("win");
          }
          else if(player == 0 && online == undefined){
            writelog("cwin");
            changeStats("loss");
          }
          for (let k = 0; k < columns.length; k++){
            columns[k].removeAttribute("onclick");
            columns[k].style.cursor = "auto";
          }
          return;
        }
      }
      else{
        count = 0;
      }
    }
    count=0;
  }
  cont = 0;

////////////////////////////////////////////////////
//   Analisa diagonalmente baixo-cima direita esquerda
////////////////////////////////////////////////////

for (let slice = 0; slice < vslots.length + columns.length -1; slice++) {
  if(slice < columns.length){
    var z1 = 0;
  }
  else{
    var z1 = slice - columns.length + 1;
  }
  if(slice < vslots.length){
    var z2 = 0;
  }
  else{
    var z2 = slice - vslots.length + 1;
  }
  for (let j = (vslots.length -1) - slice + z2; j<= (vslots.length - 1) - z1; j++) {
    var drslots = columns[j + (slice - vslots.length + 1)].childNodes;
    if(drslots[j].style.backgroundColor == color){
      count++;
      if(count == 4){
        var x1 = j + (slice - vslots.length + 1);
        var x2 = j;
        var i = 0;
        while(i<4){
          var ddrslots = columns[x1 - i].childNodes;
          ddrslots[x2 - i].style.boxShadow = "0 0 25px white";
          i++;
        }
        if(player == 1 && online == undefined){
          writelog("pwin");
          changeStats("win");
        }
        else if(player == 0 && online == undefined){
          writelog("cwin");
          changeStats("loss");
        }
        for (let k = 0; k < columns.length; k++){
          columns[k].removeAttribute("onclick");
          columns[k].style.cursor = "auto";
        }
        return;
      }
    }
    else{
      count = 0;
    }
  }
  count=0;
 }
 cont = 0;

////////////////////////////////////////////////////
//   Empate
////////////////////////////////////////////////////

  for(let i  = 0; i<columns.length; i++){
    var eslots = columns[i].childNodes;
    if(eslots[0].style.backgroundColor != "white"){
      count++;
      if(count == eslots.length){
        writelog("draw");
        for (let k = 0; k < columns.length; k++){
          columns[k].removeAttribute("onclick");
          columns[k].style.cursor = "auto";
        }
      }
    }
    else {
      count = 0;
    }
  }
  count = 0;
}
////////////////////////////////////////////////////
//   Muda as Classificações
////////////////////////////////////////////////////

function changeStats(arg){
  if(typeof(Storage) !== "undefined"){
    if(arg == "win"){
      let wname = document.getElementById('claname').innerHTML;
      let lname = document.getElementById('claname').innerHTML;
      if(localStorage.wname){
        localStorage.wname = Number(localStorage.wname)+1;
      }
      else{
        localStorage.wname = 1;
      }
      document.getElementById('win').innerHTML = localStorage.wname;
    }
    else{
      if(localStorage.lname){
        localStorage.lname = Number(localStorage.lname)+1;
      }
      else{
        localStorage.lname = 1;
      }
      document.getElementById('loss').innerHTML = localStorage.lname;
    }
  }
  else {
    document.getElementById("win").innerHTML = "Sorry, your browser does not support web storage...";
  }
}

////////////////////////////////////////////////////
//   Escreve a condicao de vitoria no log
////////////////////////////////////////////////////

function writelog(condition){
  var log = document.getElementById('log');
  var node = document.createElement("a");
  if(condition == "pwin"){
    cleanlog();
    var textnode = document.createTextNode("Guest ganhou.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "cwin"){
    cleanlog();
    var textnode = document.createTextNode("CPU ganhou.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if(condition == "lwin"){
    cleanlog();
    let string = String(document.getElementById('logname').innerHTML);
    var textnode = document.createTextNode(string + " ganhou.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if(condition == "nwin"){
    cleanlog();
    var textnode = document.createTextNode("Adversario ganhou.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "desist"){
    cleanlog();
    var textnode = document.createTextNode("Guest desistiu.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "draw"){
    cleanlog();
    var textnode = document.createTextNode("Empate.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "leave"){
    cleanlog();
    let string = String(document.getElementById('logname').innerHTML);
    var textnode = document.createTextNode(string + " foi desconectado\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "opleave") {
    cleanlog();
    let string = String(document.getElementById('logname').innerHTML);
    var textnode = document.createTextNode("O adversario foi desconectado\n"+string+" ganhou\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "playleave") {
    cleanlog();
    let string = String(document.getElementById('logname').innerHTML);
    var textnode = document.createTextNode(string + " foi desconectado\nO adversario ganhou\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
  else if (condition == "impossible"){
    cleanlog();
    var textnode = document.createTextNode("Não pode jogar ai.\n");
    node.appendChild(textnode);
    document.getElementById('log').appendChild(node);
  }
}

////////////////////////////////////////////////////
//   Limpa o log
////////////////////////////////////////////////////

function cleanlog(){
  var log = document.getElementById('log');
  while (log.firstChild){
    log.removeChild(log.firstChild);
  }
}
