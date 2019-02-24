#include<stdio.h>
#include<dos.h>
#include<graphics.h>
char key,a,name[100],game[10];
int ma[132]={330,330,330,262,330,392,196,262,196,164,220,246,233,220,196,330,392,440,349,392,330,262,247,262,196,164,220,246,233,220,196,330,392,440,349,392,330,262,294,247,392,370,349,311,330,207,220,262,220,262,294,392,370,349,311,330,523,523,523,392,370,349,311,330,207,220,262,220,262,294,311,296,262,262,262,262,262,294,330,262,220,196,262,262,262,262,294,300,440,392,262,262,262,262,294,330,262,220,196,330,330,330,262,330,392,196,196,262,330,392,523,660,784,660,207,262,311,415,523,622,830,622,233,294,349,466,587,698,932,932,932,932};
int i,color=1,choice,time=0,k=10,ca=25,m=10,n=0,j=5,temp=0,life=3,score=1,target=51,count=0,level=0,track=25,position=15,total=0,highscore=0,missedballs=0;
	long int l;
	long long ss;
	void car();
	void print(int,int,char);
	void Prnt1();
	void Prnt2();
	void load();
	void setobject();

void main()
{
char playername[100];
FILE *fp=fopen("highscore.txt","r+");
clrscr();
fscanf(fp,"%s%d",playername,&highscore);
if(total>highscore){
			gotoxy(40,40);
			printf("U GOT THE HIGHEST SCORE!!!");
			getch();
			clrscr();
			printf("\n\nENTER YOUR NAME");
			scanf("%[^\n]s",name);
			fp=fopen("highscore.txt","w");
			fprintf(fp,"%s\t%d",name,total);
			fclose(fp);
			clrscr();
			}
textcolor(10);
cprintf("\t\t\tWELCOME TO NEED FOR SPEED\n\n\t\t\tselect your game");
printf("\n\n\n\t\t\t1:ASPHALT 6");
printf("\n\n\n\t\t\t2:SAVE WATER");
printf("\n\n\n\t\t\t3:FF7");
printf("\n\n\n\t\t\t4:HIGHSCORE");
printf("\n\n\n\t\t\tENTER YOUR CHOICE:");
scanf("%d",&choice);
clrscr();
setobject();
total=0;
if(choice==1||choice==3)
Prnt1();
else if(choice==2)
Prnt2();
else if(choice==4)
{
printf("%s\t%d",playername,highscore);
getch();
main();
}
else
exit(1);
load();
car(ca);
}
	void print(int x,int y,char z)
	{
		gotoxy(40,3);
		printf(" SCORE:%d ",score);
		gotoxy(40,5);
		printf(" LIFE:%d ",life);
		if(choice==1||choice==3){
		gotoxy(x,y);
	cprintf(" %c ",127);
	gotoxy(x,y+1);
	cprintf("%c%c%c",z,z,z);
	gotoxy(x,y+2);
	cprintf("%c %c",z,z);  }
	else
	{
	gotoxy(x-1,y-1);
	cprintf("%c  %c",219,219);
	gotoxy(x-1,y);
	cprintf("%c%c%c%c",219,219,219,219);
	gotoxy(40,7);
	printf("BALLS MISSED:%d",missedballs);

	}
       }
       void balls(int x,int y,char z){
	gotoxy(x,y);
	cprintf("%c%c",z,z);
	gotoxy(x,y-1);
	cprintf("%c%c",z,z);
		       }

	void load()
{

    int r;
    long int q;
    clrscr();
    gotoxy(30,11);
    printf("ARE YOU READY!!!");
    gotoxy(36,14);
    printf("loading...");
    gotoxy(30,18);
    printf("THEN Y WAITING click ENTER AND START PLAYING");
    gotoxy(30,15);
    for(r=1; r<=20; r++)
    {
	for(q=0; q<=1000000; q++); //to display the character slowly
	printf("%c",177);
    }
    getch();
}


       void Prnt1()
 {
    gotoxy(10,12);
    printf("\tWelcome to the mini car game.(press any key to continue)\n");
    getch();
    clrscr();
    printf("\tGame instructions:\n");
    printf("\n-> Use arrow keys to move the car.\n\n-> there will be several cars ahead you which you have to overtake. Everytime you overtake score will be increased.\n\n-> Here you are provided with three lives. Your life will decrease as you hit the opponent car.\n\nHINT:you can traverse through opponent car within a particular time\n\n");
    printf("\n\nPress any key to play game...");
    if(getch()==27)
	exit(0);
}
       void Prnt2()
 {
    gotoxy(10,12);
    printf("\tWelcome to the mini ball game.(press any key to continue)\n");
    getch();
    clrscr();
    printf("\tGame instructions:\n");
    printf("\n-> Use arrow keys to move the box.\n\n-> there will be several balls falling ahead which you have to catch. Everytime you catch your score will be increased.\n\n-> Here you are provided with three lives. Your life will decrease as you loose three balls.\n\n");
    printf("\n\nPress any key to play game...");
    if(getch()==27)
	exit(0);
}
	void setobject()
	{
		if(choice==1||choice==3)
		{
			m=10;n=0;k=10;
			if(choice==3||level>=3)
			{
				j=5;
				level=3;
				track=29;
				position=18;
				k=8;
			}
		}
		else if(choice==2)
		{
			m=0;n=10;
			j=5;
			position=22;
			track=29;
			k=15;
		}
		color=1;
	 }


	 void alert(){
			  for(ss=700;ss<=1000;ss++){
				      sound(ss);
				      delay(200);
				      ss+=87;
					  }
				      nosound();
				      life--;
				      clrscr();
			gotoxy(25,14);
			printf("YOU HAVE %d LIFES LEFT",life);
			printf("\n\n\t\t\tSCORE:%d",score);
			printf("\n\n\t\t\tpress enter to continue\n\n\t\t\t press E to main menu\t");
			scanf("%c",&a);
			scanf("%c",&a);
			if(a=='E'||a=='e'){
			main();
			}

			if(life==0){
			clrscr();
			gotoxy(25,14);
			printf("GAME OVER  !!!");
			printf("\nYOUR FINAL SCORE:%d",total);
			getch();
			target=51;
			life=3;
			level=0;
			count=0;
			score=0;
			missedballs=0;
			//main();
			exit(1);
			}
		       //	if(score>10){
			//score-=10; }

			setobject();
			ca=25;
			car(ca);
		 }
	void carno(){
		 if(j==22){
			 j=2;
			 color++;
			 }
			 if(n==22)
					n=4;
				if(m==22)
					m=8;
					 print(29,j,'#');

		}

void car(int ca){
		do{

				for(l=0;l<500000;l++){
				if(level<3&&choice==1)
				for(i=0;i<k-score;i++){}
				else
				for(i=0;i<k;i++){}
				}
						clrscr();
						textcolor(color);
						for(i=1;i<25;i++){
						gotoxy(13,i);
						cprintf("%c%c%c%c%c%c",175,175,175,175,175,176);
						gotoxy(track+4,i);
						cprintf("%c%c%c%c%c%c",176,174,174,174,174,174);
						}
						if(level>=3&&(choice==1||choice==3)){
							textcolor(color+8);
							carno();
						}
						textcolor(14);
						print(ca,position,'#');
						if(choice==2){
						textcolor(10);
						balls(25,n,173);
						textcolor(15);
						balls(21,m,173);
						textcolor(9);
						balls(29,j,173);
						gotoxy(19,23);
						printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c",247,247,247,247,247,247,247,247,247,247,247,247,247,247);
						gotoxy(19,24);
						printf("%c%c%c%c%c%c%c%c%c%c%c%c%c%c",247,247,247,247,247,247,247,247,247,247,247,247,247,247);
						}
						else{
						textcolor(5);
						print(25,n,'#');
						textcolor(3);
						print(21,m,'#');
						}
						sound(ma[time]);
						n++;
						m++;
						j++;
						if(color%8==0)
						color++;
				if(n==position+5){
					n=0;
					color++;
					}
				if(m==position+5)
					m=0;
				if(21*m==ca*position||ca*position==25*n||ca*position==29*j){
				      if(choice==2){
				       if(m==position){
				       m=5;
				       color+=13;
				       }
				       else if(n==position){
				       n=6;
				       }
				       else
				       j=5;
				      }
				      else{

					alert();
				    }
				    }
				    else if((m>position||n>position||j>position)&&choice==2){
				    missedballs++;
				    setobject();
				    if(missedballs==3){
				    missedballs=0;
				    alert();
				    }
				     }

	}while(!kbhit());
	key=getch();
	if(key==75){
		if(ca<=track&&ca>21)
		ca-=4;
		score++;
		time++;
	}
	if(key==77){
		if(ca>=21&&ca<track)
		ca+=4;
		score++;
		time++;
	}if(score==target){
			for(i=700;i<=1500;i++)
				{
				sound(i);
				delay(200);
				i+=77;
				}
				nosound();
			count++;
				clrscr();
				gotoxy(25,15);
				printf("LEVEL %d\n\t\t\tSUCCESSFULLY COMPLETED !!!",count);
				getch();
				clrscr();
				gotoxy(25,15);
				printf("LEVEL %d",count+1);
				scanf("%c",&a);
				scanf("%c",&a);
				total+=score;
				score=0;
				k-=2;
				time=0;
				life++;
				target+=25;
				level++;
				ca=25;
				setobject();
			}

if(time>133)
time=0;
	car(ca);
	}
