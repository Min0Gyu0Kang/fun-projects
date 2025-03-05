var canvas = document.getElementById('canvas');
var ctx = canvas.getContext('2d');

canvas.width = window.innerWidth - 100;
canvas.height = window.innerHeight- 100;

var img1 = new Image();
img1.src = 'player.png';

var dino ={
    x:10,
    y:200,
    width:50,
    height:50,
    draw(){
        ctx.fillStyle = 'grey';
        ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img1,this.x,this.y,this.width,this.height);
    }
}
dino.draw()

var img2 = new Image();
img2.src = 'ground.png';

var ground ={
    x:10,
    y:250,
    width:1000,
    height:60,
    draw(){
        ctx.fillStyle = 'green';
        //ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img2,this.x,this.y,this.width,this.height);
    }
}
ground.draw()

var img3 = new Image();
img3.src = 'rock.png';
class Rock{
    constructor(){
        this.x=1000;
        this.y=200;
        this.width=50;
        this.height=50;
    }
    draw(){
        ctx.fillStyle = 'red';
        ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img3,this.x,this.y,this.width,this.height);
    }
}

var img4 = new Image();
img4.src = 'bigrock.png';
class Bigrock{
    constructor(){
        this.x=6000;
        this.y=200;
        this.width=50;
        this.height=100;
    }
    draw(){
        ctx.fillStyle = 'purple';
        //ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img4,this.x,this.y,this.width,this.height);
    }
}

var img5 = new Image();
img5.src = 'flyenemy.png';
class Flyenemy{
    constructor(){
        this.x=900;
        this.y=100;
        this.width=50;
        this.height=50;
    }
    draw(){
        ctx.fillStyle = 'red';
        //ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img5,this.x,this.y,this.width,this.height);
    }
}

var img6 = new Image();
img6.src = 'gameover.png';

var gameover ={
    x:450,
    y:100,
    width:162,
    height:87,
    draw(){
        ctx.fillStyle = 'green';
        //ctx.fillRect(this.x,this.y,this.width,this.height);
        ctx.drawImage(img6,this.x,this.y,this.width,this.height);
    }
}

var timer = 0;
var rock여러개=[];var bigrock여러개=[];var flyenemy여러개=[];
var 점프timer=0;
var animation;

function 프레임마다실행할거(){
    animation= requestAnimationFrame(프레임마다실행할거);
    timer++;
    //모든 잔상 제거
    ctx.clearRect(0,0,canvas.width,canvas.height);
    //돌 출현
    if(timer%140==0){
        var rock = new Rock();
        rock여러개.push(rock);
        
    }
    rock여러개.forEach((a,i,o)=>{
        //x 좌표가 0 미만이면 제거
        if(a.x<0){
            o.splice(i,1)
        }
        a.x-=2;
        충돌하냐(dino,a);
        a.draw();
    })
    //빅돌 출현
    if(timer%464==0){
        var bigrock = new Bigrock();
        bigrock여러개.push(bigrock);
        
    }
    bigrock여러개.forEach((b,i,o)=>{
        //x 좌표가 0 미만이면 제거
        if(b.x<0){
            o.splice(i,1)
        }
        b.x-=2;
        충돌하냐(dino,b);
        b.draw();
    })
 //나는 놈 출현
    if(timer%500==0){
        var flyenemy = new Flyenemy();
        flyenemy여러개.push(flyenemy);
        
    }
    flyenemy여러개.forEach((c,i,o)=>{
        //x 좌표가 0 미만이면 제거
        if(c.x<0){
            o.splice(i,1)
        }
        c.x-=6;
        c.draw();
    })


    //점핑
    if(점프중 ==true){
        dino.y-=5;
        점프timer++;
    }
    if(점프중 ==false){
        if(dino.y<200)
        dino.y+=5;
    }
    if(점프timer > 50){
        점프중 = false;
        점프timer=0;
    }
    dino.draw()
    ground.draw()
}
프레임마다실행할거();
//충돌확인

function 충돌하냐(dino,cactus){
    var x축차이 = cactus.x-(dino.x+dino.width);
    var y축차이 = cactus.y-(dino.y+dino.height);
    if(x축차이 < 0 && y축차이 < 0){
        ctx.clearRect(0,0,canvas.width,canvas.height);
        gameover.draw()
        cancelAnimationFrame(animation);
    }
}
    //스페이스바 누르면 점프
    var 점프중 = false;
    document.addEventListener('keydown',function(e){
        if(e.code==='Space'){
           // if(점프중===false){
                점프중 = true;
           // }
        }
    })