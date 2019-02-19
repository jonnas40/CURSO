var username;
var password;
var colnum;
var size = {
  rows: 0,
  columns: 0
}

function config() {
  document.getElementById('poplogin').style.display = "none";
  document.getElementById('config').style.display = "block";
  document.getElementById("prijog").checked = true;
  document.getElementById("cpu").checked = true;
  document.getElementById('dif').disabled = true;
  var conspan = document.getElementsByClassName("bclose")[0];
  conspan.onclick = function() {
    document.getElementById('config').style.display = "none";
  }
  var cpu = document.getElementById('cpu');
  cpu.onclick = function() {
    document.getElementById("prijog").disabled = false;
    document.getElementById("pricpu").disabled = false;
  }
  var jog = document.getElementById('jog');
  jog.onclick = function() {
    document.getElementById("prijog").disabled = true;
    document.getElementById("pricpu").disabled = true;
  }
  document.getElementById('Save').onclick = function() {
    if(document.getElementById('jog').checked == true){
      size.rows = document.getElementById("lar").value;
      size.columns = document.getElementById("alt").value;
      join(username, password, size);
    }
    else {
      document.getElementById('bdesist').setAttribute("onclick", "disable()");
      initialize();
    }
  }
}

function show_Login(){
  document.getElementById('poplogin').style.display = "block";
  document.getElementById('rem').checked = false;
  document.getElementById('loginbtn').onclick = function() {
    username = document.getElementById('loguser').value;
    password = document.getElementById('logpasswd').value;
    register(username, password);
  }
}

function classi() {
  var clamodal = document.getElementById('classi');

  var bclassi = document.getElementById("bclassi");

  var claspan = document.getElementsByClassName("bclose")[1];

  clamodal.style.display = "block";

  claspan.onclick = function() {
    clamodal.style.display = "none";
  }
  let wname = document.getElementById('claname').innerHTML + "win";
  let lname = document.getElementById('claname').innerHTML + "loss";
  if(localStorage.wname){
    document.getElementById('win').innerHTML = localStorage.wname;
  }
  else{
    document.getElementById('win').innerHTML = 0;
  }
  if(localStorage.lname){
    document.getElementById('loss').innerHTML = localStorage.lname;
  }
  else{
    document.getElementById('loss').innerHTML = 0;
  }
}

function instru(){
  var insmodal = document.getElementById('instru');

  var inspan = document.getElementsByClassName("bclose")[2];

  insmodal.style.display = "block";

  inspan.onclick = function() {
    insmodal.style.display = "none"
  }
}

////////////////////////////////////////////////////
//   Desativa o Tabuleiro ao clicar no butao desistir
////////////////////////////////////////////////////

function disable(){
  var node = document.getElementById('node');
  if(log.firstChild == null){
    writelog("desist");
    changeStats("loss");
  }
  var game = document.getElementById('game');
  var columns = game.childNodes;
  for (let k = 0; k < columns.length; k++) {
    columns[k].removeAttribute("onclick");
    columns[k].style.cursor = "auto";
  }
}

function toranking(){
  document.getElementById('classi').style.display = "none";
  document.getElementById('netclass').style.display = "block";
  var rankspan = document.getElementsByClassName("bclose")[3];
  rankspan.onclick = function() {
    document.getElementById('netclass').style.display = "none";
    document.getElementById('classi').style.display = "block";
  }
}
